package xi.lian.musicmediaplay;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
* 使用MediaPlayer类播放音频的步骤
*   A:创建MediaPlayer对象————>装载音频文件
*   B:  b1:  1 create()方法
*            2  create(Context context,int resid)
*             或 create(Context context ,Uri uri)
*              适合播放单独的音频文件
*
*       b2   1 无参的构造方法
*            2  setDataSource()
*            3  prepare()
*            适合连续播放多个音频文件
*   C： start()
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建一个MediaPlay对象，并且装载音频文件
        final  MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.music);
        //播放按钮
        Button button1=findViewById(R.id.bottom1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();//开始播放
            }
        });

        //暂停按钮
        Button button2=findViewById(R.id.bottom2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();//暂停
            }
        });

        //停止按钮
        Button button3=findViewById(R.id.bottom3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();//停止播放
            }
        });


    }
}
