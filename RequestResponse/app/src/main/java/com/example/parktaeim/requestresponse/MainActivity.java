package com.example.parktaeim.requestresponse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AQuery aQuery;
    private Map parms;
    private List<Notice> notices;

    int num=1;

    private static String url = "http://dsm2015.cafe24.com/post/school/notice/list";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parms=new HashMap<Object,String>();

//        String id;
//
//        parms.put("id",id);
//
//

        aQuery=new AQuery(this);
        aQuery.ajax(url + "?page=" + num, String.class, new AjaxCallback<String>(){

            @Override
            public void callback(String url, String object, AjaxStatus status) {
                Toast.makeText(getApplicationContext(),object,Toast.LENGTH_SHORT).show();
            }
        });
    }


    public List<Notice> getParserJson(JSONArray jsonArray){
        notices=new ArrayList<>();
        
        for(int i=0;i<jsonArray.length();i++){

            // name
            // writer
            // date
            // add new Notice();
        }



        return notices;
    }
}
