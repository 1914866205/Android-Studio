package xi.lian.ontouch00;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //第二步，创建刚才类的对象，并为类添加触摸事件监听器
        //在重写触摸方法中根据触摸的位置重给对象定位
        final HatView hatView=new HatView(MainActivity.this);
        hatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hatView.bitmapx=event.getX()-80;
                hatView.bitmapy=event.getY()-80;
                //重绘组件
                hatView.invalidate();
                return true;
            }
        });
        //第三步，把图片添加到布局管理器中
        RelativeLayout rl=findViewById(R.id.relativelayout);
        rl.addView(hatView);
    }
}
