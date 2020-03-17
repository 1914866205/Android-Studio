package xi.lian.gamejindutiao;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
private ProgressBar timer;//声明水平进度条
    final  int time=60;//定义时间长度
    final  int time_msg=0x001;//消息代码
    private  int mProgressStatus=0;//定义完成的进度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=findViewById(R.id.time0);
        //启动进度条
        handler.sendEmptyMessage(time_msg);
    }
    //创建Handler对象，实现一秒钟更新一次进度
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if ((time-mProgressStatus)>0){
                mProgressStatus++;//进度加一
                timer.setProgress(time-mProgressStatus);
                //一秒后发生消息
                handler.sendEmptyMessageDelayed(time_msg,1000);
            }else{
                Toast.makeText(MainActivity.this, "一分钟时间到", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
