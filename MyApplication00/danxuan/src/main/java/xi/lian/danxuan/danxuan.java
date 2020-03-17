package xi.lian.danxuan;

import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class danxuan extends AppCompatActivity {
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danxuan);
        Button bt=findViewById(R.id.tj);
        rg=(RadioGroup)findViewById(R.id.rgg) ;
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<rg.getChildCount();i++){
                    RadioButton button=(RadioButton)rg.getChildAt(i);
                    if (button.isChecked()){
                        if (button.getText().equals("D:190斤")){
                            Toast.makeText(danxuan.this,"回答正确",Toast.LENGTH_SHORT).show();
                        }else{
                            AlertDialog.Builder alb=new AlertDialog.Builder(danxuan.this);
                            alb.setMessage("回答错误");
                            alb.setPositiveButton("确定",null).show();
                        }
                        break;
                    }
                }
            }
        });

    }
}
