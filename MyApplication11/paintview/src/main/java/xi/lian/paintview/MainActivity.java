package xi.lian.paintview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

/*
* BitmapFactory   创建位图对象
*               decodeFile()通过路径创建
*               decodeResource()通过资源ID创建
*               decodeStream()通过输入流创建
*
*Bitmap
*       createBitmap() 根据重载形式创建对应的Bitmap对象
*       compress()  压缩Bitmao对象并保存到文件输出流
*       createScaledBitmap() 将原位图缩放并创建新的Bitmap对象
*
*
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
