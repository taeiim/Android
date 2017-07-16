package com.example.parktaeim.autologin;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText idEdit,pwEdit;
    Button loginBtn;
    CheckBox autoLoginCheck;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean loginChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEdit = (EditText) findViewById(R.id.editID);
        pwEdit = (EditText) findViewById(R.id.editPW);
        loginBtn = (Button) findViewById(R.id.btnLogin);
        autoLoginCheck = (CheckBox) findViewById(R.id.checkAutoLogin);

        pref = getSharedPreferences("pref",0);
        editor = pref.edit();

        if(pref.getBoolean("autoLogin",false)) {
            idEdit.setText(pref.getString("id",""));
            pwEdit.setText(pref.getString("pw",""));
            autoLoginCheck.setChecked(true);

        }

        autoLoginCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //자동로그인 체크 되어 있을 때
                if(isChecked) {
                    //EditText로 받은 값 String으로
                    String id = idEdit.getText().toString();
                    String pw = pwEdit.getText().toString();
                    //SharedPreferences에 값 저장
                    editor.putString("id", id);
                    editor.putString("pw", pw);
                    editor.putBoolean("autoLogin", true);
                    editor.commit();

                } else {
                    //자동로그인 체그 안했으면 clear로 삭제 , 부분삭제는 editor.remove("ID");
                    editor.clear();
                    editor.commit();
                }
            }
        });


    }

//    private boolean loginIdentical(String id, String pw){
//        if(pref.getString("id","").equals(id) && pref.getString("pw","").equals(pw)){
//            return true;
//
//        }else if(pref.getString("id","").equals(null)){
//            Toast.makeText(this,"Please input ID",Toast.LENGTH_SHORT).show();
//            return false;
//
//        }else if(pref.getString("pw","").equals(null)){
//            Toast.makeText(this,"Please input Password",Toast.LENGTH_SHORT).show();
//            return false;
//
//        }else{
//            return false;
//        }
//
//    }


}
