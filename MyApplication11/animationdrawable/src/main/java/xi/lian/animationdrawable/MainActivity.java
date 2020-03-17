package xi.lian.animationdrawable;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    //记录播放状态
    private boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取布局管理器
        LinearLayout linearLayout=findViewById(R.id.layout);
        //获取动画资源Drawable
        final AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        //为布局管理器添加单击事件
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    animationDrawable.start();//播放动画
                    flag=false;//控制停止
                }else{
                    animationDrawable.stop();//停止动画
                    flag=true;  //控制播放与停止
                }
            }
        });
    }
}
