package com.example.trungnguyenhau.shoponline24h.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.trungnguyenhau.shoponline24h.Adapter.ViewPagerAdapter;
import com.example.trungnguyenhau.shoponline24h.R;

public class HomeActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addControls();
        addEvents();

    }

    private void addEvents() {

    }

    private void addControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        toolbar.setTitle("");
        // Tại vì đang sài gói thư viện hỗ trợ nên setSupportActionBar
        setSupportActionBar(toolbar);
        /*progressDialog = new ProgressDialog(HomeActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang tải vui lòng chờ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();*/

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itDangNhap:
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.itDangXuat:

                break;

            case R.id.itSearch:

                break;


        }

        return true;
    }
}
