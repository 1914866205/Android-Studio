package xi.lian.actionbar_tab;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;
//实现各标签页的切换
public class myTap implements ActionBar.TabListener {
    private final Activity activity;//指定要加载Fragment的Activity
    private  final  Class aClass;//用于指定要加载的Fragment所对应的类
     Fragment fragment;//定义Fragment对象
    //初始化
    public myTap(Activity activity,Class aClass){
        this.activity=activity;
        this.aClass=aClass;
    }

    //被选择时
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //先判断Fragment有没有被初始化
        if (fragment==null){
            //反射机制初始化
            fragment=Fragment.instantiate(activity,aClass.getName());
            //把要显示的Fragment添加到Fragment实例中
            fragmentTransaction.add(R.id.content,fragment,null);
        }
        //显示新界面
        fragmentTransaction.attach(fragment);
    }

    //退出选中状态
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (fragment!=null){
            fragmentTransaction.detach(fragment);//删除旧页面
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
