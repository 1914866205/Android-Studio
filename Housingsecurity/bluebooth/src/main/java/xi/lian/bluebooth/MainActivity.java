package xi.lian.bluebooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  BlueToothController mController=new BlueToothController();
    private Toast mToast;
    //监听蓝牙的状态
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state=intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
            switch (state){
                case BluetoothAdapter.STATE_OFF:
                        showToast("蓝牙打开");
                        break;
                case BluetoothAdapter.STATE_ON:
                        showToast("蓝牙关闭");
                        break;
                case BluetoothAdapter.STATE_TURNING_ON:
                        showToast("蓝牙正在打开");
                        break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                        showToast("蓝牙正在关闭");
                        break;
                default:
                        showToast("未知状态");
                        break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.activity_main);
        initUI();

        IntentFilter filter=new IntentFilter();
        //开始查找
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        //结束查找
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        //查找设备
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        //设备扫描模式改变
        filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        //绑定状态
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);

        registerReceiver(mReceiver,filter );
        mController.turnOnBlueTooth(this,0);
    }
    public void isSupportBlueTooth(View view){
        boolean ret=mController.isSupportBlueTooth();
        showToast("support Bluetooth"+ret);
    }
    public void isBlueToothEnable(View view){
        boolean ret=mController.getBlueToothStatus();
        showToast("Bluetooth enable"+ret);
    }
    public void requestTurnOnBlueTooth(View view){
        mController.turnOnBlueTooth(this, RESULT_CANCELED);
    }
    public void turnOffBlueTooth(View view){
        mController.turnOffBlueTooth();
    }
    private void showToast(String text){
        if (mToast==null){
            mToast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //打开可见
        if (id == R.id.enable_Visiblity) {
            mController.enabkeVisibly(this);
        } else if (id == R.id.find_device) {
            //查找设备
            mAdapter.refresh(mDevicelList);
            mController.findDevice();
            mListView.setOnItemClickListener(bindDeviceClick);
        } else if (id == R.id.bonded_device) {
            //查看绑定设备
            mBondDeviceList = mController.getBonderDeviceList;
            mAdapter.refresh(mBondedDeviceList);
            mListView.setOnItemClickListener(null);
        }
        if (id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_OK){
            showToast("打开成功");
        }else {
            showToast("打开失败");
        }

    }
}
