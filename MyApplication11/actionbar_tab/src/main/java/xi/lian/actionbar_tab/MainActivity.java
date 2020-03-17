package xi.lian.actionbar_tab;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置ActionBar为选项卡模式，并将标签页面添加到Action对象中
        //获取ActionBay对象
        ActionBar actionBar=getSupportActionBar();
        //设置ActionBar为选项卡模式
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //指定不显示 0         和不显示标题(隐藏标题栏)
        actionBar.setDisplayOptions(0,ActionBar.DISPLAY_SHOW_TITLE);
        //把各个标签页添加到Action Bar中

        //添加第一个标签页
        actionBar.addTab(actionBar.newTab().setText("标签页c1").setTabListener(new myTap(this,c1.class)));
        //添加第二个标签页
        actionBar.addTab(actionBar.newTab().setText("标签页c2").setTabListener(new myTap(this,c2.class)));
        //添加第三个标签页
        actionBar.addTab(actionBar.newTab().setText("标签页c3").setTabListener(new myTap(this,c3.class)));
        //添加第四个标签页
        actionBar.addTab(actionBar.newTab().setText("标签页c4").setTabListener(new myTap(this,c4.class)));
        //添加第五个标签页
        actionBar.addTab(actionBar.newTab().setText("标签页c5").setTabListener(new myTap(this,c5.class)));

    }
}
