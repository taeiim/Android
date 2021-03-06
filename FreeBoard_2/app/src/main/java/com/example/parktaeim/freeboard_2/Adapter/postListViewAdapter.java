package com.example.parktaeim.freeboard_2.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parktaeim.freeboard_2.Data.PostsData;
import com.example.parktaeim.freeboard_2.Activity.MainActivity;
import com.example.parktaeim.freeboard_2.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2017. 8. 10..
 */

public class postListViewAdapter extends BaseAdapter {
    private Context mContext = null;
    public ArrayList<PostsData> mPostsListData = new ArrayList<PostsData>();

    public postListViewAdapter(Context mContext){
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mPostsListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mPostsListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.postslist_item, null);

            holder.mTitle = (TextView) convertView.findViewById(R.id.titleTextView);
            holder.mWriter = (TextView) convertView.findViewById(R.id.writerTextView);
            holder.mDate = (TextView) convertView.findViewById(R.id.dateTextView);
            holder.mViews = (TextView) convertView.findViewById(R.id.viewsCountTextView);
            holder.mThumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        PostsData mData = mPostsListData.get(position);

        // 미리보기 이미지 있을 때랑 없을 때
        if (mData.thumbnail != null) {
            holder.mThumbnail.setVisibility(View.VISIBLE);
            holder.mThumbnail.setImageDrawable(mData.thumbnail);
        }else{
            holder.mThumbnail.setVisibility(View.GONE);
        }

        holder.mTitle.setText(mData.title);
        holder.mWriter.setText(mData.writer);
        holder.mDate.setText(mData.date);
        holder.mViews.setText(mData.views);

        return convertView;

    }

    private class ViewHolder{
        public TextView mTitle;
        public TextView mDate;
        public TextView mWriter;
        public TextView mViews;
        public ImageView mThumbnail;
    }


    //추가
    public void addItem(String mTitle, String mWriter, String mDate, String mViews, Drawable thumbnail ){
        PostsData addInfo = null;
        addInfo = new PostsData();

        addInfo.title = mTitle;
        addInfo.writer = mWriter;
        addInfo.date = mDate;
        addInfo.views = mViews;
        addInfo.thumbnail = thumbnail;

        mPostsListData.add(addInfo);
    }

//    public void remove(int position){
//        PostsData.remove(position);
//        dataChange();
//    }
//
//
//    public void dataChange(){
//        mAdapter.notifyDataSetChanged();
//    }


}
