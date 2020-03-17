package xi.lian.paintview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.view.View;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取文件的路径
        String path=Environment.getExternalStorageDirectory()+"/bj.jpg";
        //创建位图对象
        Bitmap bitmap=BitmapFactory.decodeFile(path);
        //定义画笔
        Paint paint=new Paint();
        //绘制图片 用画布canvas的drawBitmap
        canvas.drawBitmap(bitmap,0,0,paint);

        //从原来的位图对象中挖取一小块图片作为新的位图对象
        Bitmap bitmap1=Bitmap.createBitmap(bitmap,23,89,150,168);
        //绘制新的位图
        canvas.drawBitmap(bitmap1,270,50,paint);







    }


}
