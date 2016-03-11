package com.aat.rntv.controller;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aat.rntv.BaseApplication;
import com.aat.rntv.model.Lesson;
import com.champions.are.we.androidacademytlv.R;
import com.squareup.picasso.Picasso;

import io.realm.RealmResults;

/**
 * Created by vito on 3/8/2016.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {
    private RealmResults<Lesson> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
      // each data item is just a string in this case
      public TextView mTitle;
      public TextView mDescription;
      public ImageView mBackground;

      public ViewHolder(View itemView) {
        super(itemView);

        Log.e("VITO", "ViewHolder:1-Constractor");
        mTitle = (TextView) itemView.findViewById(R.id.item_title);
        mDescription = (TextView) itemView.findViewById(R.id.item_description);
        mBackground = (ImageView) itemView.findViewById(R.id.item_background);
      }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainListAdapter(RealmResults<Lesson> myDataset) {
      mDataset = myDataset;

      Log.e("VITO", "MainListAdapter:1-Constractor: " + mDataset);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

      // create a new view
      View v = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_main, parent, false);

      // set the view's size, margins, paddings and layout parameters
      ViewHolder vh = new ViewHolder(v);
      return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

      Log.e("VITO", "MainListAdapter:3-onBindViewHolder");
      // - get element from your dataset at this position
      // - replace the contents of the view with that element

      Picasso.with(BaseApplication.getInstance())
            .load(mDataset.get(position).getmBg())
            .into(holder.mBackground);
      holder.mTitle.setText(mDataset.get(position).getmTitle());
      holder.mDescription.setText(mDataset.get(position).getmDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
      return mDataset.size();
    }
  }

