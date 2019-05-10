package com.example.test;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;
//主页
public class SecondActivity extends AppCompatActivity {
    private myDatabaseHelper dbhelper;
    private myDatabaseHelper db2;
    private TextView ans;
    private TextView jieyu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        dbhelper =new myDatabaseHelper(this,"book.db",null,2);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("book", null, null, null, null, null, null);
        int sum=0;
        while (cursor.moveToNext()) {
            int miney = cursor.getInt(cursor.getColumnIndex("jine"));
            sum+=miney;
        }
        String pay = "";
        pay = sum + "";
        ans = (TextView) findViewById(R.id.pay_data);
        ans.setText(pay);
        db2 =new myDatabaseHelper(this,"yujing.db",null,2);
        SQLiteDatabase dbs = db2.getWritableDatabase();
        Cursor cursor1 = dbs.query("yujing", null, null, null, null, null, null);
        int text =0;
        int text1= 0;
        while (cursor.moveToNext()) {
            text = cursor.getInt(cursor1.getColumnIndex("sum"));
            text1 = cursor.getInt(cursor1.getColumnIndex("sum_bfb"));
        }
        jieyu = (TextView) findViewById(R.id.cash_data);
        int flag=0;
        if(text > sum){
            if(text * text1 <= sum*100) flag=1;
            text -= sum;
        }
        else text =0;
        String pay_="";
        pay_ =text + "";
        jieyu.setText(pay);
        if(flag==1){
            new AlertDialog.Builder(SecondActivity.this)
                    .setTitle("Error")
                    .setMessage("已达到预警线！！！")
                    .setPositiveButton("确认",null)
                    .show();
        }
        Button button1 = (Button) findViewById(R.id.food_pic);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, foodActivity.class);
                startActivity(intent);
            }
        });
        Button button2 = (Button) findViewById(R.id.cloth_pic);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, clothActivity.class);
                startActivity(intent);
            }
        });
        Button button3 = (Button) findViewById(R.id.learn_pic);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, learnActivity.class);
                startActivity(intent);
            }
        });
        Button button4 = (Button) findViewById(R.id.phone_pic);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, phoneActivity.class);
                startActivity(intent);
            }
        });
        Button button5 = (Button) findViewById(R.id.medical_pic);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, medicalActivity.class);
                startActivity(intent);
            }
        });
        Button button6 = findViewById(R.id.other_pic);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, otherActivity.class);
                startActivity(intent);
            }
        });

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setCheckedItem(R.id.jilu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent = new Intent(SecondActivity.this, totalActivity.class);
                startActivity(intent);
                return true;
            }
        });

        navigationView.setCheckedItem(R.id.my);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent = new Intent(SecondActivity.this, inforActivity.class);
                startActivity(intent);
                return true;
            }
        });

        navigationView.setCheckedItem(R.id.setting);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent = new Intent(SecondActivity.this, settingActivity.class);
                startActivity(intent);
                return true;
            }
        });
        navigationView.setCheckedItem(R.id.tongji);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent = new Intent(SecondActivity.this, tongjiActivity.class);
                startActivity(intent);
                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, noteActivity.class);
                startActivity(intent);
            }
        });
    }
}


