
package xi.lian.statelistdrawlable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
//根据是否输入密码，实现按钮的可用状态
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置主题  必须在setContentView 之前,否则无用
        setTheme(R.style.bgTheme);

        setContentView(R.layout.activity_main);
    final EditText editText=findViewById(R.id.password);
    final Button button=findViewById(R.id.login);
    //给编辑框设置触发事件
    editText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //如果输入不为空，则按钮可用
            if (editText.length()>0){
                button.setEnabled(true);
            }else{
                button.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });

    }
}
