package com.aat.rntv.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.champions.are.we.androidacademytlv.R;

/**
 * Created by Vito on 3/5/16.
 */
public class MainFragment extends Fragment{


    private CardView mTitle;
    private TextView mLessonTitle;
    private TextView mLessonDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitle = (CardView) view.findViewById(R.id.card_view);
        mLessonTitle = (TextView) view.findViewById(R.id.lesson_title);
        mLessonDate = (TextView) view.findViewById(R.id.lesson_date);

    }
}
