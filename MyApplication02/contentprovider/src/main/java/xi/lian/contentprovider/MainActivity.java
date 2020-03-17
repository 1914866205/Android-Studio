package xi.lian.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//创建和使用Content Provider的步骤
/*
* 1 继承ContentProvider类
* 2 声明 contentPrivuaer  AndroidManifest.xml
* 3 使用ContentProvider
* */
public class MainActivity extends Activity {
    ///获得通讯录的姓名的序列
    private  String column=ContactsContract.Contacts.DISPLAY_NAME;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=findViewById(R.id.text);
        textView.setText(getQueryData());//显示通讯录信息
    }

           //返回类型是字符序列
    private  CharSequence getQueryData() {
        //定义一个字符串构建器,用于保存获取的联系人
        StringBuilder stringBuilder = new StringBuilder();
        //获得ContentResolve对象
        ContentResolver contentResolver=getContentResolver();
        //查询通讯录中的数据
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Cursor cursor=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null);
            //获得姓名记录的索引值
            int displayNameIndex=cursor.getColumnIndex(column);
            for (cursor.moveToNext();!cursor.isAfterLast();cursor.moveToNext()){
                String displayName=cursor.getString(displayNameIndex);
                stringBuilder.append(displayName+"\n");
            }
            cursor.close();//关闭记录集
        }
        return  stringBuilder.toString();//返回查询结果
    }
}
