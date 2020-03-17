package xi.lian.dbopenhelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.locks.Condition;

public class addActivity extends Activity {
    private  DBOpenHelper dbOpenHelper;//声明DBOpenHelper对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addlayout);
        //实例化数据库                        上下文对象 数据库名称 游标工厂 版本号
        dbOpenHelper=new DBOpenHelper(addActivity.this,"db+dict",null,1);
       final EditText etWord=findViewById(R.id.addword);
       final EditText etInterpret=findViewById(R.id.addinterpret);
        Button btn_Save=findViewById(R.id.save);
        Button btn_Canel=findViewById(R.id.canel);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word=etWord.getText().toString();
                String interpret=etInterpret.getText().toString();
                if (word.equals("")||interpret.equals("")){
                    Toast.makeText(addActivity.this, "填写的单词或解释为空", Toast.LENGTH_SHORT).show();
                }else{
                    insertData(dbOpenHelper.getReadableDatabase(),word,interpret);
                    Toast.makeText(addActivity.this, "添加单词成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_Canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //插入数据的方法
    private  void insertData(SQLiteDatabase sqLiteDatabase,String word,String interpret){
        ContentValues values=new ContentValues();
        values.put("word",word);//保存单词
        values.put("detail",interpret);//保存解释
        sqLiteDatabase.insert("tb_dict",null,values);//执行插入操作
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbOpenHelper!=null){
            dbOpenHelper.close();//关闭数据库连接
        }
    }
}
