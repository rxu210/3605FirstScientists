package com.example.a3605firstscientists;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3605firstscientists.activities.Post;
import com.example.a3605firstscientists.activities.PostDetail;
import com.example.a3605firstscientists.activities.Posting;

public class AddActivity extends AppCompatActivity {

    AddressResultReceiver mResultReceiver;

    EditText latitudeEdit, longitudeEdit, addressEdit;
    ProgressBar progressBar;
    TextView infoText;
    CheckBox checkBox;
    Button btnSubmit;

    boolean fetchAddress;
    int fetchType = Constants.USE_ADDRESS_LOCATION;

    private static final String TAG = "MAIN_ACTIVITY";

    // Variables for temporarily storing user's manually added location
    double tempLatitude, tempLongitude;
    String tempAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        longitudeEdit = (EditText) findViewById(R.id.longitudeEdit);
        latitudeEdit = (EditText) findViewById(R.id.latitudeEdit);
        addressEdit = (EditText) findViewById(R.id.addressEdit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        infoText = (TextView) findViewById(R.id.infoText);
        checkBox = (CheckBox) findViewById(R.id.checkbox);

        mResultReceiver = new AddressResultReceiver(null);

        // Method for handling Submit Location button
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });

        // Method for handling Back button
        ImageButton btnLocation = findViewById(R.id.btn_locationback);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, Post.class);
                intent.putExtra("From", "Add Activity");
                startActivity(intent);
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioAddress:
                if (checked) {
                    fetchAddress = false;
                    fetchType = Constants.USE_ADDRESS_NAME;
                    longitudeEdit.setEnabled(false);
                    latitudeEdit.setEnabled(false);
                    addressEdit.setEnabled(true);
                    addressEdit.requestFocus();
                }
                break;
            case R.id.radioLocation:
                if (checked) {
                    fetchAddress = true;
                    fetchType = Constants.USE_ADDRESS_LOCATION;
                    latitudeEdit.setEnabled(true);
                    latitudeEdit.requestFocus();
                    longitudeEdit.setEnabled(true);
                    addressEdit.setEnabled(false);
                }
                break;
        }
    }

    // Method for handling check coordinates button
    public void onButtonClicked(View view) {
        Intent intent = new Intent(this, GeocodeAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, mResultReceiver);
        intent.putExtra(Constants.FETCH_TYPE_EXTRA, fetchType);
        if(fetchType == Constants.USE_ADDRESS_NAME) {
            if(addressEdit.getText().length() == 0) {
                Toast.makeText(this, "Please enter an address name", Toast.LENGTH_LONG).show();
                return;
            }
            intent.putExtra(Constants.LOCATION_NAME_DATA_EXTRA, addressEdit.getText().toString());
        }
        else {
            if(latitudeEdit.getText().length() == 0 || longitudeEdit.getText().length() == 0) {
                Toast.makeText(this,
                        "Please enter both latitude and longitude",
                        Toast.LENGTH_LONG).show();
                return;
            }
            intent.putExtra(Constants.LOCATION_LATITUDE_DATA_EXTRA,
                    Double.parseDouble(latitudeEdit.getText().toString()));
            intent.putExtra(Constants.LOCATION_LONGITUDE_DATA_EXTRA,
                    Double.parseDouble(longitudeEdit.getText().toString()));
        }
        infoText.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        Log.e(TAG, "Starting Service");
        startService(intent);

        // Make submit button visible after user has checked coordinates
        btnSubmit.setVisibility(View.VISIBLE);
    }

    // Method for saving user's manually submitted location
    public void onSubmit() {
        /*
        com.example.a3605firstscientists.activities.Login.latitude = tempLatitude;
        com.example.a3605firstscientists.activities.Login.longitude = tempLongitude;
        com.example.a3605firstscientists.activities.Login.userAddress = tempAddress;
        Intent intent = new Intent(AddActivity.this, com.example.a3605firstscientists.activities.Post.class);
        intent.putExtra("from", "AddActivity");
        startActivity(intent);
        */
        Intent returnIntent = new Intent();
        returnIntent.putExtra("postAddress", tempAddress);
        returnIntent.putExtra("postLatitude", tempLatitude);
        returnIntent.putExtra("postLongitude", tempLongitude);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, final Bundle resultData) {
            if (resultCode == Constants.SUCCESS_RESULT) {
                final Address address = resultData.getParcelable(Constants.RESULT_ADDRESS);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        infoText.setVisibility(View.VISIBLE);
                        // Save user's temporary location to variables
                        tempLatitude = address.getLatitude();
                        tempLongitude = address.getLongitude();
                        tempAddress = address.getLocality();
                        infoText.setText("Latitude: " + tempLatitude + "\n" +
                                "Longitude: " + tempLongitude + "\n" +
                                "Address: " + tempAddress);
                    }
                });
            }
            else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        infoText.setVisibility(View.VISIBLE);
                        infoText.setText(resultData.getString(Constants.RESULT_DATA_KEY));
                    }
                });
            }
        }
    }

}
