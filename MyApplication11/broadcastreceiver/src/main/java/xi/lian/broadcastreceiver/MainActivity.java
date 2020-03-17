package xi.lian.broadcastreceiver;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //获取广播按钮
        Button button=findViewById(R.id.bottom);
       //为按钮设置单击事件监听器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送一条广播
                Intent intent=new Intent("小胖");
                //为Intent添加动作
                // intent.setAction("小胖");
                //参数为  包名                          //加上包名的完整路径
                //安卓8.0   静态广播——不推荐，容易造成不死程序
                //推荐动态网络
                intent.setComponent(new ComponentName("xi.lian.broadcastreceiver","xi.lian.broadcastreceiver.MyReceiver"));
               //发送广播
                sendBroadcast(intent);
            }
        });


    }
}
