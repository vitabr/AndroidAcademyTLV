package com.aat.rntv.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.champions.are.we.androidacademytlv.R;

public class LecturersActivity extends AppCompatActivity {
 RecyclerView rv;
    LinearLayoutManager llm;
    helpItem[] help;

    public static Intent getIntent(Context context) {
        return new Intent(context, LecturersActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecyclerhelp);

        rv = (RecyclerView) findViewById(R.id.recycler);
        llm = new LinearLayoutManager(this);

        final helpItem Item[] = {
            new helpItem(getResources().getDrawable(R.drawable.img_lecturer_ylevin), "Yonatan Levin / ", "“Born with the spirit of an entrepreneur, I love to create, build and just do stuff in the technology field....", "Android Avengalist, Gett"),
            new helpItem(getResources().getDrawable(R.drawable.img_lecturer_jyarkoni), "Jonathan Yarkoni / ", " strive to always be creative and adapt quickly to new environments, both technically and socially.", "Android developer, IronSorce"),
            new helpItem(getResources().getDrawable(R.drawable.img_lectureridan), "Idan Felix / ", "Application Developer, Development Architect, Project Manager and Team Leader in a wide variety of business software applications...", "Senior Android & Redhead"),
            new helpItem(getResources().getDrawable(R.drawable.img_lecturer_britt), "Britt Barak /", "Has great interpersonal and analytical abilities. Take initiative, ownership, and leadership for excellent task completion..", "Android Lead Devloper, Real"),
            new helpItem(getResources().getDrawable(R.drawable.img_lecturer_muriel), "Muriel Felix /", "The latest edition to th Android Academny team, That added all the color to pur life. A Graphic Designer student... ", "Android Designer")
        };

        ArAdapter adaper = new ArAdapter(Item);
        adaper.notifyDataSetChanged();
        rv.setLayoutManager(llm);
        rv.setAdapter(adaper);

    }

    public class ArAdapter extends RecyclerView.Adapter<ArAdapter.vh>{
        helpItem[] item;
        public  ArAdapter(helpItem[] item ){
            this.item = item;
        }

        @Override
        public vh onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

            return new vh(v);
        }

        @Override
        public void onBindViewHolder(vh holder, int position) {
            holder.face.setImageDrawable(item[position].face);
            holder.expert.setText(item[position].expert);
            holder.name.setText(item[position].name);
            holder.desc .setText(item[position].desc);
        }

        @Override
        public int getItemCount() {
            return item.length;
        }

        public class vh extends RecyclerView.ViewHolder{

            TextView name,desc,expert;
            ImageView face;
            public vh(View itemview){
                super(itemview);
                name = (TextView)itemview.findViewById(R.id.name);
                desc = (TextView)itemview.findViewById(R.id.desc);
                expert = (TextView)itemview.findViewById(R.id.expert);
                face =(ImageView) itemview.findViewById(R.id.face);

            }

        }
    }
}
