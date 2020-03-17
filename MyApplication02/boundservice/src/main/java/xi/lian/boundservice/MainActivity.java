package xi.lian.boundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    BinderService binderService;//声明Service类的对象
    int[]tvid={
            R.id.t1,R.id.t2,
            R.id.t3,R.id.t4,
            R.id.t7,R.id.t6,
            R.id.t7
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取随机选号按钮
        Button button=findViewById(R.id.start);
        //单击按钮，获取随机彩票号码
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List number=binderService.getRandomNumber();
                for (int i=0;i<number.size();i++){
                    //获取文本框组件
                    TextView tv=findViewById(tvid[i]);
                    //为文本框组件设置随机数字
                    tv.setText(number.get(i).toString());
                }
            }
        });
    }
    //创建Service对象
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        //当Service与绑定它的组件连接成功时的方法
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取后台的Service
            binderService=((BinderService.MyBinder)service).getSercive();
        }

        @Override
        //当Service与绑定它的组件断开连接时的方法
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,BinderService.class);
        //与后台Service绑定
        //绑定时是否自动创建Sercvice  不自动创建参数为0  自动创建参数为BIND_AUTO_CREATE
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //解除绑定
        unbindService(serviceConnection);
    }
}
