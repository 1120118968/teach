package com.example.teacher;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
public class TaskActivity extends AppCompatActivity {
    private BottomNavigationBar bottomNavigationBar;
    private TextBadgeItem badgeItem1;
    private TextBadgeItem badgeItem2;
    private TextBadgeItem badgeItem3;
    private TextBadgeItem badgeItem4;
    private TextBadgeItem badgeItem5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        badgeItem1 = new TextBadgeItem().setHideOnSelect(true);
        badgeItem2 = new TextBadgeItem().setHideOnSelect(true);
        badgeItem3 = new TextBadgeItem().setHideOnSelect(true);
        badgeItem4 = new TextBadgeItem().setHideOnSelect(true);
        badgeItem5 = new TextBadgeItem().setHideOnSelect(true);
        bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "全部").setBadgeItem(badgeItem1))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "未提交").setBadgeItem(badgeItem2))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "待批改").setBadgeItem(badgeItem3))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"被打回").setBadgeItem(badgeItem4))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"已批改").setBadgeItem(badgeItem5))
                .initialise();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.i("tab", "onTabSelected position" + position);
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });
    }
}

