package com.abirhossain.nsu.fall2020.cse486.sec01.lab09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressing;
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressing = findViewById(R.id.bar);
        startBtn = findViewById(R.id.startButton);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LabChallenge task = new LabChallenge(this);
                task.execute(10);

            }
        });


    }

    private class LabChallenge extends AsyncTask<Integer,Integer,String>  {
        private WeakReference<MainActivity> activityWeakReference;
        LabChallenge(View.OnClickListener activity) {
            activityWeakReference = new WeakReference<MainActivity>((MainActivity) activity);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.progressing.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++) {
                publishProgress((i * 100) / integers[0]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Finished!";
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.progressing.setProgress(values[0]);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            activity.progressing.setProgress(0);
            activity.progressing.setVisibility(View.INVISIBLE);
        }
    }
}
