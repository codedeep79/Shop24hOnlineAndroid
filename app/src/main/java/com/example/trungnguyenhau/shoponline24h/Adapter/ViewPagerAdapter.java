package com.example.trungnguyenhau.shoponline24h.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.trungnguyenhau.shoponline24h.Fragment.FragmentChuongTrinhKhuyenMai;
import com.example.trungnguyenhau.shoponline24h.Fragment.FragmentDienTu;
import com.example.trungnguyenhau.shoponline24h.Fragment.FragmentLamDep;
import com.example.trungnguyenhau.shoponline24h.Fragment.FragmentMeVaBe;
import com.example.trungnguyenhau.shoponline24h.Fragment.FragmentNhaCuaVaDoiSong;
import com.example.trungnguyenhau.shoponline24h.Fragment.FragmentNoiBat;
import com.example.trungnguyenhau.shoponline24h.Fragment.FragmentTheThaoVaDuLich;
import com.example.trungnguyenhau.shoponline24h.Fragment.FragmentThoiTrang;
import com.example.trungnguyenhau.shoponline24h.Fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TRUNGNGUYENHAU on 4/13/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        listFragment.add(new FragmentNoiBat());
        listFragment.add(new FragmentChuongTrinhKhuyenMai());
        listFragment.add(new FragmentDienTu());
        listFragment.add(new FragmentNhaCuaVaDoiSong());
        listFragment.add(new FragmentMeVaBe());
        listFragment.add(new FragmentLamDep());
        listFragment.add(new FragmentThoiTrang());
        listFragment.add(new FragmentTheThaoVaDuLich());
        listFragment.add(new FragmentThuongHieu());

        titleFragment.add("Nổi bật");
        titleFragment.add("Chương trình khuyến mãi");
        titleFragment.add("Điện tử");
        titleFragment.add("Nhà cửa & đời sống");
        titleFragment.add("Mẹ và bé");
        titleFragment.add("Làm đẹp");
        titleFragment.add("Thời trang");
        titleFragment.add("Thể thao & du lịch");
        titleFragment.add("Thương hiệu");
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
