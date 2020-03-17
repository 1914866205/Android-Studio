package xi.lian.beiwanglu;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
File file;//声明一个File类用来保存外部存储文件
    int type=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save=findViewById(R.id.save);
        Button canel=findViewById(R.id.canel);
        Button saveOut=findViewById(R.id.save2);
        final EditText editText=findViewById(R.id.edit);
        //内部存储
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存当前的信息
                type=1;
                FileOutputStream fos=null;
                String test=editText.getText().toString();
                try {
                    fos=openFileOutput("日记",MODE_PRIVATE);
                    fos.write(test.getBytes());
                    fos.flush();//清除缓存
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (fos != null) {
                        try {
                            fos.close();
                            Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });


        //外部存储
        file=new File(Environment.getExternalStorageDirectory(),"日记");
        saveOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type =2;
                //保存当前的信息
                FileOutputStream fos=null;
                String test=editText.getText().toString();
                try {
                    fos= new FileOutputStream(file);
                    fos.write(test.getBytes());
                    fos.flush();//清除缓存
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (fos != null) {
                        try {
                            fos.close();
                            Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        switch (type){
            case 1:
                //读取保存到备忘信息
                FileInputStream fis=null;
                byte[]bys=null;
                try {
                    fis=openFileInput("备忘录");
                    bys=new byte[fis.available()];
                    fis.read(bys);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (fis!=null){
                        try {
                            fis.close();
                            String data=new String(bys);
                            editText.setText(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case 2:
                //读取保存的备忘信息
                FileInputStream fis2=null;
                byte[]bys2=null;
                try {
                    fis2= new FileInputStream(file);
                    bys2=new byte[fis2.available()];
                    fis2.read(bys2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (fis2!=null){
                        try {
                            fis2.close();
                            String data=new String(bys2);
                            editText.setText(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
        canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//退出当前程序
            }
        });
    }
}
