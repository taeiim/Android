package com.example.parktaeim.freeboard_2.Activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.parktaeim.freeboard_2.Adapter.postListViewAdapter;
import com.example.parktaeim.freeboard_2.R;

public class MainActivity extends AppCompatActivity {

    private ListView mListView = null;
    private com.example.parktaeim.freeboard_2.Adapter.postListViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.postsListView);

        mAdapter = new postListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        //이미지 있을때
        mAdapter.addItem(getResources().getDrawable(R.drawable.thumbnail,null),
                "오늘 축제 꿀잼~~~",
                "박태임","2017.08.07","18");
        //이미지 null 일때
        mAdapter.addItem(null, "오늘 축제 꿀잼~~~", "박태임","2017.08.07","18");


//        mListView.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
//                ListData mData = mAdapter.mListData.get(position);
//                Toast.makeText(MainActivity.this, mData.mTitle, Toast.LENGTH_SHORT).show();
//            }
//        });


    }




}
