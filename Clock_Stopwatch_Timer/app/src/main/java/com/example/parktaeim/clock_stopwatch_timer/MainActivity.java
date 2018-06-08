package com.example.parktaeim.clock_stopwatch_timer;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final long TIMER_START_TIME = 900000;

    Button clockBtn;
    Button stopwatchBtn;
    Button timerBtn;
    Button startStopBtn;
    Button resetBtn;
    TextView timeTv;
    LinearLayout startStopResetBtnLayout;

    int mainType = 0;

    //stopwatch
    boolean isStopwatchRun;
    long stopwatch_startTime = 0L, stopwatch_time = 0L, stopwatch_timeSwapBuff = 0L, stopwatch_updateTime = 0L;
    Handler stopwatchHandler = new Handler();

    //timer
    long timer_time = TIMER_START_TIME;
    boolean isTimerRun = false;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        clock();

        clockBtn.setOnClickListener(v -> clock());
        stopwatchBtn.setOnClickListener(v -> stopWatch());
        timerBtn.setOnClickListener(v -> timer());

    }

    private void clock() {
        mainType = 0;
        startStopResetBtnLayout.setVisibility(View.GONE);
        Thread clockThread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Calendar calendar = Calendar.getInstance(); // 칼렌다 변수
                            int hour = calendar.get(Calendar.HOUR_OF_DAY); // 시
                            int minute = calendar.get(Calendar.MINUTE); // 분
                            int second = calendar.get(Calendar.SECOND); // 초
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (mainType == 0) {
                                        String printTimeStr = String.format("%02d:%02d:%02d", hour,minute,second);
                                        timeTv.setText(printTimeStr);
                                    }
                                }
                            });
                        }
                    });
                    try {
                        Thread.sleep(1000); // 1000 ms = 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        clockThread.start();
    }

    Runnable updateStopWatchThread = new Runnable() {
        @Override
        public void run() {
            stopwatch_time = SystemClock.uptimeMillis() - stopwatch_startTime;
            stopwatch_updateTime = stopwatch_timeSwapBuff + stopwatch_time;

            String printTimeStr = String.format("%02d:%02d.%02d", stopwatch_updateTime / 1000 / 60, (stopwatch_updateTime / 1000) % 60, (stopwatch_updateTime % 1000) / 10);
            if (mainType == 1) timeTv.setText(printTimeStr);

            stopwatchHandler.post(this);
        }
    };

    private void stopWatch() {
        mainType = 1;
        startStopResetBtnLayout.setVisibility(View.VISIBLE);
        timeTv.setText("00:00:00");

        startStopBtn.setOnClickListener(v -> {
            if (isStopwatchRun) {
                isStopwatchRun = false;
                startStopBtn.setText("시작");
                resetBtn.setVisibility(View.VISIBLE);

                stopwatch_timeSwapBuff += stopwatch_time;
                stopwatchHandler.removeCallbacks(updateStopWatchThread);

            } else if (isStopwatchRun == false) {
                isStopwatchRun = true;
                startStopBtn.setText("멈춤");
                resetBtn.setVisibility(View.GONE);

                stopwatch_startTime = SystemClock.uptimeMillis();
                stopwatchHandler.postDelayed(updateStopWatchThread, 0);
            }
        });

        resetBtn.setOnClickListener(v -> {
            isStopwatchRun = false;

            stopwatch_startTime = 0L;
            stopwatch_time = 0L;
            stopwatch_timeSwapBuff = 0L;
            stopwatch_updateTime = 0L;

            timeTv.setText("00:00:00");
        });

    }

    private void timer() {
        mainType = 2;
        startStopResetBtnLayout.setVisibility(View.VISIBLE);
        timer_time = TIMER_START_TIME;
        updateTimerTv();

        startStopBtn.setOnClickListener(v -> {
            if (isTimerRun) {
                isTimerRun = false;
                startStopBtn.setText("시작");
                resetBtn.setVisibility(View.VISIBLE);

                countDownTimer.cancel();

            } else if (isTimerRun == false) {
                isTimerRun = true;
                startStopBtn.setText("멈춤");
                resetBtn.setVisibility(View.GONE);

                countDownTimer = new CountDownTimer(timer_time, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timer_time = millisUntilFinished;
                        updateTimerTv();
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        });

        resetBtn.setOnClickListener(v -> {
            isTimerRun = false;
            timer_time = TIMER_START_TIME;
            updateTimerTv();
        });

    }

    private void updateTimerTv() {
        String printTimeStr = String.format("%02d:%02d.%02d", timer_time / 1000 / 60, (timer_time / 1000) % 60, (timer_time % 1000) / 10);
        if (mainType == 2) timeTv.setText(printTimeStr);
    }

    private void init() {
        clockBtn = (Button) findViewById(R.id.clockBtn);
        stopwatchBtn = (Button) findViewById(R.id.stopwatchBtn);
        timerBtn = (Button) findViewById(R.id.timerBtn);
        startStopBtn = (Button) findViewById(R.id.startStopBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        timeTv = (TextView) findViewById(R.id.timeTv);
        startStopResetBtnLayout = (LinearLayout) findViewById(R.id.startStopResetBtnLayout);
    }
}
