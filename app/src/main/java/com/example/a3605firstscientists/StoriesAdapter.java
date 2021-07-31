package com.example.a3605firstscientists;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Stories> vStories;

    // Add a constructor method for the StoriesAdapter class
    public StoriesAdapter(ArrayList<Stories> stories) {
        vStories = stories;
    }

    // Instantiate an instance from StoriesViewHolder and return (will recycle the views)
    @NonNull
    @Override
    public StoriesAdapter.StoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Specify the layout that we are going to use for the view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stories_list_row, parent, false);
        return new StoriesAdapter.StoriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesAdapter.StoriesViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Stories stories = vStories.get(position);
        holder.quote.setText(stories.getQuote());
        holder.image.setImageResource(stories.getImage());
        holder.person.setText(stories.getPerson());
        holder.itemView.setTag(stories.getId());

    }

    // Implement ViewHolder class for the adapter to hold each view and recycle
    public static class StoriesViewHolder extends RecyclerView.ViewHolder {
        // Define the TextViews, ImageView, Listener
        public TextView quote, person;
        public ImageView image;
        public StoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            person = itemView.findViewById(R.id.tv_voiceperson);
            image = itemView.findViewById(R.id.im_voicebg);
            quote = itemView.findViewById(R.id.tv_voicequote);
        }

    }

    // Return recyclerview list size equal to the number of list items
    @Override
    public int getItemCount() {
        return vStories.size();
    }

}
