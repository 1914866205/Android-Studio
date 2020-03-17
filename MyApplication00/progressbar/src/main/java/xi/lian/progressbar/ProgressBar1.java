package xi.lian.progressbar;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ProgressBar1 extends AppCompatActivity {
    private ProgressBar progressBar;
    //记录完成进度
    private int mProgress=0;
    //处理消息的Handnle对象
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏显示
        progressBar=findViewById(R.id.pb);
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                    if(msg.what==0x111){
                        progressBar.setProgress(mProgress);
                    }else{
                        Toast.makeText(ProgressBar1.this,"更新已完成",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    mProgress=doWork();
                    Message m=new Message();
                    if(mProgress<100){
                        //消息没有完成则更新进度
                        m.what=0x111;  //0x111是消息代码 随便写
                        mHandler.sendMessage(m);
                    }else{
                        m.what=0x110;
                        mHandler.sendMessage(m);
                        break;
                    }
                }
            }
            //dowWork方法是模拟一个耗时操作的
            private int doWork(){
                mProgress+=Math.random()*10;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return mProgress;
            }
        }).start();
    }
}
