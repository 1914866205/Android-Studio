package xi.lian.painttext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class myview extends View {

    public myview(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    //绘制对白
        //创建画笔
        Paint paint=new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        //设置画笔颜色
        paint.setColor(0xFF000000);
        //设置文字对齐方式
        paint.setTextAlign(Paint.Align.LEFT);
        //设置文字大小
        paint.setTextSize(12);

        //绘制文字
        //参数    文字内容   文字起始位置的X,Y坐标 画笔对象
        canvas.drawText("你想和我一起？",175,160,paint);
        canvas.drawText("去探险吗？",175,175,paint);
        canvas.drawText("不，我不想去",245,45,paint);


    }
}
