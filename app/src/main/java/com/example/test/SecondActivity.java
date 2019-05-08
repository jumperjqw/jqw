package com.example.test;

import android.content.ClipData;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;
//主页
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
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


