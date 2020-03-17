package xi.lian.seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

public class SeekBar1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar1);
        SeekBar seekBar=findViewById(R.id.sb);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //进度改变执行的的方法
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(SeekBar1.this, "进度改变",Toast.LENGTH_LONG).show();
            }
            //开始触摸时执行的方法
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(SeekBar1.this,"开始触摸",Toast.LENGTH_LONG).show();
            }
            //停止触摸时执行的方法
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(SeekBar1.this,"停止触摸",Toast.LENGTH_LONG).show();

            }
        });
    }
}
