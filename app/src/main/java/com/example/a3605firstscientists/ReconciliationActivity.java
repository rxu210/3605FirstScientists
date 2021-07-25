package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ReconciliationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconciliation);

        TextView recMessage = findViewById(R.id.tv_recmsg);
        recMessage.setText("Red Cross pledges its support for the redress of systemic discrimination" +
                " and disadvantage that has affected Aboriginal and Torres Strait Islander peoples " +
                "for generations. We acknowledge that we must do more in our efforts towards true reconciliation. \n" +
                "\n" +
                "Our current Reconciliation Action Plan (RAP), “Stronger Together (2018-21)”, is our " +
                "third and at the Stretch Level. It provides an overview of what we have achieved so " +
                "far and what is in store for us. This plan outlines our analysis of the impact of " +
                "climate change on First Nations communities’ wellbeing, language, cultures and " +
                "ecologies. The results will help us adapt our location-based climate mitigation " +
                "strategies and help amplify First Nations’ voices. \n");

        // Method for handling PDF link
        TextView link = findViewById(R.id.tv_reclink);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("https://www.redcross.org.au/getmedia/eb367b0e-905c-4f96-8fea-4baee0bfe59a/Red-Cross-RAP-2018_1.pdf?utm_medium=email&utm_campaign=CEO%20Blog%20Staff%20Reconciliation%20Champions%20Takeover&utm_content=CEO%20Blog%20Staff%20Reconciliation%20Champions%20Takeover+Preview+CID_c9aad61a9ef58b83ff5e9b4108aa6e05&utm_source=campaignmonitor&utm_term=Stronger%20Together%202018-21"));
                startActivity(intent);
            }
        });

        // Method for handling Back button
        ImageButton back = findViewById(R.id.btn_recback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReconciliationActivity.this, ActionPlanActivity.class);
                intent.putExtra("From", "ReconciliationActivity");
                startActivity(intent);
            }
        });
    }
}