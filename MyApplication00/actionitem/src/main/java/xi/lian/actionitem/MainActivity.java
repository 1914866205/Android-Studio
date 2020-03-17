package xi.lian.actionitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //解析菜单资源文件
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //实例化一个MenuInflater对象
        MenuInflater menuInflater=getMenuInflater();
        //解析菜单文件
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
