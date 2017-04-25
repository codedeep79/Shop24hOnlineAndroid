package com.example.trungnguyenhau.shoponline.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.trungnguyenhau.shoponline.Views.Home.HomeActivity;
import com.example.trungnguyenhau.shoponline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadDataApplication extends Fragment implements View.OnClickListener{

    ImageButton imgLetGo;
    public LoadDataApplication() {

    }

    public static LoadDataApplication newInstance(){
        LoadDataApplication loadDataApplication = new LoadDataApplication();
        return loadDataApplication;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_load_data_application, container, false);
        imgLetGo = (ImageButton) view.findViewById(R.id.imagebutton_letgo);
        imgLetGo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imagebutton_letgo:
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
