package com.example.a3605firstscientists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class ClimateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate);

        //Set TextView
        TextView climatemessage = findViewById(R.id.tv_ccmsg);
        climatemessage.setText("Every day at Australian Red Cross, we witness the human impacts of " +
                "climate-related disasters that seem to target the already poor and marginalised " +
                "communities. The World Disasters Report (2020) highlights the significant " +
                "challenge of climate change to humanity, recognising the unprecedented 2019-2020 " +
                "bushfire season coupled with the exposure of First Nations communities in the " +
                "Northern Territory to some of Australia’s most severe climate incidents.\n" +
                "\n" +
                "We need to educate ourselves on the facts about climate change, NOT the opinions. " +
                "So please, read our World Disasters Report (2020) and become more informed on the " +
                "impending danger that climate change introduces. \n");

        // Method for handling PDF link
        TextView link = findViewById(R.id.tv_cclink);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("https://www.redcross.org.au/getmedia/3b02a528-ae91-481d-bcbb-9b8ef3a70364/20201113-WorldDisasters-Full-FINAL_1.pdf.aspx"));
                startActivity(intent);
            }
        });

        // Method for youtube video
        YouTubePlayerView youTubePlayerView = findViewById(R.id.climatechangevideo);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = "sA69GnNYwx8";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        // Method for handling Back button
        ImageButton back = findViewById(R.id.btn_ccback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClimateActivity.this, ActionPlanActivity.class);
                intent.putExtra("From", "ClimateActivity");
                startActivity(intent);
            }
        });

    }
}