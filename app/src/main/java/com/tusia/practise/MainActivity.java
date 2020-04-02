package com.tusia.practise;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.tusia.practise.model.Item;
import com.tusia.practise.service.ItemService;
import com.tusia.practise.model.DaoMaster;
import com.tusia.practise.model.DaoSession;
import com.tusia.practise.ui.main.SectionsPagerAdapter;

import org.greenrobot.greendao.database.Database;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onListFragmentInteraction(Item item) {

    }

    @Override
    public void onDeleteClick(Item item) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}