package xi.lian.imageswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class ImageSwitch1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switch1);
        ImageSwitcher is=findViewById(R.id.imageswitch);
        //设置图片淡出和进入的效果
        is.setOutAnimation(AnimationUtils.loadAnimation(ImageSwitch1.this,android.R.anim.fade_out));
        is.setInAnimation(AnimationUtils.loadAnimation(ImageSwitch1.this,android.R.anim.fade_in));
        //定义一个图片工厂
        is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            //为图片切换器切换图片
            public View makeView() {
                ImageView imageView=new ImageView(ImageSwitch1.this);
               //设置一个默认显示的图片
                imageView.setImageResource(R.drawable.bj);
                return imageView;
            }
        });
        is.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageSwitcher)v).setImageResource(R.drawable.oo);
            }
        });
    }
}
