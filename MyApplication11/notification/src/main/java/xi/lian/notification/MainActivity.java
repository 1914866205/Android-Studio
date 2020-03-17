 package xi.lian.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*状态栏
* 获取系统的Notification服务--getSystemServer()方法
* 创建Notification对象
* 为Notification对象设置属性
* 发送Notification
* */
public class MainActivity extends AppCompatActivity {
//设置通知ID
    final int NOTIFYID=0X123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建并发送通知       创建通知管理器                                                                通知服务
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //创建通知对象
        Notification.Builder notificationbuilde=new Notification.Builder(MainActivity.this);
        //设置通知打开后,自动消失
        notificationbuilde.setAutoCancel(true);
        //设置通知图标    但运行是不显示
        notificationbuilde.setSmallIcon(R.drawable.massage);
        //设置标题
        notificationbuilde.setContentTitle("有新消息");
        //设置内容
        notificationbuilde.setContentText("点击查看内容");
        //设置发送时间      获取当前时间——————立刻发送
        notificationbuilde.setWhen(System.currentTimeMillis());
        //设置声音和震动
        notificationbuilde.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE);
        //创建一个启动详细页面的Intent
        Intent intent=new Intent(MainActivity.this,DetailActivity.class);
        //创建一种可以由其他的应用在稍晚的时间触发的机制
        //即启动详细页面的动作在点击了“点击查看详细内容”后触发   数字无所谓
        PendingIntent pendingIntent= PendingIntent.getActivity(MainActivity.this,0,intent,0);
        //设置点击通知栏的时候跳转
        notificationbuilde.setContentIntent(pendingIntent);
        //使用通知ID  发送通知  已过时
        //notificationManager.notify(NOTIFYID,notificationbuilde.build());
        String channel="mychannnel1";
        NotificationChannel channelbody = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelbody = new NotificationChannel(channel,"消息推送",NotificationManager.IMPORTANCE_DEFAULT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channelbody);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationbuilde.setChannelId(channel);
        }
        Notification notifi_true = notificationbuilde.build();
        notificationManager.notify(NOTIFYID,notifi_true);
    }
}
