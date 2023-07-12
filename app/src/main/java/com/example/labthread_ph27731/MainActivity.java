package com.example.labthread_ph27731;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNumber;
    private Button buttonGenerate;
    private TextView textViewPrimeResult;
    private TextView textViewPerfectResult;
    private TextView textViewAmicableResult;


    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findID();
        Handler refresh = new Handler(Looper.getMainLooper());
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int n = Integer.parseInt(String.valueOf(editTextNumber.getText()));
                        executorService = Executors.newFixedThreadPool(3);
                        Runnable primeThread = new PrimeSeries(refresh, n, textViewPrimeResult);
                        Runnable perfectThread = new PerfectSeries(refresh, n, textViewPerfectResult);
                        Runnable loveThread = new LoveSeries(refresh, n, textViewAmicableResult);
                        executorService.execute(primeThread);
                        executorService.execute(perfectThread);
                        executorService.execute(loveThread);

                    }
                });


            }
        });


    }

    void findID() {


        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        buttonGenerate = (Button) findViewById(R.id.buttonGenerate);
        textViewPrimeResult = (TextView) findViewById(R.id.textViewPrimeResult);
        textViewPerfectResult = (TextView) findViewById(R.id.textViewPerfectResult);
        textViewAmicableResult = (TextView) findViewById(R.id.textViewAmicableResult);

    }

    protected void onDestroy() {
        super.onDestroy();
        // Dừng ExecutorService khi không cần thiết nữa
        executorService.shutdown();
    }


}