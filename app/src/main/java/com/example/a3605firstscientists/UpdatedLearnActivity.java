package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class UpdatedLearnActivity extends AppCompatActivity implements LearnAdapter.OnItemClicked {

    private static final String TAG = "Main Activity: called.";
    // Initialising recyclerview and adapter
    private RecyclerView mRecyclerView;
    private LearnAdapter mAdapter;
    private List<Learn> learns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_learn);

        // Find the handle to the recyclerView element
        mRecyclerView = findViewById(R.id.cardList);
        // Make recyclerview more performant by making every item of the recyclerview have a
        // fixed size
        mRecyclerView.setHasFixedSize(true);
        //Specify layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Instantiate an adapter object
        mAdapter = new LearnAdapter(getApplicationContext(), Learn.getLearns());
        // Set the adapter to the recycler view object
        mRecyclerView.setAdapter(mAdapter);

        // Implements onClickListener for when the recyclerview is clicked to navigate to the
        // detail activity screen
        mAdapter.setOnClick(UpdatedLearnActivity.this);

        // Method for handling Back button
        ImageButton back = findViewById(R.id.btn_learnback2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdatedLearnActivity.this, HomeActivity.class);
                intent.putExtra("From", "Updated Learn Activity");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(UpdatedLearnActivity.this, StoriesActivity.class);
        intent.putExtra("from", "UpdatedLearnActivity");
        startActivity(intent);
    }
}