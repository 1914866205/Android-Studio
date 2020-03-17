package xi.lian.startactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import xi.lian.startactivityforresult.R;

public class HeadActivity extends Activity {
  public int []images= new int[]{
           R.mipmap.bj,R.mipmap.girl,R.mipmap.oo
   };
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head);
        GridView gridView=findViewById(R.id.gridView);
        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }



            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if(convertView==null){
                    imageView=new ImageView(HeadActivity.this);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(150);
                    imageView.setMaxWidth(158);
                    imageView.setPadding(5,5,5,5);
                }else{
                    imageView= (ImageView) convertView;
                }
                imageView.setImageResource(images[position]);
                return  imageView;
            }
        };
        gridView.setAdapter(adapter);
        //添加一个选项被点击的适配器
        gridView.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=getIntent();
                Bundle bundle=new Bundle();
                bundle.putInt("image",images[position]);
                intent.putExtras(bundle);
                //设置返回码和上一个请求码一样
                setResult(0x11,intent);
                finish();
            }
        });
    }

}
