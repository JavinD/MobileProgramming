package com.hfad.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {

    private int sec = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if (savedInstanceState != null){
            sec = savedInstanceState.getInt("sec");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState) {
        savedInstanceState.putInt("sec", sec);
        savedInstanceState.putBoolean("running", running);
    }

    public void onClickStart (View view){
        running = true;
    }

    public void onClickStop (View view){
        running = false;
    }

    public void onClickReset (View view){
        sec = 0;
        running = false;
    }

    private void runTimer () {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = sec/3600;
                int min = (sec%3600)/60;
                int secs = sec%60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hour, min, secs);
                timeView.setText(time);

                if (running) {
                    sec++;
                }
                handler.postDelayed(this, 1000);
            }
        });


    }
}