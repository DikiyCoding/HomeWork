package example.homework.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import example.homework.R;
import example.homework.fragments.HomeFragment;
import example.homework.fragments.ListFragment;
import example.homework.fragments.PagesFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    private Fragment home, list, pages;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(home);
                    return true;
                case R.id.navigation_list:
                    loadFragment(list);
                    return true;
                case R.id.fragment_pages:
                    loadFragment(pages);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        home = new HomeFragment();
        list = new ListFragment();
        pages = new PagesFragment();

        loadFragment(home);
    }
}
