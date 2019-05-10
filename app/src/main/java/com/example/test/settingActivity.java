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
public class settingActivity extends AppCompatActivity {
    private EditText canying ;
    private EditText learn;
    private EditText cloth ;
    private EditText medical ;
    private EditText other ;
    private EditText phone ;
    private EditText sum ;
    private EditText sum_bfb;
    private myDatabaseHelper dbhelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        canying =(EditText) findViewById(R.id.canying);
         learn = (EditText) findViewById(R.id.learn);
         cloth = (EditText) findViewById(R.id.cloth);
         medical =(EditText) findViewById(R.id.medical);
         other = (EditText) findViewById(R.id.other);
         phone = (EditText) findViewById(R.id.phone);
         sum = (EditText) findViewById(R.id.sum);
         sum_bfb =(EditText) findViewById(R.id.sum_bfb);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String a = canying.getText().toString();
                    String b =learn.getText().toString();
                    String c =cloth.getText().toString();
                    String d =medical.getText().toString();
                    String e =other.getText().toString();
                    String f =phone.getText().toString();
                    String ans =sum.getText().toString();
                    String ans1 =sum_bfb.getText().toString();
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("canying",a);
                values.put("learn",b);
                values.put("cloth",c);
                values.put("medical",d);
                values.put("other",e);
                values.put("phone",f);
                values.put("sum",ans);
                values.put("sum_bfb",ans1);
                db.insert("yujing", null, values);
                values.clear();
                new AlertDialog.Builder(settingActivity.this)
                        .setTitle("添加成功")
                        .setMessage("添加成功")
                        .setPositiveButton("确认",null)
                        .show();
                Intent in = new Intent();
                in.setClass(settingActivity.this,
                        SecondActivity.class);
                startActivity(in);
                // 销毁当前activity
                settingActivity.this.onDestroy();
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(settingActivity.this,
                        SecondActivity.class);
                startActivity(in);
                // 销毁当前activity
                settingActivity.this.onDestroy();
            };
        });
    }
}
