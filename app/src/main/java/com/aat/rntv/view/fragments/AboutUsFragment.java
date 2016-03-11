package com.aat.rntv.view.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.champions.are.we.androidacademytlv.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {

    private ImageView mImage;
    private int mImageResId;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        mImageResId = getArguments().getInt("image");
        mImage = (ImageView) view.findViewById(R.id.about_us_bg);
        mImage.setImageResource(mImageResId);

        return view;
    }


}
