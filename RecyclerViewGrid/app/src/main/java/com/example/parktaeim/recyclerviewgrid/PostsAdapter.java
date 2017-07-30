package com.example.parktaeim.recyclerviewgrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by parktaeim on 2017. 7. 30..
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Post> postList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView title, writer;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            writer = (TextView) view.findViewById(R.id.writer);
            image = (ImageView) view.findViewById(R.id.image);
        }

    }

    public PostsAdapter(Context mContext, List<Post> postList){
        this.mContext = mContext;
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.posts_card,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.writer.setText("작성자 : "+post.getWriter());

        Glide.with(mContext).load(post.getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
