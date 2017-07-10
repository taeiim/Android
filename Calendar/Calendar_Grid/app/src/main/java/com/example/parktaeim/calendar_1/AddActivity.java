package com.example.parktaeim.calendar_1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 * Created by parktaeim on 2017. 7. 7..
 */

public class AddActivity extends Activity {
    int year, month, day, hour, minute;
    TextView TxtStartDate;
    TextView TxtFinishDate;
    TextView TxtStartTime;
    TextView TxtFinishTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        TxtStartDate = (TextView) findViewById(R.id.startDateTextView);
        TxtFinishDate = (TextView) findViewById(R.id.finishDateTextView);
        TxtStartTime = (TextView) findViewById(R.id.startTimeTextView);
        TxtFinishTime = (TextView) findViewById(R.id.finishTimeTextView);


        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);


        Button cancelBtn = (Button) findViewById(R.id.cancelButton);
        Button okBtn = (Button) findViewById(R.id.okButton);

        //취소 버튼 누르면 캘린더 있는 페이지로 (뒤로 가기)
        cancelBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

        //ok버튼 누르면 캘린더 있는 페이지로
        okBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    //시작,종료 날짜와 시작,종료 시간 설정하는 버튼 누르면 각각 DatePickerDialog, TimePickerDialog 띄워주기
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.startDatePickerButton:
                new DatePickerDialog(AddActivity.this, startDateSetListener, year, month, day).show();
                break;
            case R.id.startTimePickerButton:
                new TimePickerDialog(AddActivity.this, startTimeSetListner, hour, minute, false).show();
                break;
            case R.id.finishDatePickerButton:
                new DatePickerDialog(AddActivity.this, finishDateSetListener, year, month, day).show();
                break;
            case R.id.finishTimePickerButton:
                new TimePickerDialog(AddActivity.this, finishTimeSetListner, hour, minute, false).show();
                break;

        }
    }

    //일정 시작 날짜 설정, textView에 띄워주기
    DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int myear, int monthOfYear, int dayOfMonth) {
            String msg = String.format("%d / %d / %d ", year, monthOfYear + 1, dayOfMonth);
            TxtStartDate.setText(msg);
        }
    };

    //일정 시작 시간
    TimePickerDialog.OnTimeSetListener startTimeSetListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int mMinute) {
            String msg = String.format("%d : %d ", hourOfDay, minute);
            TxtStartTime.setText(msg);
        }
    };

    //일정 종료 날짜
    DatePickerDialog.OnDateSetListener finishDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int myear, int monthOfYear, int dayOfMonth) {
            String msg = String.format("%d / %d / %d ", year, monthOfYear + 1, dayOfMonth);
            TxtFinishDate.setText(msg);
        }
    };

    //일정 종료 시간
    TimePickerDialog.OnTimeSetListener finishTimeSetListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int mMinute) {
            String msg = String.format("%d : %d ", hourOfDay, minute);
            TxtFinishTime.setText(msg);
        }
    };


}

