package xi.lian.photo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private  int [] arrayPicture=new int[]{
            R.drawable.bj,R.drawable.oo
    };
    private ImageSwitcher imageSwitcher;
    private int index;//图片索引
    private float touchDownx;//手指落下
    private float touchUpx;//手指抬起
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageSwitcher=findViewById(R.id.is);
        //定义一个视图工程
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(MainActivity.this);
                //设置默认显示的图片
                imageView.setImageResource(arrayPicture[0]);
                return imageView;
            }
        });
        //添加触摸事件监听器
        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //按下
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    touchDownx=event.getX();
                    return true;
                }//抬起
                else if (event.getAction()==MotionEvent.ACTION_UP){
                    touchUpx=event.getX();
                    //设置从左向右滑动时的动画 左进右出
                    if ((touchUpx-touchDownx)>100){
                        index=index==0?arrayPicture.length-1:index-1;
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.class));
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.class));
                        imageSwitcher.setImageResource(arrayPicture[index]);
                    }else if ((touchDownx-touchUpx)>100) {
                        //设置从右向左滑动时的动画  右进左出
                        index = index == 0 ? arrayPicture.length : index + 1;
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.class));
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.class));
                        imageSwitcher.setImageResource(arrayPicture[index]);
                    }
                    }
                    return true;
                }

        });
    }
}
