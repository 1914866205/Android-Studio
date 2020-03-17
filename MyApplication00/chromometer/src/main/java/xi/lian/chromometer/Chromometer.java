package xi.lian.chromometer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.Toast;

public class Chromometer extends AppCompatActivity {
    Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chromometer);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏显示
        chronometer=findViewById(R.id.chronometer1);
        //设置计时器的起始时间
        chronometer.setBase(SystemClock.elapsedRealtime());
        //设置时间格式
        chronometer.setFormat("%s");
        //开始计时器
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(SystemClock.elapsedRealtime()-chronometer.getBase()>=60000){
                    Toast.makeText(Chromometer.this,"一分钟时间到",Toast.LENGTH_LONG).show();
                    chronometer.stop();
                }
            }
        });
    }
}
