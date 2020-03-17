package xi.lian.simple_adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private int[]pucture=new int[]{R.mipmap.bj,R.mipmap.oo,R.mipmap.aa,
            R.mipmap.ab,R.mipmap.ac,R.mipmap.ad,R.mipmap.af};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView=findViewById(R.id.gridview);
        List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        for (int i=0;i<pucture.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("image",pucture[i]);
            list.add(map);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,list,R.layout.cell,new String[]{"image"});
        gridView.setAdapter(simpleAdapter);

    }
}
