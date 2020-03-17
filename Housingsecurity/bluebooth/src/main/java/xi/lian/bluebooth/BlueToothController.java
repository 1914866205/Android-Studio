package xi.lian.bluebooth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;

//蓝牙适配器
public class BlueToothController {
    //创建蓝牙适配器
    private BluetoothAdapter mAdapter;

    public BlueToothController(){
        //如果返回值为空，则该设备不支持蓝牙
        mAdapter=BluetoothAdapter.getDefaultAdapter();
    }

    //是否支持蓝牙
    public  boolean isSupportBlueTooth(){
        if (mAdapter!=null){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 判断当前蓝牙状态
     *      true 打开
     *      false 关闭
     */

    @SuppressLint("MissingPermission")
    public boolean getBlueToothStatus(){
        //断言
        assert (mAdapter!=null);
        return  mAdapter.isEnabled();
    }

    //打开蓝牙
    public void turnOnBlueTooth(Activity activity,int requestcode){
        Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(intent, requestcode);
    }

    /*
    * 打开蓝牙可见性
    * */
    public void enabkeVisibly(Context context){
        Intent discoverableIntent=new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300 );
//        context.startActivities(discoverableIntent);
        context.startActivities(new Intent[]{discoverableIntent});
    }

    /*
    * 查找设备
    * */
    @SuppressLint("MissingPermission")
    public void findDevice(){
        assert (mAdapter!=null);
        mAdapter.startDiscovery();
    }
    //关闭蓝牙
    @SuppressLint("MissingPermission")
    public void turnOffBlueTooth() {
        mAdapter.disable();
    }
}
