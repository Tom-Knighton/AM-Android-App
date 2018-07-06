package com.almurray.android.almurrayportal.firstPages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.almurray.android.almurrayportal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class firstSteps extends AppCompatActivity {


    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    Button loginButton;
    Button createAccountButton;
    TextView goToWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_steps);

        loginButton = findViewById(R.id.firstPageLogin);
        createAccountButton = findViewById(R.id.firstPageCreate);
        goToWebsite = findViewById(R.id.firstPageWebsite);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(firstSteps.this, loginPage.class));
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(firstSteps.this, signupPage.class));
            }
        });

        goToWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GARY PORTAl", "webclicked");
            }
        });
    }
}
