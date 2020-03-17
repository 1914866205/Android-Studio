package xi.lian.ratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class Ratbing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratbing);
        RatingBar rb=findViewById(R.id.rb);
        //获取选中几颗星
        //把Float类型转化为字符串类型
        String rating=String.valueOf(rb.getRating());
        Toast.makeText(Ratbing.this,"Rating:"+rating,Toast.LENGTH_SHORT).show();
        //每次至少改变几颗星
        String stepSize=String.valueOf(rb.getStepSize());
        Toast.makeText(Ratbing.this,"StepSize:"+stepSize,Toast.LENGTH_SHORT).show();
        //获取进度
        String progress=String.valueOf(rb.getProgress());
        Toast.makeText(this, "Progress:"+progress, Toast.LENGTH_SHORT).show();
    }
}
