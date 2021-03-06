package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Donate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        // Method for handling Donate button
        Button donate = findViewById(R.id.btn_donate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("https://donations.redcross.org.au/donate-winepw"));
                startActivity(intent);
            }
        });

        // Method for handling Back button
        ImageButton back = findViewById(R.id.btn_ccback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Donate.this, UpdatedLearnActivity.class);
                intent.putExtra("From", "Donate");
                startActivity(intent);
            }
        });

        TextView supportMessage = findViewById(R.id.tv_ccmsg);
        supportMessage.setText("We’re committed to working respectfully and in partnership with Australia’s First Nations peoples.\n" +
                "\n" +
                "We recognise the importance of maintaining and re-establishing connections to culture, country and kinship to supporting Aboriginal and Torres Strait Islander people’s health and wellbeing.\n" +
                "\n" +
                "We design programs with Aboriginal and Torres Strait Islander peoples: always aiming to empower people to pursue their goals, and to be culturally appropriate and accessible.");
        YouTubePlayerView youTubePlayerView = findViewById(R.id.climatechangevideo);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = "YFXAn7qLeyY";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }
}