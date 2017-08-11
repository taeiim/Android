package com.example.parktaeim.freeboard_2.Activity;

import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.parktaeim.freeboard_2.Adapter.postListViewAdapter;
import com.example.parktaeim.freeboard_2.Data.PostsData;
import com.example.parktaeim.freeboard_2.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

       // postsArrayList.add(new PostsData("오늘은 치킨이 먹고싶다","박태임","2017.08.15.","28",null));
        //이미지 있을때
        mAdapter.addItem("오늘 축제 꿀잼~~~", "박태임","2017.08.07","1",getResources().getDrawable(R.drawable.thumbnail,null));
        //이미지 null 일때
        mAdapter.addItem("오늘 축제 꿀잼~~~", "박태임","2017.08.07","18",null);


//        mListView.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
//                ListData mData = mAdapter.mListData.get(position);
//                Toast.makeText(MainActivity.this, mData.mTitle, Toast.LENGTH_SHORT).show();
//            }
//        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                PostsData mData = mAdapter.mPostsListData.get(position);
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);

                intent.putExtra("title",mData.title);
                intent.putExtra("writer",mData.writer);
                intent.putExtra("date",mData.date);
                intent.putExtra("views",mData.views);
                //intent.putExtra("thumbnail", (Parcelable) mData.thumbnail);

                startActivity(intent);
            }
        });



    }




}
