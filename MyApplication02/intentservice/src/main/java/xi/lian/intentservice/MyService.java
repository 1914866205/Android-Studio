
package xi.lian.intentservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

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
        Log.i("Service","Service已创建");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Service","Service已启动");
        new Thread(new Runnable() {
            @Override
            public void run() {
                long endTime=System.currentTimeMillis()+20*1000;//结束时间
                while (System.currentTimeMillis()<endTime){
                    synchronized (this){
                        try {
                            wait(endTime-System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();//开启线程
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.i("Service","Service已停止");
        super.onDestroy();
    }
}
