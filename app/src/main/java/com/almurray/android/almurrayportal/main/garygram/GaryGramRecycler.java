package com.almurray.android.almurrayportal.main.garygram;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.almurray.android.almurrayportal.R;
import com.almurray.android.almurrayportal.utils.FirebaseAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tom on 06/07/2018.
 */

public class GaryGramRecycler extends FirebaseAdapter<GaryGramRecycler.ViewHolder, GaryGramPost> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        Integer num;
        ImageView image;
        CircleImageView posterImage ;
        TextView poster, likes, description;
        Button comments, options;
        //CardView cardView;
        Boolean hasUp, hasDown, canUp, canDown, canDoStuff;
        ImageView heart;


        public ViewHolder(View view) {
            super(view);


        }
    }

    Context mContext;


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);

        return new ViewHolder(view);
    }

    public GaryGramRecycler(Query query, @Nullable ArrayList<GaryGramPost> items,
                            @Nullable ArrayList<String> keys, Context context) {
        super(query, items, keys);
        mContext = context;

    }














    @Override protected void itemAdded(GaryGramPost item, String key, int position) {
        Log.d("MyAdapter", "Added a new item to the adapter.");
    }

    @Override protected void itemChanged(GaryGramPost oldItem, GaryGramPost newItem, String key, int position) {
        Log.d("MyAdapter", "Changed an item.");
    }

    @Override protected void itemRemoved(GaryGramPost item, String key, int position) {
        Log.d("MyAdapter", "Removed an item from the adapter.");
    }

    @Override protected void itemMoved(GaryGramPost item, String key, int oldPosition, int newPosition) {
        Log.d("MyAdapter", "Moved an item.");
    }

}
