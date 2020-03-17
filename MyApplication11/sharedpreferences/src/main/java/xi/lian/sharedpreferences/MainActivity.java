package xi.lian.sharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 *SharedPreferences存储数据的步骤
 *
 * 1  获取SharedPreferences对象
 * 		getSharedPreferences(String name,int mode)
 * 		getPreferences(int mode)
 * 		name是SharedPreferences文件名
 * 		mode分为
 * 			MODE_PRIVATE 被本应用读写
 * 			MODE_MULTI_PROCESS 可跨应用读写
 *
 * 2  获得SharedPreferences.Editor对象
 * 		通过 edit()
 *
 * 3  像SharedPreferences.Editor对象中添加数据
 * 		put+数据类型
 *
 * 		如 putBoolean()
 * 		   putString()
 * 		   putInt()
 *
 * 4  提交数据
 * 	commit()
 *
 *
 *
 * SharedPreferences读取数据的步骤
 *
 * 1  获取SharedPreferences对象
 * 		getSharedPreferences(String name,int mode)
 * 		getPreferences(int mode)
 * 		name是SharedPreferences文件名
 * 		mode分为
 * 			MODE_PRIVATE 被本应用读写
 * 			MODE_MULTI_PROCESS 可跨应用读写
 *
 *
 * 2 使用SharedPreferences类提供的getXxx()方法读取数据
 * 				getBoolean()
 * 				getString()
 * 				getInt()
 *
 */

public class MainActivity extends Activity {
private String mr="mr";
private String mrsoft="mrsoft";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText username=findViewById(R.id.usernumber);
        final EditText password=findViewById(R.id.password);
        Button login=findViewById(R.id.login);
        //获取SharedPreferences对象
        final SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
        //实现自动登录功能
        //获取账号信息
        String username1=sp.getString("username",null);
        //获取密码信息
        final String password1=sp.getString("password",null);
        if (username1!=null&&password1!=null){
            if (username.equals(mr)&&password1.equals(mrsoft)){
                Intent intent=new Intent(MainActivity.this,Path.Direction.class);
                startActivity(intent);
            }else{
                //实现手动登录并储存账号和密码
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //获取输入的账号和密码
                        String in_username=username.getText().toString();
                        String in_password=password.getText().toString();
                        //获取edit对象
                        SharedPreferences.Editor editor=sp.edit();
                        if (in_username.equals(mr)&&in_password.equals(mrsoft)){
                            editor.putString("username",in_username);//保存账号
                            editor.putString("password",in_password);//保存密码
                            editor.commit();//提交信息
                            Intent intent=new Intent(MainActivity.this,Path.Direction.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "已保存账号和密码", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

    }
}
