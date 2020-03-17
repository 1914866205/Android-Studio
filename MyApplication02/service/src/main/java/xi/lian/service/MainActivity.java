package xi.lian.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button start=findViewById(R.id.start);
       final Intent intent=new Intent(MainActivity.this,MyService.class);

        Button stop=findViewById(R.id.stop);


        //启动servce
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);//启动service
            } 
        });
        //停止servce
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);//停止service
            }
        });

    }
}
