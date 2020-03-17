package xi.lian.resource;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class detail2Activity extends Activity {
    int []tvid={
            R.id.t1,
            R.id.t2,
            R.id.t3,
            R.id.t4,
            R.id.t5,
            R.id.t6,
            R.id.t7,
            R.id.t8,
            R.id.t9,
    };
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail2layout);
        int[]color=getResources().getIntArray(R.array.bgcolor);
        String[]word=getResources().getStringArray(R.array.word);
        for(int i=0;i<word.length;i++){
            TextView textView=findViewById(tvid[i]);
            textView.setBackgroundColor(color[i]);
            textView.setText(word[i]);
        }

    }
}
