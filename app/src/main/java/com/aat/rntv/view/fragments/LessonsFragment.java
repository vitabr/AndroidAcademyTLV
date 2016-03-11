package com.aat.rntv.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aat.rntv.BaseApplication;
import com.aat.rntv.controller.MainListAdapter;
import com.aat.rntv.model.Constants;
import com.aat.rntv.model.FirebaseLesson;
import com.aat.rntv.model.Lesson;
import com.champions.are.we.androidacademytlv.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Vito on 3/5/16.
 */
public class LessonsFragment extends Fragment implements Constants {

    private CardView mTitle;
    private TextView mLessonTitle;
    private TextView mLessonDate;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RealmResults<Lesson> mRealmLessons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e("VITO", "MainFragment:onCreateView");
        return inflater.inflate(R.layout.fragment_lessons, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("VITO", "MainFragment:onViewCreated");

        mTitle = (CardView) view.findViewById(R.id.card_view);
        mLessonTitle = (TextView) view.findViewById(R.id.lesson_title);
        mLessonDate = (TextView) view.findViewById(R.id.lesson_date);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadData();
    }

    private void loadData() {

        final Realm realm = Realm.getInstance(BaseApplication.getInstance());

        RealmResults<Lesson> previousLessons = realm.where(Lesson.class).findAll();

        realm.beginTransaction();
        previousLessons.clear();
        realm.commitTransaction();

        final Firebase firebase = new Firebase("https://flickering-torch-6484.firebaseio.com/Course_4/Lessons");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " lessons");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    final FirebaseLesson lesson = postSnapshot.getValue(FirebaseLesson.class);

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Lesson lesson1 = realm.createObject(Lesson.class);
                            lesson1.setmTitle(lesson.getmTitle());
                            lesson1.setmDescription(lesson.getmDescription());
                            lesson1.setmLecturerName(lesson.getmLecturerName());
                            lesson1.setmStartDate(lesson.getmStartDate());
                        }
                    });
                }

                // specify an adapter (see also next example)
                mRealmLessons = Realm.getInstance(getContext()).where(Lesson.class).findAll();
                mAdapter = new MainListAdapter(mRealmLessons);
                mRecyclerView.setAdapter(mAdapter);

                firebase.removeEventListener(this);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

}
