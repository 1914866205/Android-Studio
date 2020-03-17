package xi.lian.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 控制摄像头的基本步骤
 * 1  打开摄像头  open()
 * 2  设置拍照参数 Camera.Parameter类
 * 3  预览拍照画面 startPreview()
 * 4  进行拍照  takePicture()
 * 5  结束预览 stopPriview()
 * 6 释放摄像头资源 release()
 *
 * Surface就是“表面”的意思。在SDK的文档中，对Surface的描述是这样的：“Handle onto a raw buffer that is being managed by the screen compositor”，翻译成中文就是“由屏幕显示内容合成器(screen compositor)所管理的原生缓冲器的句柄”，这句话包括下面两个意思：
 *
 * 1.      通过Surface（因为Surface是句柄）就可以获得原生缓冲器以及其中的内容。就像在C语言中，可以通过一个文件的句柄，就可以获得文件的内容一样；
 *
 * 2.      原生缓冲器（rawbuffer）是用于保存当前窗口的像素数据的。
 *
 *     引伸地，可以认为Android中的Surface就是一个用来画图形（graphics）或图像（image）的地方。
 *     根据Java方面的常规知识，我们知道通常画图是在一个Canvas对象上面进行的，由此，可以推知一个Surface对象中应该包含有一个Canvas对象，事实上的确如此，
 *     而且这一点可以很容易通过debug运行程序的方式得到证明（将光标停留在对象变量surface上，
 *     会弹出一个对话框，其中红色方框的内容，就表面surface中有一个CompatileCanvas成员变量）当然，看源代码也是可以证明这一点： *
 *     因此，在前面提及的两个意思的基础上，可以再加上一条：
 *
 * 3.      Surface中有一个Canvas成员，专门用于画图的。
 *
 * 所以，Surface中的Canvas成员，是专门用于供程序员画图的场所，就像黑板一样；其中的原生缓冲器是用来保存数据的地方；
 * Surface本身的作用类似一个句柄，得到了这个句柄就可以得到其中的Canvas、原生缓冲器以及其它方面的内容。
 *
 * 二、SurfaceView
 *
 *     SurfaceView，顾名思义就是Surface的View，通过SurfaceView就可以看到Surface的部分或者全部的内容，下面用一个图来形象地描述一下Surface和SurfaceView的关系：
 *
 *   SurfaceHolder是一个接口，其作用就像一个关于Surface的监听器。
 *   提供访问和控制SurfaceView背后的Surface 相关的方法
 *   （providingaccess and control over this SurfaceView's underlying surface），
 *   它通过三个回调方法，让我们可以感知到Surface的创建、销毁或者改变。在SurfaceView中有一个方法getHolder，
 *   可以很方便地获得SurfaceView所对应的Surface所对应的SurfaceHolder
 *
 *   为SurfaceHolder添加一个SurfaceHolder.Callback回调接口。
 */
public class MainActivity extends Activity {
    private android.hardware.Camera camera;//定义一个摄像头对象
    private  boolean isPreview=false;//是否为预览状态，false表示非预览状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //判断手机是否安装SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "请安装SD卡", Toast.LENGTH_SHORT).show();
        }
        //打开摄像并预览
        //用于显示摄像头预览
        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        //获取SurfaceHolder
        final SurfaceHolder surfaceHolder = surfaceView.getHolder();
        //设置SurfaceView自己不维护缓冲
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //获取"预览"按钮
        ImageButton imageButton = findViewById(R.id.pre);
        //实现相机预览功能
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断摄像头是否为非预览状态
                if (!isPreview) {
                    camera = android.hardware.Camera.open();//打开摄像头
                    isPreview = true;//设置为预览状态
                }
                try {
                    //设置用于显示预览的SurfaceView
                    camera.setPreviewDisplay(surfaceHolder);

                    //获取摄像头参数对象并修改参数
                    android.hardware.Camera.Parameters parameters = camera.getParameters();
                    //设置图片的尺寸
                    parameters.setPictureSize(1024, 1080);
                    //设置图片格式为JPG图片
                    parameters.setPictureFormat(PixelFormat.JPEG);
                    //设置图片的质量
                    parameters.set("jpeg-quality", 80);
                    //重新设置摄像头的参数
                    camera.setParameters(parameters);


                    //开始预览
                    camera.startPreview();
                    //设置自动对焦
                    camera.autoFocus(null);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //获取“拍照”按钮
        ImageButton tackPicture = findViewById(R.id.take);
        //实现相机拍照功能
        tackPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实现拍照功能
                if (camera != null) {
                    // 进行拍照 前两个参数是回调函数
                    camera.takePicture(null, null, Jpeg);
                }
            }
        });
    }
        //保存照片   为SurfaceHolder添加一个SurfaceHolder.Callback回调接口。
       final android.hardware.Camera.PictureCallback Jpeg=new android.hardware.Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
                //根据拍照获得的数据创建一个位图              数据    偏移量    数据的长度
                Bitmap bitmap=BitmapFactory.decodeByteArray(data,0,data.length);
                //停止预览
                camera.stopPreview();
                isPreview=false;//设置为非预览状态
                //获取SD卡中相片的位置
                File appDir=new File(Environment.getExternalStorageDirectory(),"/dcim/Camera/");
                if (!appDir.exists()){//如果该目录不存在
                    appDir.mkdir();//就创建爱你该目录
                }
                //将获取当前系统时间毫秒值设为照片的名称
                String fileName=System.currentTimeMillis()+".jpg";
                File file=new File(appDir,fileName);//创建文件对象

                //保存拍到的照片
                try {
                    //创建一个文件输出流对象
                    FileOutputStream fos=new FileOutputStream(file);
                    //将图片内容压缩为JPEG格式输出到输出流
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                    fos.flush();//将缓冲区中的数据全部写到输出流中
                    fos.close();//关闭文件输出流对象

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //将照片插入到系统图库
                try {
                    MediaStore.Images.Media.insertImage(MainActivity.this.getContentResolver(),
                            file.getAbsolutePath(),fileName,null);

                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
              //最后通知图库更新
                MainActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.parse("file://"+"")));
                Toast.makeText(MainActivity.this, "图片保存至："+file, Toast.LENGTH_SHORT).show();
                //重新预览
                resetCamera();
                onPause();
            }
            //创建重新预览方法
           private void resetCamera() {
                if (!isPreview){
                    camera.startPreview();//开启预览
                    isPreview=true;
                }
           }
       };




    //停止预览并释放摄像头资源
    @Override
    protected void onPause() {
        super.onPause();
        if (camera!=null){
            camera.stopPreview();//停止预览
            camera.release();//释放资源
        }
    }

}
