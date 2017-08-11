package com.example.parktaeim.freeboard_2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parktaeim.freeboard_2.R;

/**
 * Created by parktaeim on 2017. 8. 11..
 */

public class DetailActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpage);

        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView writerTextView = (TextView) findViewById(R.id.writerTextView);
        TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        TextView viewsTextView = (TextView) findViewById(R.id.viewsCountTextView);
        ImageView thumbnail = (ImageView) findViewById(R.id.thumbnail);

        Intent intent = getIntent();
        titleTextView.setText(intent.getStringExtra("title"));
        writerTextView.setText(intent.getStringExtra("writer"));
        dateTextView.setText(intent.getStringExtra("date"));
        viewsTextView.setText(intent.getStringExtra("views"));
        //thumbnail.setImageResource(intent.getIntExtra("thumbnail",0));
    }
}
