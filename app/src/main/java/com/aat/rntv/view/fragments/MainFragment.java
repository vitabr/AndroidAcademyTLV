package com.aat.rntv.view.fragments;

import android.content.Intent;
import android.net.Uri;
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
import com.aat.rntv.view.activities.RsvpActivity;
import com.aat.rntv.view.activities.Tip1Activity;
import com.aat.rntv.view.activities.Tip2Activity;
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
public class MainFragment extends Fragment implements Constants {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e("VITO", "MainFragment:onCreateView");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.tip1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Tip1Activity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.tip2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Tip2Activity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.trick1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // http://www.uplabs.com/posts/c/resources
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.uplabs.com/posts/c/resources"));
                startActivity(browserIntent);
            }
        });

        view.findViewById(R.id.trick2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // http://developer.android.com/design/downloads/index.html
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/design/downloads/index.html"));
                startActivity(browserIntent);
            }
        });
    }


//    private void loadData() {
//
//        final Realm realm = Realm.getInstance(BaseApplication.getInstance());
//
//        RealmResults<Lesson> previousLessons = realm.where(Lesson.class).findAll();
//
//        realm.beginTransaction();
//        previousLessons.clear();
//        realm.commitTransaction();
//
//        final Firebase firebase = new Firebase("https://flickering-torch-6484.firebaseio.com/Course_4/Lessons");
//        firebase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println("There are " + snapshot.getChildrenCount() + " lessons");
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    final FirebaseLesson lesson = postSnapshot.getValue(FirebaseLesson.class);
//
//                    realm.executeTransaction(new Realm.Transaction() {
//                        @Override
//                        public void execute(Realm realm) {
//                            Lesson lesson1 = realm.createObject(Lesson.class);
//                            lesson1.setmTitle(lesson.getmTitle());
//                            lesson1.setmDescription(lesson.getmDescription());
//                            lesson1.setmLecturerName(lesson.getmLecturerName());
//                            lesson1.setmStartDate(lesson.getmStartDate());
//                        }
//                    });
//                }
//
//                // specify an adapter (see also next example)
//                mRealmLessons = Realm.getInstance(getContext()).where(Lesson.class).findAll();
//                mAdapter = new MainListAdapter(mRealmLessons);
//                //mRecyclerView.setAdapter(mAdapter);
//
//                firebase.removeEventListener(this);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                System.out.println("The read failed: " + firebaseError.getMessage());
//            }
//        });
//    }

}
