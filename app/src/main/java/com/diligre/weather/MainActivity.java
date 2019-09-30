package com.diligre.weather;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button re;
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        re = (Button) findViewById(R.id.but_add);
        re.setOnClickListener(this);
        mResult = findViewById(R.id.textview);
        new MyTask().execute();

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.but_add:
                Intent intent = new Intent(this, Second.class);
                intent.putExtra("json", mResult.getText().toString());
                startActivity(intent);
                break;
                default:
                    break;
        }
    }
    class MyTask extends AsyncTask<Void,Void,Void>{
        ProgressBar progressBar;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = (ProgressBar) findViewById(R.id.pb);
            progressBar.setMax(15);
            progressBar.setProgress(0);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            OkHttpClient client = new OkHttpClient();
            String url = "https://api.github.com/users/1";

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        final String myResponse = response.body().string();
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mResult.setText(myResponse);
                            }
                        });
                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
        }
    }
}
