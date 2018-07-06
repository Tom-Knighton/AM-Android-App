package com.almurray.android.almurrayportal.firstPages;

import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.almurray.android.almurrayportal.main.*;
/**
 * Created by tom on 16/06/2018.
 */

public class userData {



    private static String uid;
    private static Integer aPoints;
    private static Integer pPoints;
    private static String aRank;
    private static String pRank;
    private static String urlToImage;
    private static String sName;
    private static String fullName;
    private static Boolean queued;
    private static String email;
    private static Boolean staff;
    private static Boolean admin;
    private static String standing;
    private static String sendbird;
    private static Boolean otherTeams;
    private static String team;
    private static Integer totalPrayers;
    private static Integer simplePrayers;
    private static Integer meaningfulPrayers;


    //region getting
    public String getUid() {
        return uid;
    }
    public Integer getaPoints() {
        return aPoints;
    }
    public Integer getpPoints() {
        return pPoints;
    }
    public String getaRank() {
        return aRank;
    }
    public String getpRank() {
        return pRank;
    }
    public String getUrlToImage() {
        return urlToImage;
    }
    public String getsName() {
        return sName;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getStanding() {
        return standing;
    }
    public String getSendbird() {
        return sendbird;
    }
    public String getTeam() {
        return team;
    }
    public Boolean getStaff() {
        return staff;
    }
    public Boolean getQueued() {
        return queued;
    }
    public Boolean getOtherTeams() {
        return otherTeams;
    }
    public Boolean getAdmin() {
        return admin;
    }
    public Integer getTotalPrayers() {
        return totalPrayers;
    }
    public Integer getSimplePrayers() {
        return simplePrayers;
    }
    public Integer getMeaningfulPrayers() {
        return meaningfulPrayers;
    }
    //endregion

    //region setting
    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setaPoints(Integer aPoints) {
        this.aPoints = aPoints;
    }

    public void setpPoints(Integer pPoints) {
        this.pPoints = pPoints;
    }

    public void setaRank(String aRank) {
        this.aRank = aRank;
    }

    public void setpRank(String pRank) {
        this.pRank = pRank;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setQueued(Boolean queued) {
        this.queued = queued;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStaff(Boolean staff) {
        this.staff = staff;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setStanding(String standing) {
        this.standing = standing;
    }

    public void setSendbird(String sendbird) {
        this.sendbird = sendbird;
    }

    public void setOtherTeams(Boolean otherTeams) {
        this.otherTeams = otherTeams;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    public void setTotalPrayers(Integer totalPrayers) {
        this.totalPrayers = totalPrayers;
    }
    public void setSimplePrayers(Integer simplePrayers) {
        this.simplePrayers = simplePrayers;
    }
    public void setMeaningfulPrayers(Integer meaningfulPrayers) {
        this.meaningfulPrayers = meaningfulPrayers;
    }
    //endregion

    //region Listening
    public void listenForChanges(String uid) {
        FirebaseDatabase.getInstance().getReference().child("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("TAG", "onDataChange");
                setAdmin(dataSnapshot.child("admin").getValue(Boolean.class));
                setUid(dataSnapshot.child("uid").getValue(String.class));
                setaPoints(dataSnapshot.child("aPoints").getValue(Integer.class));
                setpPoints(dataSnapshot.child("pPoints").getValue(Integer.class));
                setaRank(dataSnapshot.child("aRank").getValue(String.class));
                setpRank(dataSnapshot.child("pRank").getValue(String.class));
                setUrlToImage(dataSnapshot.child("urlToImage").getValue(String.class));
                setsName(dataSnapshot.child("sName").getValue(String.class));
                setFullName(dataSnapshot.child("fullName").getValue(String.class));
                setQueued(dataSnapshot.child("queued").getValue(Boolean.class));
                setEmail(dataSnapshot.child("email").getValue(String.class));
                setStaff(dataSnapshot.child("staff").getValue(Boolean.class));
                setStanding(dataSnapshot.child("standing").getValue(String.class));
                setSendbird(dataSnapshot.child("sendbird").getValue(String.class));
                setOtherTeams(dataSnapshot.child("otherTeams").getValue(Boolean.class));
                setTeam(dataSnapshot.child("team").getValue(String.class));
                setTotalPrayers((dataSnapshot.child("prayersSimple").getValue(Integer.class)) + dataSnapshot.child("prayersMeaningful").getValue(Integer.class));
                setSimplePrayers(dataSnapshot.child("prayersSimple").getValue(Integer.class));
                setMeaningfulPrayers(dataSnapshot.child("prayersMeaningful").getValue(Integer.class));
                Log.d("TAG", "********done");
                profileFragment.updateDisplay();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





}
