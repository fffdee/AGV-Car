package com.car;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import android.os.VibrationEffect;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;


public class MainActivity extends Activity implements View.OnClickListener,View.OnTouchListener,View.OnLongClickListener {
    private final Handler handler2 = new Handler();
    private static final String TAG = "MainActivity::";
    private Handler handler;
    private ImageView imageView;
    private SeekBar seekBar1, seekBar2;
    private TextView textView1,textView2,textView3,textView4;
    private final int DOWNDLOAD = 1;
    private final int UP = 3;
    private final int DOWN = 4;
    private final int LEFT = 5;
    private final int RIGHT = 6;
    private final int STOP = 7;
    private final int DX = 8;
    private final int DY = 9;
    private final int GET1 = 10;
    private final int GET2 = 11;
    private final int STRMODE = 12;
    private final int MODE1 = 14;
    private final int MODE2 = 15;
    private final int MAPMODE = 16;
    private DatagramSocket socket;

    private static final int PhonePort = 12345;//手机端口号

    private DatagramPacket packet;

    private volatile boolean stopReceiver;

    String filePath = "/sdcard/AIUI/devices.txt";
    private Spinner spinner;
    int position2 ;
    int Dxval;
    int Dyval;
    String ip;
    int speed;
    DatagramSocket clientsocket;
    String hello = "hello";
    byte[] sendbuf = new byte[1024];
    byte[] receivedata = new byte[1024];
    //RockerView rockerView1, rockerView2;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.downloadFile).setOnClickListener(this);
        imageView = findViewById(R.id.img);
        seekBar1 = findViewById(R.id.sb1);
        seekBar2 = findViewById(R.id.sb2);
        handler2.post(task);
     //   seekBar4 = findViewById(R.id.sb4);
        textView1 = findViewById(R.id.T1);
        textView2 = findViewById(R.id.T2);
        textView3 = findViewById(R.id.T3);
        textView4 = findViewById(R.id.T4);
        findViewById(R.id.sb1).setOnTouchListener(this);
        findViewById(R.id.sb2).setOnTouchListener(this);

     //   findViewById(R.id.sb4).setOnTouchListener(this);
        findViewById(R.id.left).setOnTouchListener(this);
        findViewById(R.id.right).setOnTouchListener(this);
        findViewById(R.id.up).setOnTouchListener(this);
        findViewById(R.id.down).setOnTouchListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ip = bundle.getString("IP","");
        Toast.makeText(MainActivity.this,"获取IP:"+ip,Toast.LENGTH_SHORT).show();
        HandlerThread handlerThread = new HandlerThread("http");
        handlerThread.start();




