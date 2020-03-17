package xi.lian.phone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ImageButton imagephone=findViewById(R.id.phone01);
         ImageButton imagesms=findViewById(R.id.sms01);
       //为监听器对象设置事件
        imagephone.setOnClickListener(l);
        imagesms.setOnClickListener(l);
    }
    View.OnClickListener l=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            ImageButton imageButton= (ImageButton) v;
            switch (imageButton.getId()){
                case R.id.phone01:
                    //拨打电话
                    intent.setAction(intent.ACTION_DIAL);
                     intent.setData(Uri.parse("tel:18851855106"));
                     startActivity(intent);
                    Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
                     break;
                case R.id.sms01:
                    //发送短信
                    intent.setAction(intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:18851855106"));
                    //设置默认内容
                    Toast.makeText(MainActivity.this, "222", Toast.LENGTH_SHORT).show();
                    intent.putExtra("sms_body","短信内容");
                    startActivity(intent);
                    break;
            }
        }
    };
}
