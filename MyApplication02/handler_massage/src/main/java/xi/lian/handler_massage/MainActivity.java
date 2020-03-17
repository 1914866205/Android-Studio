package xi.lian.handler_massage;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
    private ViewFlipper flipper;
    final int FLAG=0x001;
    private Message message;
    int[]images=new int[]{
         R.mipmap.a,R.mipmap.b,R.mipmap.c
        };
    Animation[]animation=new Animation[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //通过viewFlipper组件播放广告图片
        flipper=findViewById(R.id.viewFliper);
        for (int i=0;i<images.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(images[i]);
            flipper.addView(imageView);//加载图片
        }
        //初始化动画数组
        //左侧平移进入动画
        animation[0]=AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in) ;
        //右侧平移进入动画
        animation[1]=AnimationUtils.loadAnimation(this, R.anim.anim_alpha_out);
        //为flipper设置图片进入动画效果
        flipper.setInAnimation(animation[0]);
        flipper.setInAnimation(animation[1]);
        //开启广告轮播
        message=Message.obtain();//获取message对象
        message.what=FLAG;
        handler.sendMessage(message);
    }
    //创建Handler对象实现3秒更新一次
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==FLAG){
                flipper.showPrevious();//切换到下一张图片
                //message的实例化
                message=handler.obtainMessage(FLAG);//获取Message
                handler.sendMessageDelayed(message,3000);//延迟为3秒
            }
        }
    };
}
