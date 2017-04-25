package com.example.trungnguyenhau.shoponline.Views.Home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.trungnguyenhau.shoponline.Adapter.ViewPagerAdapter;
import com.example.trungnguyenhau.shoponline.R;
import com.example.trungnguyenhau.shoponline.Views.DangNhap_DangKy.LoginActivity;

public class HomeActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
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
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);


        toolbar.setTitle("");
        // Tại vì đang sài gói thư viện hỗ trợ nên setSupportActionBar thì mới hiện toolbar
        setSupportActionBar(toolbar);


        // actionBarDrawerToggle phải để dưới toolbar thì mới đổi màu actionBarDrawerToggle vì
        // actionBarDrawerToggle ghi đè
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        // Thêm actionBarDrawerToggle vào drawerLayout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // Vì mình sẽ lây component trên actionBar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();


        /*progressDialog = new ProgressDialog(HomeActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang tải vui lòng chờ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();*/

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        // Gắn tab thương hiệu trên trang chủ
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Nếu actionBarDrawerToggle được click trên menu thì mở ra
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

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
