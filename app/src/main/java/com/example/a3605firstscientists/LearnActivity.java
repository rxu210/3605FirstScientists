package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        // Method for handling Support Button
        CardView cvSupport = findViewById(R.id.supportcard);
        cvSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnActivity.this, Donate.class);
                intent.putExtra("From", "LearnActivity");
                startActivity(intent);
            }
        });

        // Method for handling Back button
        ImageButton back = findViewById(R.id.btn_learnback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnActivity.this, HomeActivity.class);
                intent.putExtra("From", "Learn Activity");
                startActivity(intent);
            }
        });

    }
}