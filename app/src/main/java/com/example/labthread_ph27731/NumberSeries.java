package com.example.labthread_ph27731;

import android.os.Handler;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;


class PrimeSeries implements Runnable {
    private int n;
    private TextView tv;

    private ExecutorService ex;
    private Handler handler;

    public PrimeSeries(Handler refresh, int n, TextView tv) {
        this.n = n;
        this.tv = tv;
        handler = refresh;

    }

    @Override
    public void run() {
        String str = "";
        // Tìm dãy số nguyên tố
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                str += " " + i;
            }
        }
        String finalStr = str;
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv.setText(finalStr);

            }
        });

    }


    // Kiểm tra số nguyên tố
    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class PerfectSeries implements Runnable {
    private int n;
    private TextView tv;
    private Handler ex;

    public PerfectSeries(Handler refresh, int n, TextView tv) {
        this.n = n;
        this.tv = tv;
        this.ex = refresh;
    }

    @Override
    public void run() {
        String str = "";
        // Tìm dãy số hoàn hảo
        for (int i = 2; i <= n; i++) {
            if (isPerfect(i)) {
                str += " " + i;
            }
        }
        String finalStr = str;
        ex.post(new Runnable() {
            @Override
            public void run() {
                tv.setText(finalStr);
            }
        });


    }

    // Kiểm tra số hoàn hảo
    private boolean isPerfect(int num) {
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }
        return sum == num;
    }
}

class LoveSeries implements Runnable {
    private int n;
    private TextView tv;

    private Handler ex;

    public LoveSeries(Handler refresh, int n, TextView tv) {
        this.n = n;
        this.tv = tv;
        this.ex = refresh;
    }

    @Override
    public void run() {
        String str = "";
        // Tìm cặp số tình yêu
        for (int i = 2; i <= n; i++) {
            int sum = getSumOfDivisors(i);
            if (sum > i && sum <= n && getSumOfDivisors(sum) == i) {
                str += " (" + i + ", " + sum + ") ";
            }
        }
        String finalStr = str;
        ex.post(new Runnable() {
            @Override
            public void run() {
                tv.setText(finalStr);

            }
        });

    }

    // Tính tổng các ước số dương của một số
    private int getSumOfDivisors(int num) {
        int sum = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum;
    }
}