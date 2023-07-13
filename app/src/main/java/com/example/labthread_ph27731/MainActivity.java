package com.example.labthread_ph27731;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNumber;
    private Button buttonGenerate;
    private TextView textViewPrimeResult;
    private TextView textViewPerfectResult;
    private TextView textViewAmicableResult;


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

                        if (editTextNumber.getText().toString().length() > 10) {

                            Toast toast = new Toast(getApplicationContext());
                            toast.setText("khong duoc nhạp nhieu");
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();
                            return;
                        }


                        int n = Integer.parseInt(String.valueOf(editTextNumber.getText()));
                        Thread t1 = new Thread(new PrimeSeries(refresh, n, textViewPrimeResult));
                        Thread t2 = new Thread(new PerfectSeries(refresh, n, textViewPerfectResult));
                        Thread t3 = new Thread(new LoveSeries(refresh, n, textViewAmicableResult));


                        t1.start();
                        t2.start();
                        t3.start();


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
    }


}