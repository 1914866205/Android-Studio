package xi.lian.aliiarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //设置闹钟
        //获取时间拾取器
        final TimePicker timePicker=findViewById(R.id.time);
        //设置24小时制
        timePicker.setIs24HourView(true);
        //获取 "设置闹钟" 按钮
        Button button=findViewById(R.id.set);
        //为 “设置闹钟” 按钮添加单击事件监听器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置闹钟
                //创建一个Intent对象
                Intent intent=new Intent(MainActivity.this,AlarmActivity.class);
                //获取显示闹钟的PendingIntent对象
                PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,0);
                //获取AlarmManager对象
                AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                //获取日历对象
                Calendar calendar=Calendar.getInstance();
                //设置闹钟的小时数
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                //设置闹钟的分钟数
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                //设置秒数为0
                calendar.set(Calendar.SECOND,0);
                // 设置一个闹钟  设置闹钟类型     到达指定时间会唤醒系统的闹钟类型
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                Toast.makeText(MainActivity.this, "闹钟设置成功", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
