package example.homework;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import example.homework.dialogs.EditDialog;
import example.homework.fragments.GalleryFragment;
import example.homework.fragments.ProfileFragment;
import example.homework.fragments.SlideshowFragment;
import example.homework.fragments.ToolsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private FragmentTransaction transaction;
    private EditDialog dialog;
    private ProfileFragment profile;
    private Fragment gallery, slideshow, tools;
    private TextView tvNavLogin, tvNavEmail;

    public static String login, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dialog = new EditDialog();

        gallery = new GalleryFragment();
        profile = new ProfileFragment();
        slideshow = new SlideshowFragment();
        tools = new ToolsFragment();

        View header = navigationView.getHeaderView(0);
        tvNavLogin = header.findViewById(R.id.header_login);
        tvNavEmail = header.findViewById(R.id.header_email);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        transaction = getFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_profile:
                transaction.replace(R.id.container, profile);
                break;
            case R.id.nav_gallery:
                transaction.replace(R.id.container, gallery);
                break;
            case R.id.nav_slideshow:
                transaction.replace(R.id.container, slideshow);
                break;
            case R.id.nav_tools:
                transaction.replace(R.id.container, tools);
        }
        transaction.addToBackStack(null);
        transaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void click(View view) {
        switch(view.getId()) {
            case R.id.btn_edit:
                dialog.show(getFragmentManager(), "dialog");
                break;
            case R.id.btn_confirm:
                dialog.getInfo();
                if(!login.equals("") || !email.equals("")) {
                    profile.setInfo(login, email);
                    tvNavLogin.setText(login);
                    tvNavEmail.setText(email);
                }
            case R.id.btn_cancel:
                dialog.dismiss();
        }
    }
}
