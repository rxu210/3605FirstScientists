package com.example.a3605firstscientists.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.ResourceBusyException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a3605firstscientists.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    private EditText userMail, userPassword;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private Intent HomeActivity;
    private ImageView loginPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = findViewById(R.id.tv_email);
        userPassword = findViewById(R.id.tv_password);
        btnLogin = findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();
        HomeActivity = new Intent (this,com.example.a3605firstscientists.HomeActivity.class);
        loginPhoto = findViewById(R.id.iv_loginPhoto);
        loginPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent (getApplicationContext(), Register.class);
                startActivity(register);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View view){
                                            btnLogin.setVisibility(View.INVISIBLE);

                                            final String mail = userMail.getText().toString();
                                            final String password = userPassword.getText().toString();

                                            if (mail.isEmpty() || password.isEmpty()){

                                                showMessage("Please Verify All Fields are Entered Correctly");

                                                btnLogin.setVisibility(View.VISIBLE);

                                            }

                                            else{

                                                signIn(mail,password);

                                            }
                                        }

                                    }
        );

    }

    private void signIn(String mail, String password) {

        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    btnLogin.setVisibility(View.VISIBLE);

                    updateUI();
                }

                else{

                    showMessage(task.getException().getMessage());

                    btnLogin.setVisibility(View.VISIBLE);

                }

            }
        });


    }

    private void updateUI() {

        startActivity(HomeActivity);
        finish();


    }

    private void showMessage (String text) {


        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            updateUI();
        }
    }

}
