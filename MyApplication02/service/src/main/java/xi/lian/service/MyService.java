package xi.lian.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");





    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(isRunning()){
                    //输出变量i的值
                    Log.i("Service",String.valueOf(++i));
                    //线程休眠一秒钟
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
    //判断Service是否正在运行
    public boolean isRunning(){
        ActivityManager activityManager= (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        //获取所有正在运行的Service
        ArrayList<ActivityManager.RunningServiceInfo> runningServiceInfos= (ArrayList<ActivityManager.RunningServiceInfo>) activityManager.getRunningServices(100);
             for (int i=0;i<runningServiceInfos.size();i++){
                    if (runningServiceInfos.get(i).service.getClassName().toString().equals("xi.lian.service.MyService")){
                        return  true;
                    }
             }
             return  false;
    }
}
