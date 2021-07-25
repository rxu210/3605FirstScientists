package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ActionPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_plan);

        // Method for handling Reconciliation button
        Button reconciliation = findViewById(R.id.btn_reconciliation);
        reconciliation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActionPlanActivity.this, ReconciliationActivity.class);
                startActivity(intent);
            }
        });

        // Method for handling Climate button
        Button climate = findViewById(R.id.btn_climatechange);
        climate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActionPlanActivity.this, ClimateActivity.class);
                startActivity(intent);
            }
        });

        // Method for handling Communities button
        Button communities = findViewById(R.id.btn_communities);
        communities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActionPlanActivity.this, CommunitiesActivity.class);
                startActivity(intent);
            }
        });

        // Method for handling Back button
        ImageButton back = findViewById(R.id.btn_apback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActionPlanActivity.this, UpdatedLearnActivity.class);
                intent.putExtra("From", "Action Plan Activity");
                startActivity(intent);
            }
        });
    }
}