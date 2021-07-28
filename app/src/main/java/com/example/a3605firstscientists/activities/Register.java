package com.example.a3605firstscientists.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3605firstscientists.Donate;
import com.example.a3605firstscientists.LearnActivity;
import com.example.a3605firstscientists.R;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class Register extends AppCompatActivity {


    private EditText userEmail, userName, userPassword, userPassword2;

    private Button regButton;

    private FirebaseAuth mAuth;

    ImageView userPhoto;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    Uri pickedImgUri;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = findViewById(R.id.tv_remail);
        userName = findViewById(R.id.tv_ruser);
        userPassword = findViewById(R.id.tv_rpassword);
        userPassword2 = findViewById(R.id.tv_rpassword2);
        regButton = findViewById(R.id.btn_register);
        userPhoto = findViewById(R.id.iv_picture);


        userPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestForPermission();
                    showMessage("Please Verify all fields are entered properly");
                }

                else{

                    openGallery();


                }
            }
        });

        // Method for handling Sign In textview
        TextView login = findViewById(R.id.tv_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                intent.putExtra("From", "Donate");
                startActivity(intent);
            }
        });


        mAuth = FirebaseAuth.getInstance();



        regButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View view){
                regButton.setVisibility(View.INVISIBLE);

                final String email = userEmail.getText().toString();
                final String name = userName.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();

                if( email.isEmpty() || name.isEmpty() || password.isEmpty() || password2.isEmpty() || !password.equals(password2)){

                    showMessage("Please Verify all fields are entered properly");

                    regButton.setVisibility(View.VISIBLE);

                }

                else{
                    CreateUserAccount (email,name,password);

                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode ==RESULT_OK && requestCode == REQUESCODE && data !=null) {

            pickedImgUri = data.getData();
            userPhoto.setImageURI(pickedImgUri);


        }

    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(Register.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();
            }

            else{

                ActivityCompat.requestPermissions(Register.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode );

            }

        }

        else{
            openGallery();
        }



    }

    private void CreateUserAccount (String email, String name, String password){

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    showMessage("Account created");

                                    updateUserInfo (name, pickedImgUri, mAuth.getCurrentUser());


                                }
                                else {
                                    showMessage("Account Creation Failed" + task.getException().getMessage());
                                    regButton.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                );



    }

    private void updateUserInfo(String name, Uri pickedImgUri, FirebaseUser currentUser) {

        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder().
                                setDisplayName(name).
                                setPhotoUri(uri).
                                build();


                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            showMessage("Register Complete");
                                            updateUI();

                                        }

                                    }

                                });

                    }
                });

            }
        });


    }

    private void updateUI() {

        Intent loginActivity = new Intent(getApplicationContext(),Login.class);
        startActivity(loginActivity);
        finish();
    }



    private void showMessage (String message){

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }


    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }
}
