package com.example.test;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//注册
public class registered extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText password_next;
    private EditText zhifu_name;
    private EditText zhifu_password;
    private ImageButton touxiang;
    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/myHead/";// sd路径
    private Context mContext;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.register);
        Button button1=(Button) findViewById(R.id.button1);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        password_next=(EditText) findViewById(R.id.password_next);
        //zhifu_name=(EditText) findViewById(R.id.zhifu_name);
        //zhifu_password=(EditText) findViewById(R.id.zhifu_password);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        password_next.setTransformationMethod(PasswordTransformationMethod.getInstance());
        //zhifu_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=username.getText().toString();
                String b=password.getText().toString();
                String c=password_next.getText().toString();
                if(a.equals("")||b.equals("")||c.equals("")){
                    new AlertDialog.Builder(registered.this)
                            .setTitle("Error")
                            .setMessage("注册信息不能为空")
                            .setPositiveButton("确认",null)
                            .show();
                    username.getText().clear();
                    password.getText().clear();
                    password_next.getText().clear();
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


       // 点击头像进行更换头像

        touxiang.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.touxiang:// 更换头像
                    showTypeDialog();
                    break;
            }
        }
    });


   /* private void initView() {
        LL01=(LinearLayout)getActivity().findViewById(R.id.LL01);
        LL02=(LinearLayout)getActivity().findViewById(R.id.LL02);
        LL03=(LinearLayout)getActivity().findViewById(R.id.LL03);*/

        touxiang = (ImageButton)getActivity().findViewById(R.id.touxiang);
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            touxiang.setImageDrawable(drawable);
        } else {

              //如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中

        }
    private void showTypeDialog() {
        //显示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getActivity(), R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                //打开文件
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);// 保存在SD卡中
                        touxiang.setImageBitmap(head);// 用ImageButton显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



