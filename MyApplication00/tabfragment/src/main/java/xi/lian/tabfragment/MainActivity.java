package xi.lian.tabfragment;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView1=findViewById(R.id.image1);
        ImageView imageView2=findViewById(R.id.image2);
        ImageView imageView3=findViewById(R.id.image3);
        imageView1.setOnClickListener(l);
        imageView2.setOnClickListener(l);
        imageView3.setOnClickListener(l);

    }
    View.OnClickListener l=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //获取Fragment管理器
            FragmentManager fragmentManager=getSupportFragmentManager();
            //开启一个事物，也就是实例化FragmentTransaction对象
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            //初始化Fragment对象
            Fragment f=null;
            //通过switch语句判断单击了哪一个事物
            switch (v.getId()){
                case R.id.image1:
                        f=new wechat_fragment2();
                        break;
                case R.id.image2:
                        f=new wechat_fragment1();
                        break;
                case R.id.image3:
                        f=new wechat_Fragment();
                        break;
                  default:
                      break;
            }
            //替换每次所显示的fragment     所显示的容器，创建的fragment
            fragmentTransaction.replace(R.id.fragment1,f);
            //提交这个事物
            fragmentTransaction.commit();
        }
    };
}
