package xi.lian.ontouch_and_onclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.bt);
        //第一步，为按钮添加单击事件监听器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("onClick","单击事件");
            }
        });
        //第二步，为按钮添加触摸事件监听器
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //如果是手指按下
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.i("onTouch", "按下事件");
                }   //如果是手指抬起
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("onTouch", "抬起事件");
                }
                return false;
            }
            });
    }
}
