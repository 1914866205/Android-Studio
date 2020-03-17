package xi.lian.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinderService extends Service {
    public BinderService() {
    }

    //创建MyBinder内部类
    public class MyBinder extends Binder {
        //创建获得Service的方法
        public BinderService getSercive() {
            //返回当前的Service类
            return BinderService.this;
        }

        ;
    }

    @Override
    // IBinder是实现Service和绑定它的组件进行实时通信的
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();//返回MyBinder对象
        //删除一行是ctri+y
    }
    //自定义方法用于生成随机数
    public List getRandomNumber(){
        List list=new ArrayList();
        String strNumber="";//用于保存生成的随机数
        for (int i=0;i<7;i++){
            //生成1-33之间的随机整数
            int number=new Random().nextInt(33)+1;
            if (number<10){
                //如果是个位数，则在十位上补0
                strNumber="0"+String.valueOf(number);
            }else {
                strNumber=String.valueOf(number);
            }
            //把随机数添加到集合
            list.add(strNumber);
        }
        return list;
    }

    @Override
    //销毁Service
    public void onDestroy() {
        super.onDestroy();
    }
}