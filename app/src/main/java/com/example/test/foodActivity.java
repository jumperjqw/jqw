package com.example.test;

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
        dbhelper = new myDatabaseHelper(this, "book", null, 2);
        noteList = new ArrayList<note>();

    }
    public void Query_Data(View v) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("book", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndex("leixing"));
            String miney = cursor.getString(cursor.getColumnIndex("jine"));
            int time = cursor.getInt(cursor.getColumnIndex("time"));
            String remark = cursor.getString(cursor.getColumnIndex("beizhu"));
            note p=new note(date,miney,remark,time);
                noteList.add(p);
        }
        ListView lv = (ListView) findViewById(R.id.list_view_cloth);
        //设置适配器
        lv.setAdapter(new MyAapter());

    }
    //适配器类
    class MyAapter extends BaseAdapter {

        //获取集合中有多少条元素,由系统调用
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return noteList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        //由系统调用，返回一个view对象作为listview的条目
        /*
         * position：本次getView方法调用所返回的view对象在listView中处于第几个条目，position的值就为多少
         * */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(foodActivity.this);
            tv.setTextSize(18);
            //获取集合中的元素
            note s = noteList.get(position);
            tv.setText(s.toString());

            return tv;
        }
    }
    public class note{
        private String leixing;
        private String miney;
        private String remark;
        private int time;
        public note(String date,String money,String remark,int time)
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
    }
}
