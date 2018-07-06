package com.almurray.android.almurrayportal.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.almurray.android.almurrayportal.firstPages.*;
import com.almurray.android.almurrayportal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.almurray.android.almurrayportal.utils.*;
import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link profileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static boolean canChange;


    private OnFragmentInteractionListener mListener;



    //region main view
    private static NestedScrollView scroller;

    private static TextView fullName;
    private static TextView sName;
    private static CircleImageView profileImg;
    private static Button goToWeb;

    private static TextView aPoints;
    private static TextView pPoints;

    private static TextView aRank;
    private static TextView pRank;
    private static TextView team;

    private static Button prayer;
    private static Button RR;
    private static Button feedback;
    private static Button cd;
    private static Button settings;
    //endregion
    //region prayers
    private static Button simplePrayer;
    private static Button meaningfulPrayer;
    private static TextView currentPrayers;
    private static Button adminPrayers;
    private static Button prayerGoBack;
    private static RelativeLayout prayerCont;
    //endregion
    //region settings
    private Switch ggSwitch;
    private Switch largeChatSwitch;
    private Button settingsGoBack;
    private RelativeLayout settingsCont;
    //endregion

    @Override
    public void onResume() {
        super.onResume();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final SharedPreferences preferences = getActivity().getSharedPreferences("gpSettings", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();
                //region main view
                scroller = getActivity().findViewById(R.id.profileScroller);

                fullName = getActivity().findViewById(R.id.profileName);
                sName = getActivity().findViewById(R.id.profileSName);
                profileImg = getActivity().findViewById(R.id.profileImage);
                goToWeb = getActivity().findViewById(R.id.profileGoToWeb);

                aPoints = getActivity().findViewById(R.id.profileAPoints);
                pPoints = getActivity().findViewById(R.id.profilePPoints);

                aRank = getActivity().findViewById(R.id.profileStatAR);
                pRank = getActivity().findViewById(R.id.profileStatPR);
                team = getActivity().findViewById(R.id.profileStatTR);

                prayer = getActivity().findViewById(R.id.profilePrayer);
                RR = getActivity().findViewById(R.id.profileRR);
                feedback = getActivity().findViewById(R.id.profileFeedback);
                cd = getActivity().findViewById(R.id.profileCD);
                settings = getActivity().findViewById(R.id.profileSettings);

                final userData userData = new userData();

                scroller.setNestedScrollingEnabled(true);
                Picasso.get().load(userData.getUrlToImage()).into(profileImg);

                fullName.setText(userData.getFullName());
                sName.setText(userData.getsName());

                aPoints.setText("AMIGO POINTS: "+userData.getaPoints().toString());
                pPoints.setText("POSITIITY POINTS: "+userData.getpPoints().toString());

                aRank.setText(userData.getaRank());
                pRank.setText(userData.getpRank());
                team.setText(userData.getTeam());

                goToWeb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), webView.class);
                        i.putExtra("url", "https://garyportal.xyz");
                        startActivity(i);
                    }
                });

                RR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), webView.class);
                        i.putExtra("url", "https://garyportal.xyz/rr");
                        startActivity(i);
                    }
                });
                feedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), webView.class);
                        i.putExtra("url", "https://garyportal.typeform.com/to/wqObIy");
                        startActivity(i);
                    }
                });
                cd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), webView.class);
                        i.putExtra("url", "https://garyportal.xyz/computerdating");
                        startActivity(i);
                    }
                });

                prayer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RelativeLayout prayerCon = getActivity().findViewById(R.id.prayerContainer);
                        prayerCon.setVisibility(View.VISIBLE);
                        prayerCon.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                return true;
                            }
                        });

                    }
                });
                settings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RelativeLayout settingsCon = getActivity().findViewById(R.id.settingsContainer);
                        settingsCon.setVisibility(View.VISIBLE);
                        settingsCon.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                return true;
                            }
                        });
                    }
                });
                //endregion

                //region prayer view
                prayerCont = getActivity().findViewById(R.id.prayerContainer);
                simplePrayer = getActivity().findViewById(R.id.prayersSimple);
                meaningfulPrayer = getActivity().findViewById(R.id.prayersMeaningful);
                prayerGoBack = getActivity().findViewById(R.id.prayersGoBack);
                adminPrayers = getActivity().findViewById(R.id.prayersAdmin);
                currentPrayers = getActivity().findViewById(R.id.prayersCurrent);
                FirebaseDatabase.getInstance().getReference().child("users").child(userData.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer p1 = dataSnapshot.child("prayersMeaningful").getValue(Integer.class);
                        Integer p2 = dataSnapshot.child("prayersSimple").getValue(Integer.class);
                        String total = String.valueOf(p1 + p2);
                        currentPrayers.setText("Current Prayers: " + total);
                        userData.setSimplePrayers(p2);
                        userData.setMeaningfulPrayers(p1);
                        userData.setTotalPrayers(p1 + p2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                simplePrayer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(userData.getUid()).child("prayersSimple").setValue(userData.getSimplePrayers() + 1);
                        userData.setSimplePrayers(userData.getSimplePrayers() + 1);
                    }
                });
                meaningfulPrayer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(userData.getUid()).child("prayersMeaningful").setValue(userData.getMeaningfulPrayers() + 1);
                        userData.setMeaningfulPrayers(userData.getMeaningfulPrayers() + 1);
                    }
                });

                prayerGoBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prayerCont.setVisibility(View.GONE);
                        scroller.setNestedScrollingEnabled(true);
                    }
                });
                adminPrayers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    snapshot.child("prayersSimple").getRef().setValue(0);
                                    snapshot.child("prayersMeaningful").getRef().setValue(0);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
                //endregion

                //region settings view
                settingsGoBack = getActivity().findViewById(R.id.settingGoBack);
                ggSwitch = getActivity().findViewById(R.id.settingGGSwitch);
                largeChatSwitch = getActivity().findViewById(R.id.settingChatSwitch);
                settingsCont = getActivity().findViewById(R.id.settingsContainer);

                if (preferences.getBoolean("compactGG", false)) {
                    Log.d("TAG", "got GG true");
                    ggSwitch.setChecked(true);
                }
                else {
                    ggSwitch.setChecked(false);
                    Log.d("TAG", "got GG false");
                }

                if (preferences.getBoolean("compactChat", false)) {
                    largeChatSwitch.setChecked(true);
                }
                else {
                    largeChatSwitch.setChecked(false);
                }

                ggSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (ggSwitch.isPressed()) {
                            if (b) {
                                editor.putBoolean("compactGG", true);
                                editor.apply();
                                Log.d("TAG", "applied true");
                            } else {
                                editor.putBoolean("compactGG", false);
                                editor.apply();
                                Log.d("TAG", "applied false");
                            }
                        }
                    }
                });
                largeChatSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (largeChatSwitch.isPressed()) {
                            if (b) {
                                editor.putBoolean("compactChat", true);
                                editor.apply();
                            } else {
                                editor.putBoolean("compactChat", false);
                                editor.apply();
                            }
                        }
                    }
                });
                settingsGoBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        settingsCont.setVisibility(View.GONE);
                    }
                });
                //endregion
                canChange = true;

            }

        };
        handler.post(runnable);
    }

    public static void updateDisplay() {
        if (canChange) {
            final userData userData = new userData();

            Log.d("TAG", "update display");
            Log.d("TAG", "new "+userData.getaRank());

            scroller.setNestedScrollingEnabled(true);
            Picasso.get().load(userData.getUrlToImage()).into(profileImg);

            fullName.setText(userData.getFullName());
            sName.setText(userData.getsName());

            aPoints.setText("AMIGO POINTS: "+userData.getaPoints().toString());
            pPoints.setText("POSITIITY POINTS: "+userData.getpPoints().toString());

            aRank.setText(userData.getaRank());
            pRank.setText(userData.getpRank());
            team.setText(userData.getTeam());
        }
    }


    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
