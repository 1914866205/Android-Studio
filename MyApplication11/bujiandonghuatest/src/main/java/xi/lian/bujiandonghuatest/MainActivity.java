package xi.lian.bujiandonghuatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper flipper;//定义ViewFliper
    GestureDetector detector;//定义手势检测器

    final int distance=50;//定义手势动作两点之间最小距离
    //定义图片数组
    private  int[]images=new int[]{
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a3,
            R.drawable.a4,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建手势检测器
        detector=new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //第四步：在onFling()方法中通过触摸事件的X坐标判断是向左滑动还是向右滑动，并为其设置动画
                //从右向左滑动
                if (e1.getX()-e2.getX()>distance){
                    //显示前一张
                    flipper.showPrevious();
                    return  true;
                }else  if (e2.getX()-e1.getX()>distance){
                    //显示后一张
                    flipper.showNext();
                    return  true;
                }

                return false;
            }
        });
        //获取ViewFliper
        flipper = findViewById(R.id.filpper);
        for (int i=0;i<images.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(images[i]);
            flipper.addView(imageView);//加载图片
        }
        //定义并初始化动画数组
        Animation[]animations=new Animation[2];
        //淡入动画
        animations[0]=AnimationUtils.loadAnimation(this,R.anim.anim_alpha_in);
        //淡出动画
        animations[1]=AnimationUtils.loadAnimation(this,R.anim.anim_alpha_out);
        //指定切换的动画效果
        flipper.setInAnimation(animations[0]);
        flipper.setOutAnimation(animations[1]);

    }
    //第五步：将Activit上的触摸事件交给GestureDetector处理

    public boolean onTouchEvent(MotionEvent event) {

        return detector.onTouchEvent(event);
    }
}
