package xi.lian.paintpath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

/*
* Pathi类
*       addArc()  弧形
*       addCircle 圆形
*       addRect() 矩形
*       addRoundRect() 圆角矩形
*
*
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout=findViewById(R.id.frame);
        frameLayout.addView(new MyView(this));

    }
}
