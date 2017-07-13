package com.example.parktaeim.implicitintent_1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mapBtn = (Button) findViewById(R.id.mapButton);
        Button webBtn = (Button) findViewById(R.id.webButton);
        Button telBtn = (Button) findViewById(R.id.telButton);


        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:37.563563, 126.975331"));
                startActivity(intent1);
            }
        });

        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com/"));
                startActivity(intent2);
            }
        });

        telBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people/"));
                startActivity(intent3);
            }
        });
    }
}
