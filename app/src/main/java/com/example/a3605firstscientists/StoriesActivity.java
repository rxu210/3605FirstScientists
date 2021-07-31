package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

public class StoriesActivity extends AppCompatActivity {

    private static final String TAG = "Stories Activity: called.";
    // Initialising recyclerview and adapter
    private RecyclerView vRecyclerView;
    private StoriesAdapter vAdapter;
    private List<Stories> story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);

        // Find the handle to the recyclerView element
        vRecyclerView = findViewById(R.id.voicesList);
        // Make recyclerview more performant by making every item of the recyclerview have a
        // fixed size
        vRecyclerView.setHasFixedSize(true);
        //Specify layout manager
        vRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Instantiate an adapter object
        vAdapter = new StoriesAdapter(Stories.getStories());
        // Set the adapter to the recycler view object
        vRecyclerView.setAdapter(vAdapter);

        // Method for handling Back button
        ImageButton back = findViewById(R.id.btn_learnback3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoriesActivity.this, UpdatedLearnActivity.class);
                intent.putExtra("From", "Updated Stories Activity");
                startActivity(intent);
            }
        });

    }
}