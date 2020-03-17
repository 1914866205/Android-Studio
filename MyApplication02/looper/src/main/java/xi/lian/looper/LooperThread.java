package xi.lian.looper;
//都是os包下
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.logging.LogRecord;

public class LooperThread extends Thread {
    public Handler handler;//声明一个Handle对象
    @Override
    public void run() {
        super.run();
        //初始化Looper对象
        Looper.prepare();
        handler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("Looper",String.valueOf(msg.what));
            }
        };
        //获取Message
        Message message=handler.obtainMessage();
        //设置消息代码
        message.what=0x001;
        //发送消息
        handler.sendMessage(message);
        //启动Looper
        Looper.loop();
    }
}
