package xi.lian.buleboothtest;

import android.os.Handler;
import android.os.Message;

import java.io.DataInputStream;
import java.io.IOException;

class bluetoothMsgThread extends Thread{
    //输入数据流
    private DataInputStream mmInStream;
    //Handle对象
    private Handler msgHandler;
    //构造函数
    public bluetoothMsgThread(DataInputStream mmInStream,Handler msgHandler){
        this.mmInStream=mmInStream;
        this.msgHandler=msgHandler;
    }
    public void run(){
        //定义缓冲区
        byte[]InBuffer=new byte[64];
        while(!Thread.interrupted()){       //如果线程不被中断
            try {
                mmInStream.readFully(InBuffer,0,8);
                //创建message对象
                Message msg=new Message();
                msg.what=0x1234;
                msg.obj=InBuffer;
                //通过msgHandler消息发送到界面
                msgHandler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
