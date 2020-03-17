package xi.lian.timepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class timePicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_ticker);
        android.widget.TimePicker t=findViewById(R.id.timepicker);
        //设置为24小时制
        t.setIs24HourView(true);
        t.setOnTimeChangedListener(new android.widget.TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(android.widget.TimePicker view, int hourOfDay, int minute) {
                String str=hourOfDay+"时"+minute+"分";
                Toast.makeText(timePicker.this,str,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
