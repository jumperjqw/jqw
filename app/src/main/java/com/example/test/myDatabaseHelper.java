package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDatabaseHelper extends SQLiteOpenHelper {
    public static final String create_book ="create table book ("
            + "jine integer," +"leixing text,"+"time text,"+"beizhu text)";
    public static final  String create_yujing = "create table yujing ("
            + "canying integer,clonth integer,tongxun integer,learn integer,medical integer,other integer,sum integer,sum_bfb integer)";
    private Context mcontext;
    public myDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mcontext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(create_book);db.execSQL(create_yujing);
    }
    public void onUpgrade (SQLiteDatabase db,int oldversion,int newversion){
        db.execSQL("drop table if exists book");
        onCreate(db);
    }


}