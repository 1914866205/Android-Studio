package xi.lian.backgroundmusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final Intent intent=new Intent(MainActivity.this,MusicService.class);
        //获取播放、停止的按钮
        ImageButton imageButton=findViewById(R.id.play);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动和停止musicservice
                if (MusicService.isplay==false){
                   startService(intent);//启动service
                    //设置当前图片为隐藏
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ((ImageButton)v).setImageDrawable(getResources().getDrawable(R.mipmap.start,null));
                            }
                    }
                    else{
                    stopService(intent);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ((ImageButton)v).setImageDrawable(getResources().getDrawable(R.mipmap.stop,null));
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        startService(new Intent(MainActivity.this,MusicService.class));
        super.onStart();
    }
}
