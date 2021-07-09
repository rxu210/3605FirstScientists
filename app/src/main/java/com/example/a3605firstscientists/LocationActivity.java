package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    TextView tvLocation, tvCoordinates;

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
    }
}