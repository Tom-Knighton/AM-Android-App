package com.almurray.android.almurrayportal.firstPages;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.almurray.android.almurrayportal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;
import gun0912.tedbottompicker.TedBottomPicker;

public class signupPage extends AppCompatActivity {


    Button signup;
    Button goback;

    EditText email;
    EditText pass;
    EditText passConf;
    EditText school;
    EditText name;

    Button upload;
    CircleImageView image;

    Boolean imageuploaded = false;
    Uri imageURL;
    private final static int SELECT_PHOTO = 12345;

    Bitmap bmp;
    ByteArrayOutputStream baos;
    byte[] imageb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        signup = findViewById(R.id.createCreate);
        goback = findViewById(R.id.createGoBack);
        email = findViewById(R.id.createEmail);
        pass = findViewById(R.id.createPassword);
        passConf = findViewById(R.id.createConfirm);
        school = findViewById(R.id.createSchool);
        name = findViewById(R.id.createFullName);
        upload = findViewById(R.id.createImageB);
        image = findViewById(R.id.createImage);


        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signupPage.this, firstSteps.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailT = email.getText().toString();
                final String passT = pass.getText().toString();
                final String confT = passConf.getText().toString();
                final String schoolT = school.getText().toString();
                final String nameT = name.getText().toString();

                Boolean canWrite = true;
                if (emailT.equals("") || passT.equals("") || confT.equals("") || schoolT.equals("") || nameT.equals("")) {
                    Toast.makeText(signupPage.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    canWrite = false;
                    return;
                }
                if (!imageuploaded) {
                    Toast.makeText(signupPage.this, "Please select a profile image", Toast.LENGTH_SHORT).show();
                    canWrite = false;
                    return;
                }
                if (!passT.equals(confT)) {
                    Toast.makeText(signupPage.this, "Your passwords do not match", Toast.LENGTH_SHORT).show();
                    canWrite = false;
                    return;
                }
                if (passT.length() < 6) {
                    Toast.makeText(signupPage.this, "Your password must be longer than 6 characters", Toast.LENGTH_SHORT).show();
                    canWrite = false;
                    return;
                }

                if (canWrite) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailT, passT).addOnCompleteListener(signupPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("TAG", "user made");
                                final FirebaseUser usr = FirebaseAuth.getInstance().getCurrentUser();

                                final StorageReference ref = FirebaseStorage.getInstance().getReference().child("users/"+usr.getUid()+".jpg");
                               // final UploadTask uploadTask = ref.putFile(imageURL);

                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                Bitmap bitmap = BitmapFactory.decodeFile(imageURL.getPath());
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                                byte[] data = baos.toByteArray();
                                final UploadTask uploadTask = ref.putBytes(data);


                                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                final Uri url = uri;
                                                Log.d("TAG", url.toString());
                                                Log.d("TAG", usr.getUid());
                                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(usr.getUid());
                                                ref.child("aPoints").setValue(0);
                                                ref.child("aRank").setValue("TBA");
                                                ref.child("admin").setValue(false);
                                                ref.child("banned").setValue(false);
                                                ref.child("bannedR").setValue("");
                                                ref.child("chatBan").setValue(false);
                                                ref.child("desc").setValue("");
                                                ref.child("email").setValue(emailT);
                                                ref.child("fullName").setValue(nameT);
                                                ref.child("hidden").setValue(false);
                                                ref.child("id").setValue("TBA");
                                                ref.child("mainRole").setValue("TBA");
                                                ref.child("otherTeams").setValue(false);
                                                ref.child("pPoints").setValue(0);
                                                ref.child("pRank").setValue("TBA");
                                                ref.child("pass").setValue(passT);
                                                ref.child("prayersMeaningful").setValue(0);
                                                ref.child("prayersSimple").setValue(0);
                                                ref.child("queued").setValue(true);
                                                ref.child("quote").setValue("");
                                                ref.child("sName").setValue("TBA");
                                                ref.child("sendbird").setValue("demoa");
                                                ref.child("staff").setValue(false);
                                                ref.child("standing").setValue("TBA");
                                                ref.child("team").setValue("");
                                                ref.child("teamRank").setValue("TBA");
                                                ref.child("uid").setValue(usr.getUid());
                                                ref.child("urlToImage").setValue(url.toString());
                                                Log.d("TAG", usr.getUid());
                                                startActivity(new Intent(signupPage.this, zeroPage.class));
                                            }
                                        });


                                    }

                                });


                            }
                            else {
                                Toast.makeText(signupPage.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        //Images

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TedBottomPicker picker = new TedBottomPicker.Builder(signupPage.this).setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri uri) {
                        Picasso.get().load(uri).into(image);
                        upload.setVisibility(View.GONE);
                        imageuploaded = true;
                        imageURL = uri;
                    }
                }).create();
                picker.show(getSupportFragmentManager());
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TedBottomPicker picker = new TedBottomPicker.Builder(signupPage.this).setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri uri) {
                        Picasso.get().load(uri).into(image);
                        upload.setVisibility(View.GONE);
                        imageuploaded = true;
                        imageURL = uri;
                    }
                }).create();
                picker.show(getSupportFragmentManager());
            }
        });
    }


}
