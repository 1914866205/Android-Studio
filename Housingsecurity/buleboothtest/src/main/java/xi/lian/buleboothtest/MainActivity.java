package xi.lian.buleboothtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends Activity {
    //蓝牙适配器对象
    BluetoothAdapter mBluetoothAdapter;
    //蓝牙设备对象
    BluetoothDevice device;
    //蓝牙socker对象
    BluetoothSocket cliebtSocket;
    //蓝牙服务器socket对象
    BluetoothServerSocket btserver;
    //蓝牙地址
    String address;
    //out数据源
    OutputStream mmOutStream;
    //in数据流
    DataInputStream mmInStream;
    //蓝牙线程
    bluetoothMsgThread blue_tooth_msg_thread;

    //蓝牙设备的uuid
    UUID uuid=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    //震动对象
    Vibrator vibrator;
    public void show_result(byte[]buffer,int count) {
        //定义stringbuffer对象
        StringBuffer msg = new StringBuffer();
        //界面显示控件
        TextView tvInfo = findViewById(R.id.textViewReceiveInfo);
        //清空界面
        tvInfo.setText("");
        //使用16进制的方式显示接收到的buffer内容
        for (int i = 0; i < count; i++) {
            msg.append(String.format("0x%x", buffer[i]));
        }
        tvInfo.setText(msg);
    }
        //设置按钮状态
        public void set_btn_status(boolean status){
            Button ledonBtn=findViewById(R.id.ledonBtn);
            ledonBtn.setEnabled(status);
            Button ledoffBtn=findViewById(R.id.ledoffBtn);
            ledoffBtn.setEnabled(status);
            Button jdqonBtn=findViewById(R.id.jdqonBtn);
            jdqonBtn.setEnabled(status);
            Button jdqoffBtn=findViewById(R.id.jdqoffBtn);
            jdqoffBtn.setEnabled(status);
        }

            protected void onDestrory(){
                super.onDestroy();
                try {
                    //关闭out数据流
                    if (mmOutStream!=null) {
                        mmOutStream.close();
                    }
                    //关闭in数据流
                    if (mmInStream!=null){
                        mmInStream.close();
                    }
                    //关闭socket对象
                    if (cliebtSocket!=null){
                        cliebtSocket.close();
                    }
                    blue_tooth_msg_thread.interrupt();
                    Toast.makeText(getApplicationContext(), "蓝牙App退出", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                        e.printStackTrace();
                }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //设置界面，继电器，led灯，按钮不能操作
        set_btn_status(false);
        //获取震动对象
        vibrator= (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        //查找蓝牙，需要先在安卓蓝牙设置里面配对
        Button searchDeviceBtn=findViewById(R.id.searchDeviceBtn);
        searchDeviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取蓝牙适配器
                mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter==null){
                    Toast.makeText(getApplicationContext(), "bluetooth is no available", Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
                //允许蓝牙适配器
                mBluetoothAdapter.enable();
                if (!mBluetoothAdapter.isEnabled()){
                    Toast.makeText(getApplicationContext(), "bluetooth function is no available", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                //获取安卓系统已经配对的蓝牙设备
                Set<BluetoothDevice> pairedDevice=mBluetoothAdapter.getBondedDevices();
                if (pairedDevice.size()<1){
                    Toast.makeText(getApplicationContext(), "没有配对的蓝牙设备，请先配对再使用", Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }


                Spinner spinner=findViewById(R.id.spinner1);
                List<String> list=new ArrayList<String>();
                for (BluetoothDevice device:pairedDevice){
                    //myArrayAdapter.add（device.getName()+""+device.getAddress()）;
                    //list.add(device.getName()+""+device.getAddress());
                    //将mac地址加入到下拉框
                    list.add(device.getAddress());

                }
                //通过适配器对象，显示下拉框中的内容
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);


                Button connectBtn=findViewById(R.id.connectBtn);
                //允许 连接 按钮 操作
                connectBtn.setEnabled(true);
            }
        });

        Button connectBtn=findViewById(R.id.connectBtn);
        connectBtn.setEnabled(false);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Spinner spinner=findViewById(R.id.spinner1);
                address=spinner.getSelectedItem().toString();
                try {
                    //根据蓝牙地址获取到蓝牙设备
                    device=mBluetoothAdapter.getRemoteDevice(address);
                    //根据uuid连接到clientsocket
                    cliebtSocket=device.createRfcommSocketToServiceRecord(uuid);
                    //连接蓝牙设备
                    cliebtSocket.connect();

                    mmOutStream=cliebtSocket.getOutputStream();
                    //获取out对象
                    mmInStream=new DataInputStream(new BufferedInputStream(cliebtSocket.getInputStream()));
                    Toast.makeText(MainActivity.this, "蓝牙设备连接成功", Toast.LENGTH_SHORT).show();
                    //震动
                    vibrator.vibrate(100);
                    //设置 继电器  led  允许操作
                    set_btn_status(true);

                    //创建线程接收 单片机发送给安卓 蓝牙的数据
//                    blue_tooth_msg_thread=new bluetoothMsgThread(mmInStream,bluetoothMessageHandle);
                    blue_tooth_msg_thread=new bluetoothMsgThread(mmInStream,bluetoothMessageHandle);
                    blue_tooth_msg_thread.start();

                } catch (IOException e) {
                    set_btn_status(false);
                    Toast.makeText(getApplicationContext(), "连接蓝牙设备失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });


        Button ledonBtn=findViewById(R.id.ledonBtn);
        ledonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[]InBuffer=new byte[64];
                byte []buffer="a".getBytes();
                try {
                    mmOutStream.write(buffer);
                    //mmInStream.readFully(InBuffer,0,8);
                    //show_result(InBuffer,8);
                    vibrator.vibrate(100);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button jdqonBtn=findViewById(R.id.jdqonBtn);
        jdqonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[]InBuffer=new byte[64];
                byte []buffer="c".getBytes();
                try {
                    mmOutStream.write(buffer);
                    //mmInStream.readFully(InBuffer,0,8);
                    //show_result(InBuffer,8);
                    vibrator.vibrate(100);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button jdqoffBtn=findViewById(R.id.jdqoffBtn);
        jdqoffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] InBuffer = new byte[64];
                byte buffer[] = "d".getBytes();
                try {
                    mmOutStream.write(buffer);
                    //mmInStream.readFully(InBuffer, 0, 8);
                    //show_result(InBuffer,8);
                    vibrator.vibrate(100);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    //定义Handler对象
    Handler bluetoothMessageHandle=new Handler() {
        public void handleMessage(Message msg) {
            //接收what=0x1234的自定义数据
            if (msg.what == 0x1234) {
                //显示内容到界面上
                show_result((byte[]) msg.obj, 8);
            }
        }
    };
}
