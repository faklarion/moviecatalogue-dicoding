package com.studio.faisal.moviecatalogue2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.studio.faisal.moviecatalogue2.fragmentbottomnav.FragmentFav;
import com.studio.faisal.moviecatalogue2.fragmentbottomnav.FragmentFilm;
import com.studio.faisal.moviecatalogue2.fragmentbottomnav.FragmentTv;
import com.studio.faisal.moviecatalogue2.searchview.carimovie;
import com.studio.faisal.moviecatalogue2.searchview.caritv;
import com.studio.faisal.moviecatalogue2.setting.SettingNotif;


public class MainActivity extends AppCompatActivity {

    FloatingActionMenu fabmenu;
    FloatingActionButton fabmovie, fabtv;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    {
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new FragmentFilm();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                                .commit();
                        return true;
                    case R.id.navigation_dashboard:
                        fragment = new FragmentTv();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                                .commit();
                        return true;
                    case R.id.navigation_notifications:
                        fragment = new FragmentFav();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                                .commit();
                        return true;
                }
                return false;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null){
            navigation.setSelectedItemId(R.id.navigation_home);
        }
        fabmenu = findViewById(R.id.fabmenu);
        fabmovie = findViewById(R.id.fabmovie);
        fabtv = findViewById(R.id.fabtv);
        fabmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, carimovie.class);
                startActivity(intent);
             }
        });
        fabtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, caritv.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            gantiBahasa();
        } else if (item.getItemId() == R.id.setting_notif) {
            settingNotif();
        }
        return super.onOptionsItemSelected(item);
    }

    public void gantiBahasa() {
        Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(mIntent);
    }
    public void settingNotif(){
        Intent intent = new Intent(this, SettingNotif.class);
        startActivity(intent);
    }

}
