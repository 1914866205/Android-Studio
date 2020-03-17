package xi.lian.musicmediaplaytest;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //创建全局的MediaPlayer对象
    private MediaPlayer mediaPlayer;
    //定义是否为暂停状态
    private  boolean isPause=false;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取要播放的音频文件
         file = new File("/sdcard/music.mp3");
        //判断该音频是否存在
        if (file.exists()) {
            //获取文件的绝对路径并且装载了音频
            mediaPlayer = MediaPlayer.create(this, Uri.parse(file.getAbsolutePath()));
        } else {
            Toast.makeText(this, "要播放的音频文件不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        //控制音频的播放/暂停 和停止按钮
        final ImageButton button = findViewById(R.id.play);
        final ImageButton button1 = findViewById(R.id.stop);

        //播放和暂停的单击事件监听器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()&&!isPause){
                       mediaPlayer.pause();//暂停播放
                        isPause=true;//设置为暂停状态
                    //暂停后，把图片按钮设为空，则显示背景图片
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.p,null));
                    }
                }else{
                    mediaPlayer.start();//继续播放
                    isPause=false;//设置为播放状态
                    //显示图片
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.s,null));
                    }
                }
            }
        });

        //停止按钮的事件监听器

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();//停止播放
                button.setImageDrawable(getResources().getDrawable(R.drawable.p, null));
            }
        });
        //设置完成事件监听器
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play();//重新播放功能
            }
        });
    }

    private void play() {
        mediaPlayer.reset();//重置MediaPlayer
        try {
            mediaPlayer.setDataSource(file.getAbsolutePath());
            mediaPlayer.prepare();//预加载音频
            mediaPlayer.start();//开始播放

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//一定要释放资源
    @Override
    protected void onDestroy() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();//停止播放
            mediaPlayer.release();//释放资源
        }
        super.onDestroy();
    }
}