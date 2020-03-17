package xi.lian.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver{
    //动作 1
    private static  final  String ACTION1="小胖";
    //动作 2
    private static  final  String ACTION2="倪涛涛";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION1)){//回复第一个广播
            Toast.makeText(context, "MyReceiver收到，小胖的广播", Toast.LENGTH_SHORT).show();
        }else if (intent.getAction().equals(ACTION2)){//回复第二个广播
            Toast.makeText(context, "MyReceiver收到，倪涛涛的广播", Toast.LENGTH_SHORT).show();
        }

    }
}
