package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning; //records whether the stopwatch was running before onStop() method was called

    @Override
    //onCreate() method is the initialization place
    //it gets called after the activity has launched but before it starts running
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) { //retrieve the values of the seconds and running variables from the bundle
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        startTimer(); //when the activity is created, it will then calls startTimer() method to start the timer
    }

    @Override
    //onSaveInstaneState method is used to save the state of the variables before the activity is destroyed
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }

    @Override
    //onStart method runs when the activity is about to become visible
    //after the onStart method runs, the user can see the activity on the screen
    protected void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }
    @Override
    //onResume method runs when the activity is about to move to the foreground
    //this happens when activity gain its focus and about to start interacting with user
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    //onStop method runs then the activity stops being visible to the user
    //after the onStop method runs, the activity is no longer visible
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }
    @Override
    //onPause method runs when the activity stops being in the foreground
    //activity is still visible but doesn't have the focus
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view){
        running = true;
    }
    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view){
        running = false;
    }
    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    //starTime() method to to start the timer
    private void startTimer(){
        final TextView timeView  = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        //post() method is used to process the code immediately without delay
        //in this case, the method will pass a new Runnable so the Runnable will run immediately
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                //postDelayed() method runs the Runnable with a delay of specific milliseconds
                handler.postDelayed(this, 1000);
            }
        });
    }
}