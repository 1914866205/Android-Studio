package xi.lian.scroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout=findViewById(R.id.line1);
        //添加垂直线性布局管理器
        LinearLayout linearLayout1=new LinearLayout(MainActivity.this);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        //创建滚动视图对象
        ScrollView scrollView=new ScrollView(MainActivity.this);
        linearLayout.addView(scrollView);
        scrollView.addView(linearLayout1);
        //为第二个滚动视图添加一个图像视图和文本框组件
        ImageView imageView=new ImageView(MainActivity.this);
        imageView.setImageResource(R.mipmap.o);
        linearLayout1.addView(imageView);
        TextView textView=new TextView(MainActivity.this);
        textView.setText(R.string.girl);
        linearLayout1.addView(textView);
    }
}
