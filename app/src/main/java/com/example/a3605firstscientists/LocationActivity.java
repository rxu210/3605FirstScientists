package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    TextView tvLocation, tvCoordinates;
    Button btnYes, btnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Update TextViews to display user's location, retrieved in HomeActivity
        tvLocation = (findViewById(R.id.tvLocation));
        tvLocation.setText("Is your approximate address " + HomeActivity.userAddress + "?");

        tvCoordinates = (findViewById(R.id.tvCoordinates));
        tvCoordinates.setText("Are your current coordinates approximately " + HomeActivity.latitude
         + " by " + HomeActivity.longitude + "?");

        // Method for handling No button to manually add new location
        btnNo = (findViewById(R.id.btnNo));
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this, AddActivity.class);
                intent.putExtra("From", "LocationActivity");
                startActivity(intent);
            }
        });
    }
}