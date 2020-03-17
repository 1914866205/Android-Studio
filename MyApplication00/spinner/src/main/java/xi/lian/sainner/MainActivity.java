package xi.lian.sainner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[]ctype1=new String[]{
                "全部","图片","音频","视频"
        };
        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ctype1);
        //设置下拉时的样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner=findViewById(R.id.sp2);
        spinner.setAdapter(arrayAdapter);
        //获取下拉列表的选中值
        String str=spinner.getSelectedItem().toString() ;
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();

    }
}
