package xi.lian.cengjishidaohang;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
public class FriendActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendlayout);
        //判断父Activity是否为空，不为空则设置导航图标显示
        if (NavUtils.getParentActivityName(FriendActivity.this)!=null){
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }
}
