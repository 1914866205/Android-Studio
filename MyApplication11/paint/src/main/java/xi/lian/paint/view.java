package xi.lian.paint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

@SuppressLint("ViewConstructor")
class view extends View {
    public view(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas是画布
        //创建一个画笔
  //      Paint paint=new Paint();
        //设置画笔的颜色
    //  透明    paint.setColor(0XFF6600);
    //不透明
  //  paint.setColor(0XFFFF6600);
        //设置填充方式
   //     paint.setStyle(Paint.Style.FILL);
        //画一个矩形
   //     canvas.drawRect(10,10,280,150,paint);

        //绘制机器人
        //创建一个画笔
        Paint paint1=new Paint();
        paint1.setStyle(Paint.Style.FILL);
        //设置抗锯齿功能
        paint1.setAntiAlias(true);
        //设置画笔的颜色为不透明的绿色
        paint1.setColor(0xFFA4c739);
        //绘制机器人的头
        //定义外轮廓矩形       外轮廓四个顶点坐标
        RectF rectF=new RectF(10,10,100,100);
        //调整头
        rectF.offset(90,20);
        canvas.drawArc(rectF,-10,-160,false,paint1);
        //绘制眼睛  白色
        paint1.setColor(0xFFFFFFFF);
        //画眼睛 绘制圆
        canvas.drawCircle(165,53,4,paint1);
        canvas.drawCircle(125,53,4,paint1);
        //绘制笔触的宽度
        paint1.setStrokeWidth(2);
        //设置画笔的颜色为不透明的绿色
        paint1.setColor(0xFFA4c739);
        //绘制天线       线的起点x,y 线的终点x,y
        canvas.drawLine(110,15,125,35,paint1);
        canvas.drawLine(180,15,165,35,paint1);

        //绘制身体     左上定点坐标    右下定点坐标
        canvas.drawRect(100,75,190,50,paint1);
        //绘制圆角矩形
        RectF rectF_body=new RectF(100,140,190,160);
        //                                 x轴上的半径 y上的半径
        canvas.drawRoundRect(rectF_body,10,10,paint1);

        //绘制手臂
        RectF rectF_arm=new RectF(75,75,96,100);
        //绘制为圆角矩形
        canvas.drawRoundRect(rectF_arm,10,10,paint1);
        //另一只手臂通过偏移绘制
        rectF_arm.offset(120,0);
        canvas.drawRoundRect(rectF_arm,10,10,paint1);

        //绘制腿
        RectF rectF_leg=new RectF(115,150,135,200);
        //绘制为圆角矩形
        canvas.drawRoundRect(rectF_leg,10,10,paint1);
        //另一只手臂通过偏移绘制
        rectF_leg.offset(40,0);
        canvas.drawRoundRect(rectF_leg,10,10,paint1);

    }
}
