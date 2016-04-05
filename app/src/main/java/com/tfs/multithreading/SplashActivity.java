package com.tfs.multithreading;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new asyncTask_waitAndGo().execute();
    }

    private class asyncTask_waitAndGo extends AsyncTask<Void, Integer, Void>
    {
        ProgressBar mProgress;
        TextView mTextView;
        @Override
        protected void onPreExecute() {
            mProgress = (ProgressBar) findViewById(R.id.splash_progressBar);
            mTextView = (TextView) findViewById(R.id.SplashProgressTextView);
            mProgress.setVisibility(View.VISIBLE);
            mProgress.setMax(10);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(500);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgress.setProgress(values[0]);
            mTextView.setText(values[0] * 10 + "%");

            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent();
            intent.setClass(SplashActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
            super.onPostExecute(aVoid);
        }
    }
    }
