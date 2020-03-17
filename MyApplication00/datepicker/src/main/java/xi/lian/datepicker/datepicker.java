package xi.lian.datepicker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class datepicker extends Activity {
    int year,month,day;
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);
        datePicker=(DatePicker) findViewById(R.id.datepicker);
        //创建日历对象
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        //初始化日历
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                datepicker.this.year=year;
                datepicker.this.month=monthOfYear;
                datepicker.this.day=dayOfMonth;
                show(year,monthOfYear,dayOfMonth);
            }
        });
    }
    private void show(int year,int month,int day){
        String str=year+"年"+(month+1)+"月"+day+"日";
        Toast.makeText(datepicker.this,str,Toast.LENGTH_SHORT).show();
    }
}
