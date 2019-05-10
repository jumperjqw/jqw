package com.example.test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class learnActivity extends AppCompatActivity {
    private myDatabaseHelper dbhelper ;
    private List<note> noteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        dbhelper = new myDatabaseHelper(this, "book.db", null, 2);
        noteList = new ArrayList<note>();
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("book", null, "leixing=?",new String[]{"学习"} , null, null, null);
        while (cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndex("leixing"));
            int  miney = cursor.getInt(cursor.getColumnIndex("jine"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String remark = cursor.getString(cursor.getColumnIndex("beizhu"));
            note p=new note(date,miney,remark,time);
            noteList.add(p);
        }
        ListView lv = (ListView) findViewById(R.id.list_view_cloth);
        ArrayAdapter<note> adapter1 = new ArrayAdapter<note> (
                learnActivity.this,android.R.layout.simple_list_item_1,noteList
        );
        lv.setAdapter(adapter1);
    }
    public class note{
        private String leixing;
        private int miney;
        private String remark;
        private String time;
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
