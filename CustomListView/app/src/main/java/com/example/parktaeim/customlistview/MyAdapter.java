package com.example.parktaeim.customlistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2017. 8. 1..
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<MyItem> myItems = new ArrayList<>();

    @Override
    public int getCount() {
        return myItems.size();
    }

    @Override
    public Object getItem(int position) {
        return myItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_custom,viewGroup, false);

        }


        ImageView profile_img = (ImageView) view.findViewById(R.id.profile);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView comment = (TextView) view.findViewById(R.id.comment);

        profile_img.setBackground(new ShapeDrawable(new OvalShape()));
        profile_img.setClipToOutline(true);

        MyItem item = myItems.get(position);


        profile_img.setImageDrawable(item.getProfile());
        name.setText(item.getName());
        comment.setText(item.getComment());

        return view;
    }

    public void addItem(Drawable img , String name, String comment){
        MyItem myItem = new MyItem();

        myItem.setProfile(img);
        myItem.setName(name);
        myItem.setComment(comment);

        myItems.add(myItem);

    }

}

