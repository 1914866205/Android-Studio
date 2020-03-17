package xi.lian.housingsecurity2;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;

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
    //判断蓝牙是否连接
}
