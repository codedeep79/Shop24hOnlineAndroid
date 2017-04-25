package com.example.trungnguyenhau.shoponline.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.trungnguyenhau.shoponline.Model.ProductIntro;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by TRUNGNGUYENHAU on 4/12/2017.
 */

public class IntroAdapter extends FragmentStatePagerAdapter {

    // Chứa danh sách các fragment có chung cấu trúc
    private ArrayList<Fragment> listFragments;
    private ArrayList<ProductIntro> productIntro;

    public IntroAdapter(FragmentManager fm, ArrayList<Fragment> listFragments, ArrayList<ProductIntro> productIntro) {
        super(fm);
        this.listFragments = listFragments;
        this.productIntro = productIntro;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = listFragments.get(position);

        // Nếu không sẽ bị lỗi vì có 1 cái không theo cấu trúc chung
        if (position < listFragments.size() - 1)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putSerializable("product",(Serializable) productIntro.get(position));
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    // Trả về số fragment
    @Override
    public int getCount() {
        return listFragments.size();
    }
}
