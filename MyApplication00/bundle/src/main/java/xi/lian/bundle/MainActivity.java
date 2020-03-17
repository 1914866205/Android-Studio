package xi.lian.bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String site1=((EditText)findViewById(R.id.dizhi)).getText().toString();
                String site2=((EditText)findViewById(R.id.dizhi1)).getText().toString();
                String site3=((EditText)findViewById(R.id.dizhi2)).getText().toString();
                String phone=((EditText)findViewById(R.id.tel)).getText().toString();
                String name=((EditText)findViewById(R.id.xm)).getText().toString();
                String mail=((EditText)findViewById(R.id.mail)).getText().toString();
                if (!"".equals(site1)&&!"".equals(site2)&&!"".equals(site3)&&!"".equals(name)&&
                        !"".equals(mail)){
                    Intent intent=new Intent(MainActivity.this,AddressActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putCharSequence("name",name);
                    bundle.putCharSequence("phone",phone);
                    bundle.putCharSequence("site1",site1);
                    bundle.putCharSequence("site2",site2);
                    bundle.putCharSequence("site3",site3);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "请将收货地址填写完", Toast.LENGTH_SHORT).show();
                }
            }
        });
}
}