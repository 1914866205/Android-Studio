package xi.lian.taphost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost=findViewById(R.id.tabhost);
        //初始化
        tabHost.setup();
        //为tabhost添加标签页
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        //用该对象加载标签页的布局文件
        layoutInflater.inflate(R.layout.tab,tabHost.getTabContentView());
        layoutInflater.inflate(R.layout.tab2,tabHost.getTabContentView());
        //添加第一个标签页
        tabHost.addTab(tabHost.newTabSpec("tab").setIndicator("一号女嘉宾").setContent(R.id.left));
        //添加第二个标签页
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("二号女嘉宾").setContent(R.id.right));


    }
}
