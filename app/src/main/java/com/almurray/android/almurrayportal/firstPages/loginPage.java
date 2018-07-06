package com.almurray.android.almurrayportal.firstPages;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.almurray.android.almurrayportal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginPage extends Activity {


    Button login;
    Button goBack;
    EditText email;
    EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = findViewById(R.id.loginLogin);
        pass = findViewById(R.id.loginPassword);
        email = findViewById(R.id.loginEmail);
        goBack = findViewById(R.id.loginGoBack);


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginPage.this, firstSteps.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext = email.getText().toString();
                String passtext = pass.getText().toString();

                if (emailtext.equals("") || passtext.equals("")) {
                    AlertDialog.Builder err = new AlertDialog.Builder(loginPage.this);
                    err.setMessage("Please enter an email and a password");
                    err.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    err.setCancelable(true);
                    AlertDialog errAlert = err.create();
                    errAlert.show();
                }
                else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailtext, passtext)
                            .addOnCompleteListener(loginPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(loginPage.this, zeroPage.class));
                                    } else {
                                        Toast.makeText(loginPage.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
