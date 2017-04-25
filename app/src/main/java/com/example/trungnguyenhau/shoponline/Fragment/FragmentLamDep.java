package com.example.trungnguyenhau.shoponline.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trungnguyenhau.shoponline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLamDep extends Fragment {


    public FragmentLamDep() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_lam_dep, container, false);
    }

}
