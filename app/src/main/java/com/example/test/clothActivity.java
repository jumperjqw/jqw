package com.example.test;

import android.content.Context;
import android.provider.ContactsContract;
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

    private ListView curListView;
    private CurAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(
                clothActivity.this,android.R.layout.simple_list_item_1);
        ListView listView=(ListView)findViewById(R.id.list_view_cloth);
        listView.setAdapter(adapter);
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

    public Class NoteAdapter extends ArrayAdapter<>{
        private int resourceId;
        public NoteAdapter(Context context,int textViewRessourceId;
        List<>objects){
            super(context,textViewRessourceId,objects);
            resourceId=textViewRessourceId;
        }
        @Override
                public View getView(int position,View convertView, ViewGroup parent){
            ContactsContract.CommonDataKinds.Note note=getItem(position);
            View view= LayoutInflater.from(getContext()).inflate(resourceId,pareent,false);
            TextView date=(TextView)view.findViewById(R.id.date1);
            TextView money=(TextView)view.findViewById(R.id.money1);
            TextView remarks=(TextView)view.findViewById(R.id.remarks1);

        }
//删除
        curListView = (ListView) findViewById(R.id.list_view_cloth);
        adapter = new CurAdapter();
        curListView.setAdapter(adapter);

        curListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (delState) {
                    if (lastPress < parent.getCount()) {
                        View delview = parent.getChildAt(lastPress).findViewById(R.id.linear_del);
                        if (null != delview) {
                            delview.setVisibility(View.GONE);
                        }
                    }
                    delState = false;
                    return;
                } else {
                    Log.d("click:", position + "");
                }
//                lastPress = position;
            }
        });
        curListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            private View delview;

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (lastPress < parent.getCount()) {
                    delview = parent.getChildAt(lastPress).findViewById(R.id.note_del);
                    if (null != delview) {
                        delview.setVisibility(View.GONE);
                    }
                }
                delview = view.findViewById(R.id.linear_del);
                delview.setVisibility(View.VISIBLE);
                delview.findViewById(R.id.note_del).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delview.setVisibility(View.GONE);
                        curList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                delview.findViewById(R.id.note_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delview.setVisibility(View.GONE);
                    }
                });
                lastPress = position;
                delState = true;
                return true;
            }
        });
    }
    private class CurAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return curList.size();
        }
        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
       @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = LayoutInflater.from(Context).inflate(R.layout.item_content_delete, null);
            }
            ((TextView) convertView.findViewById(R.id.)).setText(curList.get(position));
            return convertView;
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
