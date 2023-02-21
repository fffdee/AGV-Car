package com.car;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class login extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("StaticFieldLeak")
    private static EditText editText;
    private CheckBox checkBox;

    private boolean flag = false;
    String result,path,content;

    private final static String[] PERMISSIONS=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int REQUEST_CODE_PERMISSION =1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.B1).setOnClickListener(this);
        editText = findViewById(R.id.E1);
        checkBox = findViewById(R.id.login);
        path = Environment.getExternalStorageDirectory().getPath();

        result = "";
        if(requestPermission(PERMISSIONS)){ //请求权限
            Toast.makeText(this,"已经获取权限",Toast.LENGTH_SHORT).show();
        }
        String name = "/storage/emulated/0/DCIM/ip2.txt";
        File file = new File(name);

        try {
            InputStream is = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content = line ;
                Log.d("gg",content);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.toString();
        }


        if(content.equals("0")) {
            checkBox.setChecked(false);

        }else{
            checkBox.setChecked(true);
            editText.setText(content);
        }


    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean valid=true;
        for(String permission:permissions){
            if(!shouldShowRequestPermissionRationale(permission)) valid=false; //某项权限被禁止并不再询问
        }
        if (valid) {
            // recreate();//如果某项权限没通过，刷新本页，会继续请求
        }else {
            //某项权限被禁止并不再询问，则转到程序的设置页

        }
    }

    private boolean requestPermission(String[] permissions){
        boolean valid=true;
        List<String> requestPermissions=new ArrayList<>();
        for(String permission:permissions){
            if(ActivityCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions.add(permission);
                valid=false;
            }
        }
        if(requestPermissions.size()>0){
            ActivityCompat.requestPermissions(this,requestPermissions.toArray(new String[requestPermissions.size()]),REQUEST_CODE_PERMISSION);
        }
        return valid;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.B1){

            if(checkBox.isChecked()) {
                flag = true;
                String name = "/storage/emulated/0/DCIM/ip2.txt";
                File file = new File(name);

                try {
                    FileOutputStream outStream = new FileOutputStream(file);
                    outStream.write(editText.getText().toString().getBytes());
                    outStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{
                flag = false;
                String name = "/storage/emulated/0/DCIM/ip2.txt";
                File file = new File(name);

                try {
                    FileOutputStream outStream = new FileOutputStream(file);
                    String nola = "0";
                    outStream.write(nola.getBytes() );
                    outStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }


            Intent intent = new Intent(login.this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("IP",editText.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);

        }
    }
}