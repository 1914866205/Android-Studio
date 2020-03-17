package xi.lian.bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AddressActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xinxi);
        //获取Intent对象
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String name=bundle.getString("name");
        String phone=bundle.getString("phone");
        String site=bundle.getString("site1")+bundle.getString("site2")+bundle.getString("site3");
        TextView text_name=findViewById(R.id.name);
        TextView text_phone=findViewById(R.id.dianhua);
        TextView text_site=findViewById(R.id.site);
        text_name.setText(name);
        text_phone.setText(phone);
        text_site.setText(site);

    }
}
