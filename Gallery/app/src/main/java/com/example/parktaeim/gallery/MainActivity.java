package com.example.parktaeim.gallery;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Gallery");

        Gallery gallery = (Gallery) findViewById(R.id.gallery);


        MyGalleryAdapter galleryAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galleryAdapter);

    }

    public class MyGalleryAdapter extends BaseAdapter{
        Context context;

        //이미지파일의 ID를 배열로 저장
        Integer[] imageID = {
                R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,
                R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10
        };

        public MyGalleryAdapter(Context c){
            context =c;
        }

        @Override
        public int getCount() {
            return imageID.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(100,150));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5,5,5,5);

            final int pos = position;
            imageview.setOnTouchListener(new View.OnTouchListener(){
                public boolean onTouch(View v, MotionEvent event){
                    ImageView imgView = (ImageView) findViewById(R.id.imageView);
                    imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imgView.setImageResource(imageID[pos]);
                    return false;
                }
            });
            return imageview;
        }


    }
}
