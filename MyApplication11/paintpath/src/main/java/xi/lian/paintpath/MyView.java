package xi.lian.paintpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();//创建一个画笔
        paint.setAntiAlias(true);//设置为抗锯齿
        paint.setColor(0xFF0000FF);//设置画笔颜色为蓝色
        paint.setStyle(Paint.Style.STROKE);//设置填充样式为描边

        //设置路径
        Path path=new Path();
        //设置圆形路径  圆心的x,y坐标  半径   方向（顺时针cw，逆时针ccw）
        path.addCircle(650,800,530,Path.Direction.CW);
        //绘制路径
    //    canvas.drawPath(path,paint);
        //设置绘制文字大小
        paint.setTextSize(60);
        //绘制绕路径的文本        文本  路径  水平偏移量  垂直偏移量  画笔
        canvas.drawTextOnPath("如果有一天，你不再寻找爱情，只是去爱；你不再渴望成功，只是去做，你不再追求成长，只是去修；一切才真正开始",path,0,0,paint);




    }
}
