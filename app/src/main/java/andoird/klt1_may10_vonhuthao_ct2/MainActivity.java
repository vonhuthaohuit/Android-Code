package andoird.klt1_may10_vonhuthao_ct2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();
        bottomNavigationView=(BottomNavigationView)
                findViewById(R.id.bottom_nav_main);
        frameFragment=(FrameLayout)findViewById(R.id.frame_Main);
        loadFragment(new FragmentBai1());
        bottomNavigationView.setOnNavigationItemSelectedListener(new
         BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 Fragment fragment;
                 int id = item.getItemId();
                 int navigationBai1 = R.id.navigation_bai1;
                 int navigationBai2 = R.id.navigation_bai2;
                 if(id == navigationBai1) {
                     loadFragment(new FragmentBai1());
                     return true;
                 }
                 else if (id == navigationBai2){
                     loadFragment(new FragmentBai2());
                     return true;
                 }
                 return false;
             }
         });
    }
    public void loadFragment(androidx.fragment.app.Fragment
                                     fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.frame_Main,fragment);
        ft.commit();
    }
}