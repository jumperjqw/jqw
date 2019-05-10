package com.example.test;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class foodActivity extends AppCompatActivity {
    private myDatabaseHelper dbhelper;
    List<note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        dbhelper = new myDatabaseHelper(this, "book.db", null, 2);
        noteList = new ArrayList<note>();
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("book", null, "leixing=?",new String[]{"餐饮"} , null, null, null);
        while (cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndex("leixing"));
            int  miney = cursor.getInt(cursor.getColumnIndex("jine"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String remark = cursor.getString(cursor.getColumnIndex("beizhu"));
            note p=new note(date,miney,remark,time);
            noteList.add(p);
        }
        ListView lv = (ListView) findViewById(R.id.list_view_cloth);
        ArrayAdapter<note> adapter = new ArrayAdapter<note>(
                foodActivity.this,android.R.layout.simple_list_item_1,noteList
        );
        lv.setAdapter(adapter);
    }
    public class note{
        private String leixing;
        private int miney;
        private String remark;
        private String  time;
        public note(String date,int money,String remark,String time)
        {
            this.leixing=date;
            this.miney=money;
            this.remark=remark;
            this.time=time;
        }
        @Override
        public String toString() {
            return  leixing + "," + miney + ", " + time+ ", " + remark ;
        }
        public String getleixing(){
            return this.leixing;
        }
        public int getMiney(){
            return this.miney;
        }
        public String getRemark(){
            return this.remark;
        }
    }
}
