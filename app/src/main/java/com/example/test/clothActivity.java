package com.example.test;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class clothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(
                clothActivity.this,android.R.layout.simple_list_item_1,);
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
        public NoteAdapter(Context context,int textViewRessourceId,
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
