package xi.lian.bujiandonghua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView=findViewById(R.id.snail);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建动画对象
                Animation animation=AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                //开启动画
                imageView.startAnimation(animation);

                //创建动画对象
                Animation animation1=AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                //开启动画
                imageView.startAnimation(animation1);

                //创建动画对象
                Animation animation2=AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
                //开启动画
                imageView.startAnimation(animation2);

                //创建动画对象
                Animation animation3=AnimationUtils.loadAnimation(MainActivity.this, R.anim.transilate);
                //开启动画
                imageView.startAnimation(animation3);

            }
        });

    }
}
