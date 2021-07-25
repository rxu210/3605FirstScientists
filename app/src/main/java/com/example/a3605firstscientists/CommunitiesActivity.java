package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class CommunitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communities);

        //Set TextView
        TextView commMessage = findViewById(R.id.tv_commMsg);
        commMessage.setText("Climate change is happening and is here to stay. Whether it's " +
                "bushfires, heatwaves, floods or cyclones, extreme weather events are becoming more " +
                "frequent and more intense. With the poor and vulnerable likely to be hit the " +
                "hardest, it's essential that communities are supported to adapt to the changing conditions.\n" +
                "Red Cross has developed “Climate-Ready Communities: A Guide to Getting Started” " +
                "to encourage communities to have their own conversation about how the things they " +
                "value will be impacted by climate change, and what they can do to continue to thrive.\n" +
                "This guide is for anyone wanting to prepare for and adapt to the impacts of " +
                "climate change, whether they be individuals, a self-organised community group, " +
                "local council or community service provider.\n");

        // Method for handling PDF link
        TextView link = findViewById(R.id.tv_commLink);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("https://www.redcross.org.au/getmedia/b5b004b5-e572-4d9d-a1a1-c8fb5d1be5e3/climate-ready-communities-a-guide-to-getting-started.pdf.aspx"));
                startActivity(intent);
            }
        });

        // Method for handling Back button
        ImageButton back = findViewById(R.id.btn_commBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunitiesActivity.this, ActionPlanActivity.class);
                intent.putExtra("From", "CommunitiesActivity");
                startActivity(intent);
            }
        });

    }
}