package xi.lian.camproj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private long exitTime=0;

    //触摸事件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        //单击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "基于回调的监听器主要用于通用性事件", Toast.LENGTH_SHORT).show();
            }
        });
        //长按事件
        ImageView imageView=findViewById(R.id.gg);
        //第二部，将长按事件注册到菜单中，并打开菜单
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //通过registerForContextMenu方法调用OnCreateContextMenu方法
                registerForContextMenu(v);
                openContextMenu(v);
                return true;
            }
        });
    }
//基于监听的事件监听器：主要用于特定性事件
    @Override
    //触摸
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(this, "触摸", Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }

    @Override
    //按下
    public boolean onKeyDown(int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_BACK)
                    exit();
                    return true;
    }
    //创建退出方法exit()
    private void exit() {
        //如果按键时间超过两秒，就弹出消息提示
        //获取当前时间-第一次按下返回键的时间
        if ((System.currentTimeMillis()-exitTime)>2000){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime=System.currentTimeMillis();
        }else{
            finish();
            //退出程序
            System.exit(0);
        }

    }

    @Override
    //抬起
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Toast.makeText(this, "抬起", Toast.LENGTH_SHORT).show();
        return super.onKeyUp(keyCode, event);
    }
    //长按事件----2秒

    //第一步，在MainActivity中重写onCreateContextMenu菜单，为菜单添加选项值
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("收藏");
        menu.add("举报");
    }
}
