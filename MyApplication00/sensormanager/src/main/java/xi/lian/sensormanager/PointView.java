package xi.lian.sensormanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;

import java.text.AttributedCharacterIterator;

public class PointView extends View implements SensorEventListener {
    private SensorManager sensorManager;//获取传感器管理器
    private Bitmap pointer=null;//定义指针的位图对象
    private  float [] allValue;//磁场传感器的值
    public PointView(Context context, AttributeSet attrs) {
        super(context,attrs);
        //获取传感器管理器
        sensorManager=(SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //设置传感器类型为磁场传感器
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),sensorManager.SENSOR_DELAY_GAME);
        //指定要绘制的指针
        pointer=BitmapFactory.decodeResource(super.getResources(),R.drawable.bj );

    }


    @Override//传感器值改变
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            float[]values=sensorEvent.values;
            allValue=values;
            super.postInvalidate();//刷新界面
        }
    }

    @Override//传感器精度改变
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    //根据X,Y轴的磁场强度绘制指针
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (allValue!=null){
            float x=allValue[0];//获取xy轴的磁场强度
            float y=allValue[1];
            canvas.restore();//重新绘制对象
            //设置旋转的中心为屏幕的中点
            canvas.translate(super.getWidth()/2, super.getHeight()/2);
            if (y==0&&x>0){
                canvas.rotate(90);//画布旋转90度
            }else if (y==0&&x<0){
                canvas.rotate(270);//画布旋转270度
            }else{
                if (y>=0){
                    canvas.rotate((float)Math.tanh(x/y)*90);
                }else{
                    canvas.rotate(180+(float)Math.tanh(x/y)*90);
                }
            }
            canvas.drawBitmap(this.pointer,-this.pointer.getWidth()/2 ,-this.pointer.getHeight()/2,new Paint());


        }
    }
}
