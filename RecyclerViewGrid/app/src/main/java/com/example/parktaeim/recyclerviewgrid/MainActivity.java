package com.example.parktaeim.recyclerviewgrid;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostsAdapter adapter;
    private List<Post> postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        postsList = new ArrayList<>();
        adapter = new PostsAdapter(this, postsList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        preparePosts();

        try{
            Glide.with(this).load(R.drawable.image1).into((ImageView)findViewById(R.id.backdrop));
        }catch (Exception e){
            e.printStackTrace();
        }


    }



    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset ==0){
                    collapsingToolbar.setTitle("Gallery");
                    isShow=true;
                }
                else if(isShow){
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    private void preparePosts() {
        int [] img = new int[]{
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6,
                R.drawable.image7,
                R.drawable.image8,
                R.drawable.image9,
                R.drawable.image10,
                R.drawable.image11,
                R.drawable.image12,
                R.drawable.image13,
                R.drawable.image14,
                R.drawable.image15,
                R.drawable.image16,
                R.drawable.image17,
                R.drawable.image18,
                R.drawable.image19,
                R.drawable.image20

        };

        Post p = new Post("대마고 체육대회","퉁퉁이", img[0]);
        postsList.add(p);

        p = new Post("즐거운 축제","통통이", img[1]);
        postsList.add(p);

        p = new Post("떡볶이 먹고싶다","코알라", img[2]);
        postsList.add(p);

        p = new Post("여름싫어ㅠㅠ","겨울좋아ㅎㅅㅎ", img[3]);
        postsList.add(p);

        p = new Post("크리스마스","산타할아버지", img[4]);
        postsList.add(p);

        p = new Post("쇼미더머니","자치회", img[5]);
        postsList.add(p);

        p = new Post("대마고 짱","푸훕", img[6]);
        postsList.add(p);

        p = new Post("하이룽","앙뇽", img[7]);
        postsList.add(p);

        p = new Post("단합대회","못먹은짜장면", img[8]);
        postsList.add(p);

        p = new Post("퍼킹","퍼퍼퍼킹", img[9]);
        postsList.add(p);

        p = new Post("대마고 체육대회","퉁퉁이", img[10]);
        postsList.add(p);

        p = new Post("대마고 체육대회","퉁퉁이", img[11]);
        postsList.add(p);

        p = new Post("대마고 체육대회","퉁퉁이", img[12]);
        postsList.add(p);

        p = new Post("대마고 체육대회","퉁퉁이", img[13]);
        postsList.add(p);

        p = new Post("대마고 체육대회","퉁퉁이", img[14]);
        postsList.add(p);

        p = new Post("대마고 체육대회","퉁퉁이", img[15]);
        postsList.add(p);

        p = new Post("대마고 체육대회","퉁퉁이", img[16]);
        postsList.add(p);

        p = new Post("대마고 체육대회","퉁퉁이", img[17]);
        postsList.add(p);

        p = new Post("대마고 체육대회","퉁퉁이", img[18]);
        postsList.add(p);

        p = new Post("대마고 체육대회","퉁퉁이", img[19]);
        postsList.add(p);


        adapter.notifyDataSetChanged();

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration{
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount,int spacing,boolean includeEdge){
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        public void getItemOffsets(Rect outRect, View view,RecyclerView parent,RecyclerView.State state){
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if(includeEdge){
                outRect.left = spacing = column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if(position<spanCount){
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            }else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column+1) * spacing / spanCount;
                if(position>=spanCount){
                    outRect.top = spacing;
                }
            }
        }


    }

    private int dpToPx(int dp){
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }
}
