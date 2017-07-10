package com.example.parktaeim.calendar_1;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by parktaeim on 2017. 7. 10..
 */

public class CustomAdapter extends PagerAdapter{
    LayoutInflater inflater;

    public CustomAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public int getCount(){
        //viewpager가 몇개인지 몇번 넘길껀지
        return 10;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position){
        View view = null;

        view = inflater.inflate(R.layout.viewpager_childview,null);

        container.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container,int position, Object object){
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View v, Object obj){
        return v==obj;
    }




}
