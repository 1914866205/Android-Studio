package xi.lian.aliiarmmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlarmActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化对话框
        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        //设置对话框的图标
        alertDialog.setIcon(R.drawable.massage);
        //设置对话框的标题
        alertDialog.setTitle("闹钟5");
        //设置要显示的内容
        alertDialog.setMessage("快起床学习了");
        //添加确定按钮
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //显示对话框
        alertDialog.show();
    }
}
