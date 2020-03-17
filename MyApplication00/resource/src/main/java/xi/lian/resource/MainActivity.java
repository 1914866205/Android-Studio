package xi.lian.resource;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text1);
        textView.setText(getResources().getText(R.string.string2));
        textView.setTextColor(getResources().getColor(R.color.title));
        //或者    但少用
        //    textView.setTextColor(getResources().getColor(R.color.title));
        textView.setBackgroundColor(getResources().getColor(R.color.bg));
        textView.setTextSize(getResources().getDimension(R.dimen.fone1));
        Button button0 = findViewById(R.id.pre);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detail2Activity.class);
                startActivity(intent);
            }
        });
        Button button = findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                startActivity(intent);
            }
        });
        String[] arr = getResources().getStringArray(R.array.array1);
        String str = null;
        for (int i = 0; i < arr.length; i++) {
            str += arr[i] + "  ";
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        ImageView imageView = findViewById(R.id.image);
        //为视图注册上下文菜单
        registerForContextMenu(imageView);
    }

    //重写onCreateOptionMenu方法创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //解析菜单资源文件
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //对各个菜单项进行处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pre1:
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
                startActivity(intent);
                break;
            case R.id.next1:
                Intent intent1 = new Intent(MainActivity.this, detailActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //重写onCreateContextMenu方法添加一个上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu2, menu);
    }

    //重写onContextItemSelected方法，指定各个菜单项被选择是所应做的处理

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.jubap:
                Toast.makeText(this, "不许举报", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.shoucang:
                Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

}
