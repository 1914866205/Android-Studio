package xi.lian.alterdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //显示带取消和确认按钮的对话框
        final Button button=findViewById(R.id.bottom1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建AlterDialog对象
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setIcon(R.drawable.qq);//设置图标
                alertDialog.setTitle("删除好友");//设置标题
                alertDialog.setMessage("是否确认删除？");//设置内容
                //添加取消按钮
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "取消删除", Toast.LENGTH_SHORT).show();
                    }
                });

                //添加确定按钮
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                });
                //显示对话框
                alertDialog.show();
            }
        });

        //显示带4个列表项的列表的对话框
        Button button2=findViewById(R.id.bottom2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带4个列表项的列表对话框
                //名言数组
                final String [] items={
                        "如果有一天，",
                        "你不再寻找爱情，只是去爱；",
                        "你不再渴望成功，只是去做；",
                        "你不再追求成长，只是去修；",
                        "一切才真正开始。",
                             "                                  ————————纪伯伦"
                };
                //创建一个AlterDialog.Builder对象
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                //设置图标
                builder.setIcon(R.drawable.massage);
                //设置标题
                builder.setTitle("请选择你喜欢的名言");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "你选择了【"+items[which]+"】", Toast.LENGTH_LONG).show();
                    }
                });
                //创建并显示对话框
                builder.create().show();

            }

        });

        //显示带单选列表的对话框
        Button button3=findViewById(R.id.bottom3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带单选列表项的对话框
                //角色数组
                final String [] items=new String[]{
                  "无双剑姬","无极剑圣","疾风剑豪","暗裔剑魔"
                };
                //创建一个AlterDialog.builer对象
                AlertDialog.Builder alterdialogbuilder=new AlertDialog.Builder(MainActivity.this);
                //设置图标
                alterdialogbuilder.setIcon(R.drawable.lol);
                alterdialogbuilder.setTitle("下局玩哪个？");
                //设置单选列表                            数组   默认选项
                alterdialogbuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "下局玩"+items[which], Toast.LENGTH_SHORT).show();
                    }
                });
                //添加确定按钮
                alterdialogbuilder.setPositiveButton("确定",null);
                //创建对话框
                alterdialogbuilder.create();
                //显示对话框
                alterdialogbuilder.show();

            }
        });

        //显示带多选列表的对话框
        Button button4=findViewById(R.id.bottom4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带多选列表的对话框
                //记录各列表的状态
                 final boolean[]checkedItems=new boolean[]{
                  false,true,false,true,false,true
                };
                //名言数组
                   final String [] items={
                        "如果有一天，",
                        "你不再寻找爱情，只是去爱；",
                        "你不再渴望成功，只是去做；",
                        "你不再追求成长，只是去修；",
                        "一切才真正开始。",
                        "                                  ————————纪伯伦"
                };
                 //创建一个AlterDialog.Builder对象
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                //设置图标
                builder.setIcon(R.drawable.massage);
                //设置标题
                builder.setTitle("请选择你喜欢的句子");
                //创建多选列表
                builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //改变被操作列表项的状态
                        checkedItems[which]=isChecked;
                    }
                });
                //添加确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //记录结果的字符串
                        String result="";
                        for (int i=0;i<checkedItems.length;i++){
                           //把列表的内容添加到result中
                            if (checkedItems[i]){
                                result+=items[i]+",";
                            }
                        }
                        if (!"".equals(result)){
                            Toast.makeText(MainActivity.this, "您选择了【"+result+"】", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                //添加确定按钮
                builder.setPositiveButton("确定",null);
                //创建对话框
                builder.create();
                //显示对话框
                builder.show();
            }
        });
    }
}
