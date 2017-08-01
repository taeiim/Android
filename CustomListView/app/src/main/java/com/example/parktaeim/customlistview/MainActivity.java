package com.example.parktaeim.customlistview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        dataSetting();
    }

    private void dataSetting(){
        MyAdapter myAdapter = new MyAdapter();

        for(int i=0;i<10;i++){
            myAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.profile_img),"name "+i, "contents "+i);

        }

        mListView.setAdapter(myAdapter);
    }
}
