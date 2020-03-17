package xi.lian.dbopenhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
    //创建数据表的SQL语句
    final String CREATE_TABLE_SQL="create table tb_dict (_id integer primary key autoincrement,word,detail)";
    public DBOpenHelper(Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建单词的数据表
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("词典","--版本更新"+oldVersion+"-->"+newVersion);
    }
}