        handler = new HttpHandler(handlerThread.getLooper());
        spinner= findViewById(R.id.modeSelect);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] value = getResources().getStringArray(R.array.spinner_content);
                Toast.makeText(MainActivity.this,"进入:"+position+value[position],Toast.LENGTH_SHORT).show();
                position2 = position;
                switch (position2){
                    case 0:
                        handler.sendEmptyMessage(STRMODE);
                        break;
                    case 1:
                        handler.sendEmptyMessage(MODE1);
                        break;
                    case 2:
                        handler.sendEmptyMessage(MODE2);
                        break;
                    case 3:
                        handler.sendEmptyMessage(MAPMODE);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        new Thread() {
//
//                public void run() {
//
//
//                }
//
//            }.start();
//

//        try {
//
//
//            while (true) {
//                clientsocket = new DatagramSocket(12345);
//
//                DatagramPacket recv_packet = new DatagramPacket(receivedata, receivedata.length);
//
//                Log.d("UDP", "S: Receiving...");
//
//                clientsocket.receive(recv_packet);
//
//                String rec_str = new String(recv_packet.getData());
//
//                textView1.setText(rec_str);
//
//                Log.d(" Received String ", rec_str);
//
//                InetAddress ipaddress = recv_packet.getAddress();
//
//                int port = recv_packet.getPort();
//
//                Log.d("IPAddress : ", ipaddress.toString());
//
//                Log.d(" Port : ", Integer.toString(port));
//
//            }
//
//
//
//        } catch (Exception e) {
//
//            Log.e("UDP", "S: Error", e);
//
//        }


    }

    private final Runnable task = new Runnable() {
        @SuppressLint("SetTextI18n")
        public void run() {
            handler2.postDelayed(this, 500);//设置延迟时间，此处是5秒
            System.out.println("OK~~~~~~~~~~~~~");
//            try {
//
//
//
//
//                while (true) {
//                    byte data[] = new byte[1024];
//                    DatagramPacket packet = new DatagramPacket(data, data.length);
//                    socket.receive(packet);
//                    String result = new String(packet.getData(), packet.getOffset(), packet.getLength());
//                    Log.d(" Received String ", result);
//
//                    //以下解析数据result 自行实现
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }


    };
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.downloadFile:
                handler.sendEmptyMessage(DOWNDLOAD);
                break;

            default:
                break;
        }
    }

    @SuppressLint({"NonConstantResourceId", "ClickableViewAccessibility"})
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {
            case R.id.right:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handler.sendEmptyMessage(RIGHT);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendEmptyMessage(STOP);
                }
                break;
            case R.id.left:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handler.sendEmptyMessage(LEFT);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendEmptyMessage(STOP);
                }
                break;
            case R.id.down:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handler.sendEmptyMessage(DOWN);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendEmptyMessage(STOP);
                }
                break;
            case R.id.up:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handler.sendEmptyMessage(UP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendEmptyMessage(STOP);
                }
                break;
            case R.id.sb1:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handler.sendEmptyMessage(DX);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendEmptyMessage(GET1);
                }

                break;
            case R.id.sb2:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handler.sendEmptyMessage(DY);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendEmptyMessage(GET2);
                }
                break;
