package com.example.parktaeim.freeboard_2.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.parktaeim.freeboard_2.Data.PostsData;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2017. 8. 10..
 */

public class postListViewAdapter extends BaseAdapter {
    private Context mContext = null;
    private ArrayList<PostsData> mPostsData = new ArrayList<>();

    public postListViewAdapter(Context mContext){
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mPostsData.size();
    }

    @Override
    public Object getItem(int position) {
        return mPostsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
