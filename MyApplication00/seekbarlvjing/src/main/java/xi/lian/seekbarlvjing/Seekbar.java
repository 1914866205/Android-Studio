package xi.lian.seekbarlvjing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import xi.lian.seekbarlvjing.R;

public class Seekbar extends AppCompatActivity {
    ImageView imageView;
    SeekBar seekBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_barlvjing);
        imageView=findViewById(R.id.iv);
         seekBar=findViewById(R.id.sb);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //进度改变执行的的方法
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                imageView.setImageAlpha(progress);
            }
            //开始触摸时执行的方法
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //停止触摸时执行的方法
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
