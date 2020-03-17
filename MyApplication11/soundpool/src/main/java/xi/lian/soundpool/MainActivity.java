package xi.lian.soundpool;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    SoundPool soundPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取列表视图
        ListView listView=findViewById(R.id.listView);
        //创建SoundPool对象，并设置相关属性
        AudioAttributes audioAttributes= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)  //设置音效的使用场景
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)//设置音效类型
                    .build();
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
             soundPool=new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)//设音效的属性
                    .setMaxStreams(2)  //设置最多可哦容纳10个音频流
                    .build();
        }
        //将要播放的音频保存在HashMap对象中
        //创建一个HashMap对象
        final HashMap<Integer,Integer> soundmap=new HashMap<Integer,Integer>();
//        soundmap.put(0,R.raw.music);
//        soundmap.put(1,R.raw.music2);

        soundmap.put(0,soundPool.load(this,R.raw.music,1));//1是优先级
        soundmap.put(1,soundPool.load(this,R.raw.music2,1));
        //播放音量
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //参数  键值  左声道音量 右声道音量 优先级 循环次数 播放速率
                soundPool.play(soundmap.get(position),1,1,0,0,1);
            }
        });

    }


}
