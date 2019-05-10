package com.example.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class clothActivity extends AppCompatActivity {
    private int lastPress = 0;
    private boolean delState = false;
    private List<String> curList = new ArrayList<>();
    private myDatabaseHelper dbhelper ;
    private List<note> noteList;
    private ListView curListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                clothActivity.this,android.R.layout.simple_list_item_1);
        ListView listView=(ListView)findViewById(R.id.list_view_cloth);
        listView.setAdapter(adapter);
        dbhelper = new myDatabaseHelper(this, "book.db", null, 2);
        noteList = new ArrayList<clothActivity.note>();
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("book", null, "leixing=?",new String[]{"服饰"} , null, null, null);
        while (cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndex("leixing"));
            int  miney = cursor.getInt(cursor.getColumnIndex("jine"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String remark = cursor.getString(cursor.getColumnIndex("beizhu"));
            note p=new note(date,miney,remark,time);
            noteList.add(p);
        }
        ListView lv = (ListView) findViewById(R.id.list_view_cloth);
        ArrayAdapter<note> adapter1 = new ArrayAdapter<note>(
                clothActivity.this,android.R.layout.simple_list_item_1,noteList
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
    /*public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.toolbar,menu);
    return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add:

            break;
            case R.id.delete:

                break;
            default:
        }
        return true;
    }*/
}
