package com.almurray.android.almurrayportal.firstPages;

import android.content.Intent;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.almurray.android.almurrayportal.R;
import com.almurray.android.almurrayportal.firstPages.firstSteps;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.almurray.android.almurrayportal.main.*;

public class zeroPage extends AppCompatActivity {

    private FirebaseAuth mAuth;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero_page);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            loadStats();
        }
        else {
            goToFirst();
        }
    }


    void goToFirst() {
        Intent i = new Intent(this, firstSteps.class);
        startActivity(i);
    }

    void loadStats() {
        Log.d("GARY PORTAL", "loadStats: loading stats");
        ref.child("users").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userData userD = new userData();
                userD.setUid(dataSnapshot.child("uid").getValue(String.class));
                userD.setaPoints(dataSnapshot.child("aPoints").getValue(Integer.class));
                userD.setpPoints(dataSnapshot.child("pPoints").getValue(Integer.class));
                userD.setaRank(dataSnapshot.child("aRank").getValue(String.class));
                userD.setpRank(dataSnapshot.child("aRank").getValue(String.class));
                userD.setUrlToImage(dataSnapshot.child("urlToImage").getValue(String.class));
                userD.setEmail(dataSnapshot.child("email").getValue(String.class));
                userD.setsName(dataSnapshot.child("sName").getValue(String.class));
                userD.setFullName(dataSnapshot.child("fullName").getValue(String.class));
                userD.setStaff(dataSnapshot.child("staff").getValue(Boolean.class));
                userD.setAdmin(dataSnapshot.child("admin").getValue(Boolean.class));
                userD.setStanding(dataSnapshot.child("standing").getValue(String.class));
                userD.setSendbird(dataSnapshot.child("sendbird").getValue(String.class));
                userD.setOtherTeams(dataSnapshot.child("otherTeams").getValue(Boolean.class));
                userD.setQueued(dataSnapshot.child("queued").getValue(Boolean.class));
                userD.setTeam(dataSnapshot.child("team").getValue(String.class));
                Log.d("TAG", ":):):):)"+userD.getFullName());

                if (userD.getUid() == null) {
                    Intent i = new Intent(zeroPage.this, firstSteps.class);
                    startActivity(i);
                }

                if (userD.getQueued()) {
                    Intent i = new Intent(zeroPage.this, queue.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(zeroPage.this, mainSwiper.class);
                    startActivity(i);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }
}
