package xi.lian.demo2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class demo2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        TextView textView=findViewById(R.id.wangmima);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //连接新页面
                Intent intent=new Intent(demo2.this,passwordActivity.class);
                //启动新页面
                startActivity(intent);
            }
        });
    }

}
