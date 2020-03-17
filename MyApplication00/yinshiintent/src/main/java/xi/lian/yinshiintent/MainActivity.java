package xi.lian.yinshiintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                //隐式Intent为它设置data属性和Action属性
                intent.setAction(intent.ACTION_VIEW);
                intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setData(Uri.parse("https://www.bilibili.com/video/av22836860/?p=86"));
                startActivity(intent);
            }
        });
    }
}
