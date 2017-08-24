package com.example.parktaeim.requestresponse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AQuery aQuery;
    private Map params;
    private List<Notice> notices;

    int num=1;

    private static String url = "http://dsm2015.cafe24.com/post/school/notice/list";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //서버에 넘겨줄 데이터 넣을 HashMap
        params=new HashMap<Object,String>();

//        String id;
//
//        parms.put("id",id);
//
//
        //객체 생성
        aQuery=new AQuery(this);
        //aQuery.ajax(인자,  , 서버에 전달할 데이터 타입)

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
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                // name
                notices.add(jsonObject.getString("name"));
                // writer
                // date
                // add new Notice();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



        return notices;
    }
}
