 package xi.lian.fragment;

         import android.support.v4.app.Fragment;
         import android.support.v4.app.FragmentTransaction;
         import android.support.v7.app.AppCompatActivity;
         import android.os.Bundle;

 public class MainActivity extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         detailfragment detailfragment=new detailfragment();
         FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
         ft.add(R.id.fl, detailfragment);
         ft.commit();
     }
 }
