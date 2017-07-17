package com.example.parktaeim.jsonarrayparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText idEdit;
    EditText pwEdit;
    Button toServerBtn;
    Button toClientBtn;
    TextView JsonTextView;
    TextView ParsingTextView;

    StringBuffer str = new StringBuffer();

    ArrayList<String> idList = new ArrayList<String>();
    ArrayList<String> pwList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEdit = (EditText) findViewById(R.id.editID);
        pwEdit = (EditText) findViewById(R.id.editPW);
        toServerBtn = (Button) findViewById(R.id.loginBtn);
        toClientBtn = (Button) findViewById(R.id.parsingBtn);
        JsonTextView = (TextView) findViewById(R.id.textViewJson);
        ParsingTextView = (TextView) findViewById(R.id.textViewParsing);

        toServerBtn.setOnClickListener(btnClickListener);
        toClientBtn.setOnClickListener(btnClickListener);
    }

    View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.loginBtn:
                    //[{"id":"ti5356","pw":"ti0850"} , {"id":"abcde123","pw":"123123123"} ]
                    String start = "[";
                    String end = "]";

                    //StringBuffer 자료형의 append 메소드를 사용해서 계속해서 문자열을 추가
                    if(!str.toString().equals("")){
                        str.append(",");
                    }

                    //{"id":"","pw":""}
                    String ob="{"+"\"id\""+":"+"\""+idEdit.getText().toString()+"\""+","
                            + "\"pw\""+":"+"\""+pwEdit.getText().toString()+"\""+"}";

                    str.append(ob);

                    JsonTextView.setText(start+str+end);
                    break;

                case R.id.parsingBtn:
                    try {
                        JSONArray jsonArray = new JSONArray(JsonTextView.getText().toString());
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            idList.add(jsonObject.getString("id"));
                            pwList.add(jsonObject.getString("pw"));

                        }

                        ParsingTextView.setText(idList+"\n"+pwList);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };
}
