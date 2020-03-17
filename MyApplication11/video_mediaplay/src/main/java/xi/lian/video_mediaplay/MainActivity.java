package xi.lian.video_mediaplay;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

//MediaPlayer + SurfaceView
/*
* 1 定义SurfaceView组件
* 2 创建MediaPlayer对象
* 3 加载视频
* 4 视频输出到SurfaceView——————>setDisplay()
*          暂停  pause()
*          播放视频 包括开始播放或继续播放start()
*          停止播放 stop()
*
* */
public class MainActivity extends AppCompatActivity {
private ImageButton start;
private ImageButton pause;
private ImageButton stop;
private MediaPlayer mediaPlayer;
private SurfaceHolder surfaceHolder;
private boolean noPlay=true;//设置视频的状态，初始为未播放
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取SurfaceView组件
        SurfaceView surfaceView=findViewById(R.id.surfaceView);
        //获取SurfaceHolder
        surfaceHolder=surfaceView.getHolder();
        //获取MdeiaPlayer对象
         mediaPlayer=new MediaPlayer();
        //设置MediaPlayer的多媒体类型  为音频流
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //设置完成事件监听器
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
            }
        });

        //控制视频的播放，暂停和停止
        start=findViewById(R.id.play);
        stop=findViewById(R.id.stop);
        pause=findViewById(R.id.pause);
        //开始事件监听器
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是开始播放还是继续播放
                if (noPlay){
                    play();
                    noPlay=false;//表示视频处于播放状态
                }else{
                    mediaPlayer.start();//继续播放视频
                }
            }
        });
        //暂停事件监听器
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();//暂停播放
                }
            }
        });
        //停止事件监听器
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();//停止播放
                    noPlay=false;//把视频状态改成未播放
                }
            }
        });

    }
    //释放资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();//停止播放
            }
        mediaPlayer.release();//释放资源
    }

    //播放视频的方法
    public void play() {
        mediaPlayer.reset();//重置MediaPlayer
        mediaPlayer.setDisplay(surfaceHolder);//把视频画面输出到SurfaceView
        try {
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory()+"/爱剪辑-教官.mp4");//设置要播放的视频
            mediaPlayer.prepare();//预加载视频
        } catch (IOException e) {
            e.printStackTrace();
        }
            mediaPlayer.start();//播放视频

    }
}
