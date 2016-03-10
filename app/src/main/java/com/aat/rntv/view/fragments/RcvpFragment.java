package com.aat.rntv.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aat.rntv.controller.MainListAdapter;
import com.aat.rntv.model.RealmLesson;
import com.champions.are.we.androidacademytlv.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Vito on 3/5/16.
 */
public class RcvpFragment extends Fragment {


    private CardView mTitle;
    private TextView mLessonTitle;
    private TextView mLessonDate;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        RealmResults<RealmLesson> lessons = Realm.getInstance(getContext()).where(RealmLesson.class).findAll();
        mAdapter = new MainListAdapter(lessons);
        mRecyclerView.setAdapter(mAdapter);
    }
}
