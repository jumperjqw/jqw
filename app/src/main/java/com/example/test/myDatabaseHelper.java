package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDatabaseHelper extends SQLiteOpenHelper {
    public static final String create_book ="create table book ("
            + "jine integer," +"leixing text,"+"time integer,"+"beizhu text)";
    private Context mcontext;
    public myDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mcontext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(create_book);
    }
    public void onUpgrade (SQLiteDatabase db,int oldversion,int newversion){
        db.execSQL("drop table if exists book");
        onCreate(db);
    }


}