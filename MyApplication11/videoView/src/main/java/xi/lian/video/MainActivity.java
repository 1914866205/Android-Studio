package xi.lian.video;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取VideoView组件
        VideoView videoView=findViewById(R.id.video);

        //加载要播放的视频    获取文件对象
        File file=new File(Environment.getExternalStorageDirectory()+"/爱剪辑-教官.mp4");
        if (file.exists()){
            //指定要播放的视频          得到文件的绝对位置
            videoView.setVideoPath(file.getAbsolutePath());
        }else{
            Toast.makeText(this, "要播放的视频文件不存在", Toast.LENGTH_SHORT).show();
        }

        //控制视频的播放
        //创建MediaController对象
        MediaController mediaController=new MediaController(MainActivity.this);
        //让VideoView和MediaController关联
        videoView.setMediaController(mediaController);

        //让VideoView获得焦点
        videoView.requestFocus();
        //开始播放视频
        videoView.start();
        //设置完成事件
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
            }
        });





    }
}
