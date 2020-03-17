package xi.lian.log;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
private static String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //按级别从高到低输出日志信息
        Log.e(TAG,"MR输出的【错误信息】");
        Log.w(TAG,"MR输出的【警告信息】");
        Log.i(TAG,"MR输出的【普通/说明信息】");
        Log.d(TAG,"MR输出的【调试信息】");
        Log.v(TAG,"MR输出的【冗余信息】");

    }
}

