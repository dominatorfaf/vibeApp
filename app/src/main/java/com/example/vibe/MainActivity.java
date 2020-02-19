package com.example.vibe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gauravk.audiovisualizer.visualizer.CircleLineVisualizer;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    AnimationDrawable animationDrawable;
    MediaPlayer backgroundSong;
    CircleLineVisualizer circleLineVisualizer;
    int RECORD_AUDIO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        // Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.RECORD_AUDIO)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Permission is not granted
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.RECORD_AUDIO)) {
//                Toast.makeText(this, "Grant permission for the Waveform effect", Toast.LENGTH_LONG).show();
//            } else {
//                // No explanation needed; request the permission
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.READ_CONTACTS},
//                        RECORD_AUDIO_CODE);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    RECORD_AUDIO_CODE);

        }

        backgroundSong = MediaPlayer.create(this, R.raw.background_song);
        backgroundSong.start();

        circleLineVisualizer = findViewById(R.id.waveform);
        int audioSessionId = backgroundSong.getAudioSessionId();
        if (audioSessionId != -1)
            circleLineVisualizer.setAudioSessionId(audioSessionId);

        relativeLayout = findViewById(R.id.relativeLayout);

        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundSong.release();
        circleLineVisualizer.release();
        finish();
    }
}
