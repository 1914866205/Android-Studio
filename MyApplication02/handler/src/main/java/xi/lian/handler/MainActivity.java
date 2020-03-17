package xi.lian.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/*
* 只能在任意线程发送消息，由主线程接收并修改消息
* 不可以直接在子线程修改主线程的程序
* 其他线程----发送消息--->Handler---->修改程序
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView=findViewById(R.id.tv);
        Button button=findViewById(R.id.bottom);
        //定义Handler对象
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==0x123){
                      textView.setText("你今天的努力，是幸运的伏笔；当下的付出，是明天的花开");
                }
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个线程
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0x123);//消息验证码
                      //  textView.setText("你今天的努力，是幸运的伏笔；当下的付出，是明天的花开");
                    }
                });
                thread.start();
            }
        });
    }
}
