package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class foodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
    }

    public class note{
        private String date;
        private float miney;
        private String remark;
        public note(String date,float money,String remark)
        {
            this.date=date;
            this.miney=money;
            this.remark=remark
        }
    }
}
