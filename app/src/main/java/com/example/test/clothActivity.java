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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(
                clothActivity.this,android.R.layout.simple_list_item_1);
        ListView listView=(ListView)findViewById(R.id.list_view_cloth);
        listView.setAdapter(adapter);
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
