package com.heineck.mycustomprogressbarsample;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PROGRESSBAR_TIME_MS  = 30000;
    private static final int PROGRESSBAR_INTERVAL_MS  = 50;

    private ProgressBar progressBar;
    private Button btnBegin;
    private TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        btnBegin = (Button)findViewById(R.id.btnBegin);
        txtTime = (TextView)findViewById(R.id.txtTime);


        //Initial max steps of progress bar
        progressBar.setMax(PROGRESSBAR_TIME_MS/PROGRESSBAR_INTERVAL_MS);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void onBtnBeginClicked(View v) {

        //Setting initial value to progress bar
        progressBar.setSecondaryProgress(0);

        //Setting initial value to time textview
        txtTime.setText(String.format("%d\"", PROGRESSBAR_TIME_MS / 1000));

        //Disable button to prevent more clicks
        btnBegin.setEnabled(false);

        new CountDownTimer(PROGRESSBAR_TIME_MS, PROGRESSBAR_INTERVAL_MS) {

            public void onTick(long millisUntilFinished) {

                //Setting current step to progress bar
                int progress = (int)(Math.ceil((PROGRESSBAR_TIME_MS - millisUntilFinished)/(double)PROGRESSBAR_INTERVAL_MS));
                progressBar.setSecondaryProgress(progress);

                //Updating value to time textview
                txtTime.setText(String.format("%d\"", millisUntilFinished / 1000));

            }

            public void onFinish() {

                //final value for the progress bar (100%)
                progressBar.setSecondaryProgress(PROGRESSBAR_TIME_MS);

                Toast.makeText(getApplicationContext(), "FINISHED TIME", Toast.LENGTH_SHORT).show();

                //Enable button again
                btnBegin.setEnabled(true);
            }
        }.start();



    }
}
