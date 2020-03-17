package xi.lian.ontouch00;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class HatView extends View {
    public float bitmapx;
    public float bitmapy;
    public HatView(Context context) {
        super(context);
        bitmapx=65;
        bitmapy=0;
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        //定义一个画笔
        Paint paint=new Paint();
        //创建一个bitmap对象
        Bitmap bitmap=BitmapFactory.decodeResource(this.getResources(),R.mipmap.bj);
        //通过画布的drawBitmap方法绘制图片
        //为它指定位置
        canvas.drawBitmap(bitmap,bitmapx,bitmapy,paint);
        //判断图片是否回收,如果没有回收，则强制回收
        if (!bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
