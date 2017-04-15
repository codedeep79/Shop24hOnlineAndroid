package com.example.trungnguyenhau.shoponline24h.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trungnguyenhau.shoponline24h.Model.ProductIntro;
import com.example.trungnguyenhau.shoponline24h.OnSkipNextListener;
import com.example.trungnguyenhau.shoponline24h.R;
public class CategoryFragment extends Fragment implements View.OnClickListener{

    private int position;
    private static  OnSkipNextListener _callback;

    public CategoryFragment() {

    }

    // Trả về fragment này, tất cả các thành phần trong CategoryFragment đều trả về
    public static CategoryFragment newInstance(OnSkipNextListener callback){
        _callback = callback;
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        TextView txtTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView txtDescription = (TextView) view.findViewById(R.id.tv_description);
        ImageView imgBackground = (ImageView) view.findViewById(R.id.iv_background);

        View viewIndicator1 = (View) view.findViewById(R.id.indicator1);
        View viewIndicator2 = (View) view.findViewById(R.id.indicator2);
        View viewIndicator3 = (View) view.findViewById(R.id.indicator3);
        View viewIndicator4 = (View) view.findViewById(R.id.indicator4);

        TextView txtNext = (TextView) view.findViewById(R.id.tv_next);
        txtNext.setOnClickListener(this);
        TextView txtSkip = (TextView) view.findViewById(R.id.tv_skip);
        txtSkip.setOnClickListener(this);

        Bundle bundle = getArguments();
        if (bundle != null)
        {

            position = bundle.getInt("position");
            ProductIntro productIntro = (ProductIntro) bundle.getSerializable("product");
            txtTitle.setText(productIntro.getTitle());
            txtDescription.setText(productIntro.getDescription());
            imgBackground.setImageResource(productIntro.getIdImage());

            // 3 Giới thiệu sản phẩm có chung cấu trúc còn cái cuối cùng cấu hình tĩnh cho indicator
            switch (position)
            {
                case 0:
                    viewIndicator1.setBackgroundResource(R.drawable.bg_indicator_active);
                    viewIndicator2.setBackgroundResource(R.drawable.bg_indicator_unactive);
                    viewIndicator3.setBackgroundResource(R.drawable.bg_indicator_unactive);

                    break;
                case 1:
                    viewIndicator1.setBackgroundResource(R.drawable.bg_indicator_unactive);
                    viewIndicator2.setBackgroundResource(R.drawable.bg_indicator_active);
                    viewIndicator3.setBackgroundResource(R.drawable.bg_indicator_unactive);

                    break;
                case 2:
                    viewIndicator1.setBackgroundResource(R.drawable.bg_indicator_unactive);
                    viewIndicator2.setBackgroundResource(R.drawable.bg_indicator_unactive);
                    viewIndicator3.setBackgroundResource(R.drawable.bg_indicator_active);
                    break;
            }
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            // Sử dụng phương thức như thế này thì tất cả các class nào thực thi OnSkipNextListener
            // đều xử lý
            case R.id.tv_next:
                _callback.onNextListener(position);
                break;
            case R.id.tv_skip:
                _callback.onSkipListener();
                break;
        }
    }
}
