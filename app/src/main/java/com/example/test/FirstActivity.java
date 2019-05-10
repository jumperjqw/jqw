package com.example.test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//活动（界面）1 登录注册
public class FirstActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    public static SQLiteDatabase db;
   @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button button1=(Button) findViewById(R.id.button1);//获取按钮Id登录
       username=(EditText) findViewById(R.id.edit1);
       password=(EditText) findViewById(R.id.edit2);
       password.setTransformationMethod(PasswordTransformationMethod.getInstance());
       Button button2=(Button) findViewById(R.id.button2);//获取注册id
       //建立sqlite数据库
       db = SQLiteDatabase.openOrCreateDatabase(FirstActivity.this.getFilesDir().toString()
               + "/test.dbs", null);
       button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String a=username.getText().toString();
                           String b=password.getText().toString();
                           if(a.equals("")||b.equals("")){
                               new AlertDialog.Builder(FirstActivity.this)
                                       .setTitle("Error")
                                       .setMessage("账号或密码不能为空")
                                       .setPositiveButton("确认",null)
                                       .show();
                               username.getText().clear();
                               password.getText().clear();
                           }
                           else {
                               try{
                                   String str = "select * from tb_user where name=? and password=?";
                                   Cursor cursor=db.rawQuery(str,new String[]{a,b});
                                   if(cursor.getCount()<=0){
                                       new AlertDialog.Builder(FirstActivity.this)
                                               .setTitle("Error")
                                               .setMessage("账号或密码错误！")
                                               .setPositiveButton("确认",null)
                                               .show();
                                   }
                                   else {
                                       new AlertDialog.Builder(FirstActivity.this)
                                               .setTitle("Toast")
                                               .setMessage("登录成功")
                                               .setPositiveButton("确认",null)
                                               .show();

                                       Intent intent = new Intent();
                                       intent.setClass(FirstActivity.this, SecondActivity.class);
                           startActivity(intent);
                           FirstActivity.this.onDestroy();
                       }
                   }catch(SQLiteException e){
                       createDb();
                   }
               }
           }
       });
       button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(FirstActivity.this, registered.class);
               //intent.setClass(FirstActivity.this, registered.class);
               startActivity(intent);
               //FirstActivity.this.onDestroy();
           }
       });
       }
    @Override
    protected  void onDestroy() {
        super.onDestroy();
        db.close();
    }
    public void createDb() {
        db.execSQL("create table tb_user( name varchar(30) primary key,password varchar(30))");
    }
}

