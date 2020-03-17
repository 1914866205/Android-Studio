package xi.lian.gesturedetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

//第一步：让主方法实现GestureDetector.OnGestureListener接口，并实现其所有方法
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    //定义一个动画数组
    Animation []animations=new Animation[2];
    final  int distance=50;
    //添加图片
    private  int[]images=new  int[]{
            R.drawable.m,R.drawable.m2,R.drawable.m3,R.drawable.m4,R.drawable.m5,
            R.mipmap.bj,R.mipmap.girl
    };
    ViewFlipper viewFlipper;
    //第二步：定义一个全局的手势检测器
    GestureDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = findViewById(R.id.flipper);
        //添加动画
        animations[0]=AnimationUtils.loadAnimation(this,R.anim.anim_alpha_in);
        animations[1]=AnimationUtils.loadAnimation(this,R.anim.anim_alpha_out);
        viewFlipper.setInAnimation(animations[0]);
        viewFlipper.setOutAnimation(animations[1]);
        detector=new GestureDetector(MainActivity.this,this);
        //第三步：把图片加载到ViewFlipper中，并初始化动画数组
        for (int i=0;i<images.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(images[i]);
            viewFlipper.addView(imageView);
        }
    }
    //按下
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }
    //轻击屏幕
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }
    //长按
    @Override
    public void onLongPress(MotionEvent e) {

    }
    //手指滑动
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
       //第四步：在onFling()方法中通过触摸事件的X坐标判断是向左滑动还是向右滑动，并为其设置动画
        //从右向左滑动
        if (e1.getX()-e2.getX()>distance){

            //显示前一张
            viewFlipper.showPrevious();
            return  true;
        }else  if (e2.getX()-e1.getX()>distance){

            //显示后一张
            viewFlipper.showNext();
            return  true;
        }
        return false;
    }
    //第五步：将Activit上的触摸事件交给GestureDetector处理

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return detector.onTouchEvent(event);
    }
}
