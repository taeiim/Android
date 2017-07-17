package com.example.parktaeim.jsonobjectparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText idEdit;
    EditText pwEdit;
    Button toServerBtn;
    Button toClientBtn;
    TextView JsonTextView;
    TextView ParsingTextView;

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
                    //{"id":"id값","pw":"pw"}
                    //json으로 변환
                    //큰따옴표를 문자로 인식하기 위해 \(역슬래쉬)를 앞에 붙혀준다.
                    String str = "{"+"\"id\""+":"+"\""+idEdit.getText().toString()+"\""+","
                            + "\"pw\""+":"+"\""+pwEdit.getText().toString()+"\""+"}";
                    //json으로 변환한 값을 TextView에 보여줌
                    JsonTextView.setText(str);
                    break;

                case R.id.parsingBtn:
                    try{
                        //JSONObject에 json형태로 JsonTextView에 있는 값을 저장
                        JSONObject jsonObject = new JSONObject(JsonTextView.getText().toString());
                        //key(id)로 value값을 id에 저장한다
                        String id = jsonObject.getString("id");
                        String pw = jsonObject.getString("pw");
                        //TextView에 보여줌
                        ParsingTextView.setText(id+"\n"+pw);

                    }catch(JSONException e){
                        //try-catch문으로 JSONException을 잡아주지 않으면 new JSONObject에서 빨간줄
                        e.printStackTrace();
                }


                    break;
            }
        }
    };
}
