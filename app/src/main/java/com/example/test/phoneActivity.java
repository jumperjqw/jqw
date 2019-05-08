package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class phoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
    }

    public class note{
        private String date;
        private float miney;
        private String remark;
        public note(String date,float money,String remark)
        {
            this.date=date;
            this.miney=money;
            this.remark=remark;
        }
    }
}
