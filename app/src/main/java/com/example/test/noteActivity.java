package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import   java.text.SimpleDateFormat;

public class noteActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private EditText jinge;
    private EditText beizhu;
    private TextView mytext;
    private String leixing;
    private myDatabaseHelper dbhelper;
    Date dt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        spinner = (Spinner) findViewById(R.id.note_type);
        Button button1 =(Button) findViewById(R.id.button1);
        jinge = (EditText) findViewById(R.id.note_money);
        beizhu =(EditText) findViewById(R.id.note_add);
        dbhelper = new myDatabaseHelper(this,"book.db",null,2);
        //数据
        data_list = new ArrayList<String>();
        data_list.add("餐饮");
        data_list.add("服饰");
        data_list.add("学习");
        data_list.add("通讯");
        data_list.add("医疗");
        data_list.add("其他");

        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
                    spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                  //从适配器里面获取选择的文本，当然也可以从list中获取 list.get[i]
                                                  //以上方法中的int i，指的是选择了第几项
                                                  leixing = arr_adapter.getItem(i).toString();
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> adapterView) {

                                              }
                                          });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = jinge.getText().toString();
                String b = beizhu.getText().toString();
                String c=leixing;
                if(!a.equals("")&&!b.equals("")) {
                    java.util.Date writeTime = new java.util.Date();
                    SQLiteDatabase db = dbhelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日HH:mm:ss");
                    Date curDate =  new Date(System.currentTimeMillis());
                    String   str   =   formatter.format(curDate);
                    values.put("jine", a);
                    values.put("leixing", c);
                    values.put("time", str);
                    values.put("beizhu", b);
                    db.insert("book", null, values);
                    values.clear();
                    new AlertDialog.Builder(noteActivity.this)
                            .setTitle("添加成功")
                            .setMessage("添加成功")
                            .setPositiveButton("确认",null)
                            .show();
                    Intent in = new Intent();
                    in.setClass(noteActivity.this,
                            SecondActivity.class);
                    startActivity(in);
                    // 销毁当前activity
                    noteActivity.this.onDestroy();
                }
                else{
                    new AlertDialog.Builder(noteActivity.this)
                            .setTitle("Error")
                            .setMessage("信息不能为空")
                            .setPositiveButton("确认",null)
                            .show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}