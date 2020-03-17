package xi.lian.dbopenhelper;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
        private  DBOpenHelper dbOpenHelper;//声明DBOpenHelper对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化数据库                        上下文对象 数据库名称 游标工厂 版本号
        dbOpenHelper=new DBOpenHelper(MainActivity.this,"db+dict",null,1);

        final ListView listView=findViewById(R.id.list);
        final EditText etSearch=findViewById(R.id.word);
        Button search=findViewById(R.id.search);
        Button add=findViewById(R.id.add);

        //添加生词
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,addActivity.class);
                startActivity(intent);
            }
        });

        //查询功能
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=etSearch.getText().toString();
                Cursor cursor=dbOpenHelper.getReadableDatabase().query("tb_dict",null,"word=?",new String[]{key},null,null,null);
                ArrayList<Map<String,String>> resultlist=new ArrayList<Map<String, String>>();
                while (cursor.moveToNext()){
                    Map<String,String>map=new HashMap<String, String>();
                    map.put("word",cursor.getString(1));
                    map.put("interpret",cursor.getString(2));
                    resultlist.add(map);
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbOpenHelper!=null){
            dbOpenHelper.close();//关闭数据库连接
        }
    }
}
