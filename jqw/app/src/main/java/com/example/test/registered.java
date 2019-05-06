package com.example.test;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormatSymbols;

public class registered extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText password_next;
    private EditText zhifu_name;
    private EditText zhifu_password;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.zhuce);
        Button button1=(Button) findViewById(R.id.button1);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        password_next=(EditText) findViewById(R.id.password_next);
        //zhifu_name=(EditText) findViewById(R.id.zhifu_name);
        //zhifu_password=(EditText) findViewById(R.id.zhifu_password);
       /* password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        password_next.setTransformationMethod(PasswordTransformationMethod.getInstance());
        zhifu_password.setTransformationMethod(PasswordTransformationMethod.getInstance());*/
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=username.getText().toString();
                String b=password.getText().toString();
                String c=password_next.getText().toString();
/*                String d=zhifu_name.getText().toString();
                String e=zhifu_password.getText().toString();*/
                if(a.equals("")||b.equals("")||c.equals("")){
                    new AlertDialog.Builder(registered.this)
                            .setTitle("Error")
                            .setMessage("注册信息不能为空")
                            .setPositiveButton("确认",null)
                            .show();
                    username.getText().clear();
                    password.getText().clear();
                    password_next.getText().clear();
                    zhifu_name.getText().clear();
                    zhifu_password.getText().clear();
                }
                else {
                    if(!b.equals(c)){
                        new AlertDialog.Builder(registered.this)
                                .setTitle("Error")
                                .setMessage("两次密码不一致")
                                .setPositiveButton("确认",null)
                                .show();
                        password.getText().clear();
                        password_next.getText().clear();
                        /*zhifu_name.getText().clear();
                        zhifu_password.getText().clear();*/
                    }
                    else{
                        if(addUser(a,b)){
                            new AlertDialog.Builder(registered.this)
                                    .setTitle("注册成功")
                                    .setMessage("注册成功")
                                    .setPositiveButton("确认",null)
                                    .show();
                            Intent in = new Intent();
                            in.setClass(registered.this,
                                    FirstActivity.class);
                            startActivity(in);
                            // 销毁当前activity
                            registered.this.onDestroy();
                        }
                        else {
                            new AlertDialog.Builder(registered.this)
                                    .setTitle("Error")
                                    .setMessage("注册失败")
                                    .setPositiveButton("确认",null)
                                    .show();
                            password.getText().clear();
                            password_next.getText().clear();
                          /*  zhifu_name.getText().clear();
                            zhifu_password.getText().clear();*/
                        }
                    }
                }
            }
        });

    }
    // 添加用户
    public Boolean addUser(String name, String password) {
        String str = "insert into tb_user values(?,?) ";
        FirstActivity main = new FirstActivity();
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()
                + "/test.dbs", null);
        main.db = db;
        try {
            db.execSQL(str, new String[] { name, password });
            return true;
        } catch (Exception e) {
            main.createDb();
        }
        return false;
    }
}
