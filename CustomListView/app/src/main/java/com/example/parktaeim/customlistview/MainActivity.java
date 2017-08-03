package com.example.parktaeim.customlistview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String> arrayList;
    private EditText commentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mListView = (ListView) findViewById(R.id.listView);

        dataSetting();
    }

    private void dataSetting(){
        final MyAdapter myAdapter = new MyAdapter();

        commentEdit=(EditText) findViewById(R.id.commentEditText);
        Button addBtn = (Button) findViewById(R.id.commentAddBtn);

        arrayList= new ArrayList<>();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newComment = commentEdit.getText().toString();
                arrayList.add(newComment);
                myAdapter.notifyDataSetChanged();
            }
        });
        for(int i=0;i<10;i++){
            myAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.profile_img),"name "+i, "contents "+i);

        }

        mListView.setAdapter(myAdapter);
    }
}
