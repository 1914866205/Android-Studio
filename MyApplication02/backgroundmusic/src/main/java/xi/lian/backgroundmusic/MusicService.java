package xi.lian.backgroundmusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;

public class MusicService extends Service {
    //记录当前播放状态的变量
    static boolean isplay;
    //播放音乐的MainPlayer对象
    MediaPlayer player;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        //创建Mediaplay对象，并加载播放的音频文件
        player=MediaPlayer.create(this,R.raw.music);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       if (!player.isPlaying()){
           player.start();//播放音乐
           isplay=player.isPlaying();//设置当前状态为正在播放
       }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //停止音乐的播放
        player.stop();
        isplay=player.isPlaying();//设置当前的状态为非播放
        player.release();//释放资源
        super.onDestroy();
    }

}