//            case R.id.sb3:
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    handler.sendEmptyMessage(L);
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    handler.sendEmptyMessage(GET3);
//                }
//              break;
           /* case R.id.sb4:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handler.sendEmptyMessage(S);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendEmptyMessage(GET4);
                }*/




            default:
                break;

        }
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    private class HttpHandler extends Handler {
        public HttpHandler(Looper looper) {
            super(looper);


        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNDLOAD:
                    downloadFile();
                    break;
                case LEFT:
                    left();
                    break;
                case RIGHT:
                    right();
                    break;
                case UP:
                    up();
                    break;
                case DOWN:
                    down();
                    break;
                case STOP:
                    stop();
                    break;
                case DX:
                    dx();
                    break;
                case DY:
                    dy();
                    break;
                case GET1:
                    get1();
                    break;
                case GET2:
                    get2();
                    break;
                case STRMODE:
                    strmode();
                    break;
                case MODE1:
                    mode1();
                    break;
                case MODE2:
                    mode2();
                    break;
                case MAPMODE:
                    mapmode();
                    break;
                default:
                    break;
            }
        }




    }

    private void mode1() {
        textView3.setText("模式：神烦模式");
        String registerUrl = "http://"+ip+"/contorlM1";
        try
        {
            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200)
            {
                httpURLConnection.disconnect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        // Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void mode2() {
        textView3.setText("模式：静默模式");
        String registerUrl = "http://"+ip+"/contorlM2";
        try
        {
            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200)
            {
                httpURLConnection.disconnect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        // Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void mapmode() {
        textView3.setText("模式：建图模式");
        String registerUrl = "http://"+ip+"/contorlM3";
        try
        {
            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200)
            {
                httpURLConnection.disconnect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        // Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    private void strmode() {

        textView3.setText("模式：标准模式");
        String registerUrl = "http://"+ip+"/contorlM0";
        try
        {
            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200)
            {
                httpURLConnection.disconnect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        // Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    @SuppressLint("SetTextI18n")
    private void get1() {


        String registerUrl = "http://"+ip+"/control?GS" + Dxval;
        textView4.setText("前进:"+Dxval+" 转向:"+Dyval);
        try {

            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200) {

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @SuppressLint("SetTextI18n")
    private void get2() {


        String registerUrl = "http://"+ip+"/control?ZX" + Dyval;
        textView4.setText("前进:"+Dxval+" 转向:"+Dyval);

        try {

            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200) {

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void dy() {
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Dyval = seekBar2.getProgress();
                
            }
        });
    }

    private void dx() {
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Dxval = seekBar1.getProgress();

            }
        });
    }

    private void stop() {
        String registerUrl = "http://"+ip+"/stop?var=stop"+"&val="+1;
        try
        {
            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200)
            {
                httpURLConnection.disconnect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        // Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void left() {
        String registerUrl = "http://"+ip+"/left?var=left"+"&val="+1;
        try
        {
            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200)
            {
                httpURLConnection.disconnect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        // Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void right() {
        String registerUrl = "http://"+ip+"/right?var=right"+"&val="+1;
        try
        {
            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200)
            {
                httpURLConnection.disconnect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        // Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void up() {
        String registerUrl = "http://"+ip+"/go?var=up"+"&val="+1;
        try
        {
            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200)
            {
                httpURLConnection.disconnect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        // Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void down() {
        String registerUrl = "http://"+ip+"/back?var=down"+"&val="+1;
        try
        {
            URL url = new URL(registerUrl);
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            httpURLConnection.setReadTimeout(1000 * 5);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            final StringBuilder buffer = new StringBuilder();
            int code = httpURLConnection.getResponseCode();
            if (code == 200)
            {
                httpURLConnection.disconnect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                {
                    buffer.append(line);
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        // Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            httpURLConnection.disconnect();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }





    private void downloadFile()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String downloadUrl = "http://"+ip+":81/stream";
                @SuppressLint("SdCardPath") String savePath = "/sdcard/pic.jpg";

                File file = new File(savePath);
                if (file.exists())
                {
                    file.delete();
                }

                BufferedInputStream bufferedInputStream = null;
                FileOutputStream outputStream = null;
                try
                {
                    URL url = new URL(downloadUrl);

                    try
                    {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(1000 * 50);
                        httpURLConnection.setReadTimeout(1000 * 50);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.connect();

                        if (httpURLConnection.getResponseCode() == 200)
                        {
                            InputStream in = httpURLConnection.getInputStream();

                            InputStreamReader isr = new InputStreamReader(in);
                            BufferedReader bufferedReader = new BufferedReader(isr);

                            String line;
                            StringBuffer stringBuffer  = new StringBuffer();

                            int i = 0;

                            int len;
                            byte[] buffer;

                            while ((line = bufferedReader.readLine()) != null)
                            {
                                if (line.contains("Content-Type:"))
                                {
                                    line = bufferedReader.readLine();

                                    len = Integer.parseInt(line.split(":")[1].trim());

                                    bufferedInputStream = new BufferedInputStream(in);
                                    buffer = new byte[len];

                                    int t = 0;
                                    while (t < len)
                                    {
                                        t += bufferedInputStream.read(buffer, t, len - t);
                                    }

                                    bytesToImageFile(buffer, "0A.jpg");

                                  final Bitmap bitmap = BitmapFactory.decodeFile("sdcard/0A.jpg");
                                    runOnUiThread(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            imageView.setImageBitmap(bitmap);
                                        }
                                    });
                                }


                            }
                        }

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e)
                {
                    e.printStackTrace();
                } finally
                {
                    try
                    {
                        if (bufferedInputStream != null)
                        {
                            bufferedInputStream.close();
                        }
                        if (outputStream != null)
                        {
                            outputStream.close();
                        }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

    private void bytesToImageFile(byte[] bytes, String fileName)
    {
        try
        {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes, 0, bytes.length);
            fos.flush();
            fos.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}