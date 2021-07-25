package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UpdatedLearnActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity: called.";
    // Initialising recyclerview and adapter
    private RecyclerView mRecyclerView;
    private LearnAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_learn);

        // Find the handle to the recyclerView element
        mRecyclerView = findViewById(R.id.cardList);
        // Make recyclerview more performant by making every item of the recyclerview have a
        // fixed size
        mRecyclerView.setHasFixedSize(true);

        /*
        // Implements onClickListener for when the recyclerview is clicked to navigate to the
        // detail activity screen
        MovieAdapter.RecyclerViewClickListener listener = new MovieAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, String id) {
                launchDetailActivity(id);
            }
        };

         */

        // Instantiate an adapter object
        mAdapter = new LearnAdapter(Learn.getLearns());
        // Set the adapter to the recycler view object
        mRecyclerView.setAdapter(mAdapter);
        //Specify layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
        private void launchDetailActivity(String message) {
            // Use an explicit intent to Launch DetailActivity
            Intent intent = new Intent(this, DetailActivity.class);
            // Add extra message to the intent (otherwise it wont pass it)
            intent.putExtra(DetailActivity.INTENT_MESSAGE, message);
            // Start DetailActivity
            startActivity(intent);
        }

         */

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

}