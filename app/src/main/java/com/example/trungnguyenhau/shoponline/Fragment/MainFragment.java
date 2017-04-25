package com.example.trungnguyenhau.shoponline.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trungnguyenhau.shoponline.Adapter.IntroAdapter;
import com.example.trungnguyenhau.shoponline.Model.ProductIntro;
import com.example.trungnguyenhau.shoponline.OnSkipNextListener;
import com.example.trungnguyenhau.shoponline.R;

import java.util.ArrayList;

/**
 * Dùng để quản lý hết các màn hình viewpager, slide
 */
public class MainFragment extends Fragment implements OnSkipNextListener{

    private ArrayList<Fragment> listProductFragments;
    private ArrayList<ProductIntro> listProductIntros;
    private OnSkipNextListener callback;

    private ViewPager viewPager;
    public MainFragment() {

    }

    //Trả về fragment này, tất cả các thành phần trong MainFragment đều trả về
    public static MainFragment newInstance(){
        MainFragment mainFragment = new MainFragment();
        return  mainFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        callback = this;

        // Các listProductFragments chứa trong view
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_main);

        CategoryFragment productSport  = CategoryFragment.newInstance(callback);
        CategoryFragment productPhone  = CategoryFragment.newInstance(callback);
        CategoryFragment productLaptop = CategoryFragment.newInstance(callback);
        LoadDataApplication loadDataApplication = LoadDataApplication.newInstance();

        // Danh sách các fragment
        listProductFragments = new ArrayList<>();
        listProductFragments.add(productSport);
        listProductFragments.add(productPhone);
        listProductFragments.add(productLaptop);
        listProductFragments.add(loadDataApplication);

        listProductIntros = new ArrayList<>();
        listProductIntros.add(new ProductIntro(getString(R.string.ic_sport_title)
                , getString(R.string.ic_sport_description)
                    , R.drawable.icon_sport));

        listProductIntros.add(new ProductIntro(getString(R.string.ic_phone_title)
                , getString(R.string.ic_phone_description)
                , R.drawable.icon_phone));

        listProductIntros.add(new ProductIntro(getString(R.string.ic_laptop_title)
                , getString(R.string.ic_laptop_description)
                , R.drawable.icon_laptop));


        // Adapter chứa các danh sách fragment
        IntroAdapter introAdapter = new IntroAdapter(getFragmentManager(), listProductFragments, listProductIntros);
        // Quản lý các fragment của intro.
        viewPager.setAdapter(introAdapter);
        return view;
    }

    // Nơi nào chứa ViewPager để tiến hành đẩy, lùi viewpager => MainFragment.
    // Thực thi interface trong MainFragment
    @Override
    public void onSkipListener() {
        viewPager.setCurrentItem(4, true);
    }

    @Override
    public void onNextListener(int currentPage) {
        viewPager.setCurrentItem(currentPage + 1);
    }
}
