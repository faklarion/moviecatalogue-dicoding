package com.studio.faisal.moviecatalogue2.setting;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.studio.faisal.moviecatalogue2.R;
import com.studio.faisal.moviecatalogue2.reminder.DailyAlarmReceiver;
import com.studio.faisal.moviecatalogue2.reminder.UpcomingAlarmReceiver;


public class SettingNotif extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_notif);
        final DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();
        final UpcomingAlarmReceiver upcomingAlarmReceiver = new UpcomingAlarmReceiver();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.settingnotif));
        }
        Switch switch1 = findViewById(R.id.switch1);
        Switch switch2 = findViewById(R.id.switch2);
        switch1.setChecked(true);
        switch2.setChecked(true);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dailyAlarmReceiver.setRepeatingAlarm(getApplicationContext());
                } else {
                    dailyAlarmReceiver.cancelAlarm(getApplicationContext());
                }
            }
        });

                if (switch1.isChecked()) {
                    dailyAlarmReceiver.setRepeatingAlarm(getApplicationContext());
                } else {
                    dailyAlarmReceiver.cancelAlarm(getApplicationContext());
                }
         switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     upcomingAlarmReceiver.setRepeatingUpcomingAlarm(getApplicationContext());
                 } else {
                     upcomingAlarmReceiver.cancelAlarm(getApplicationContext());
                 }
             }
         });
                if (switch2.isChecked()) {
                    upcomingAlarmReceiver.setRepeatingUpcomingAlarm(getApplicationContext());
                } else {
                    upcomingAlarmReceiver.cancelAlarm(getApplicationContext());
                }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
