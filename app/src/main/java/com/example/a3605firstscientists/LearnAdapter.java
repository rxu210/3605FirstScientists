package com.example.a3605firstscientists;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.LearnViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Learn> mLearns;
    private RecyclerViewClickListener mListener;
    private Context context;
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    // Add a constructor method for the LearnAdapter class
    public LearnAdapter(Context context, ArrayList<Learn> learns) {
        mLearns = learns;
        this.context = context;
    }

    // Implement a ClickListener interface
    public interface RecyclerViewClickListener {
        void onClick(View view, String id);
    }

    // Instantiate an instance from CountryViewHolder and return (will recycle the views)
    @NonNull
    @Override
    public LearnAdapter.LearnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Specify the layout that we are going to use for the view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_list_row, parent, false);
        return new LearnViewHolder(v, mListener);
    }

    // Set data for the list items on the view holder
    @Override
    public void onBindViewHolder(@NonNull LearnAdapter.LearnViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Learn learn = mLearns.get(position);
        holder.title.setText(learn.getTitle());
        holder.image.setImageResource(learn.getImage());
        holder.message.setText(learn.getMessage());
        holder.moreMessage.setText(learn.getMoreMessage());
        holder.itemView.setTag(learn.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoriesActivity.class);
                intent.putExtra("From", "MainActivity");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    // Return recyclerview list size equal to the number of card views
    @Override
    public int getItemCount() {
        return mLearns.size();
    }


    // Implement ViewHolder class for the adapter to hold each view and recycle
    public static class LearnViewHolder extends RecyclerView.ViewHolder {//implements View.OnClickListener {
        // Define the TextViews, ImageView, Listener
        public TextView title, message, moreMessage;
        public ImageView image;
        //private RecyclerViewClickListener listener;
        public LearnViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            //this.listener = listener;
            //itemView.setOnClickListener(this);
            // Finding each Androids XML/UI element
            title = itemView.findViewById(R.id.cv_title);
            image = itemView.findViewById(R.id.cv_image);
            message = itemView.findViewById(R.id.cv_message);
            moreMessage = itemView.findViewById(R.id.cv_moreMessage);
        }

//        @Override
//        public void onClick(View v) {
//            listener.onClick(v, (String) v.getTag());
//        }
    }


    public void setOnClick(OnItemClicked onClick) {
        this.onClick=onClick;
    }
}