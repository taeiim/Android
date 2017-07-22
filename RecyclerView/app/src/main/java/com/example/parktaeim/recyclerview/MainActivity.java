package com.example.parktaeim.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.StaggeredGridLayoutManager;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private RecyclerView lecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        initData();
    }

    /**
     * 레이아웃 초기화
     */
    private void initLayout(){

        lecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }

    /**
     * 데이터 초기화
     */
    private void initData(){

        List<Album> albumList = new ArrayList<Album>();

        for (int i =0; i<20; i ++){

            Album album = new Album();
            album.setTitle("어느 멋진 날");
            album.setArtist("정용");
            album.setImage(R.drawable.ic_launcher);
            albumList.add(album);
        }

        lecyclerView.setAdapter(new MyRecyclerAdapter(albumList,R.layout.row_album));
        lecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        lecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

}