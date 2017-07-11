package com.example.parktaeim.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ArrayList<String>형으로 비어 있는 변수를 선언
        final ArrayList<String> arrayList = new ArrayList<String>();
        ListView list = (ListView) findViewById(R.id.listview);

        //this는 MainActivity의 instance
        //두번째 인자인 simple_list_item_1은 리스트에서 각 항목을 표시할 레이아웃
        //ArrayList<String>형 변수 arrayList를 ArrayAdapter()생성자 맨 마지막 파라미터로 사용
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        list.setAdapter(adapter);

        final EditText edtItem = (EditText) findViewById(R.id.editText);
        Button addBtn = (Button) findViewById(R.id.addButton);

        addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                arrayList.add(edtItem.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        //길게 눌렀을때(롱클릭) remove()메소드로 항목이 삭제
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            public boolean onItemLongClick(AdapterView<?> arg0,View arg1, int arg2,long arg3){
                arrayList.remove(arg2);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}
