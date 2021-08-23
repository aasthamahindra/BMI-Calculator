package com.example.bmicalculator.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmicalculator.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;
    private GoogleSignInOptions googleSignInOptions;
    private ImageButton backBtnL;
    private EditText emailLogin;
    private EditText passwordLogin;
    private Button loginBtn;
    private Button googleLogin;
    private TextView signupRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backBtnL = (ImageButton) findViewById(R.id.backLogin);
        emailLogin = (EditText) findViewById(R.id.emailIdLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordIdLogin);
        loginBtn = (Button) findViewById(R.id.loginID);
        googleLogin = (Button) findViewById(R.id.googleLogin);
        signupRedirect = (TextView) findViewById(R.id.linkSignup);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                mUser = firebaseAuth.getCurrentUser();

                if(mUser != null){
                    Toast.makeText(LoginActivity.this, "Signed in", Toast.LENGTH_SHORT).show();
                }
                }
        };


        backBtnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivity = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(switchActivity);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(emailLogin.getText().toString())
                        && !TextUtils.isEmpty(passwordLogin.getText().toString())) {
                    String email = emailLogin.getText().toString();
                    String password = passwordLogin.getText().toString();

                    login(email, password);
                }
            }

            private void login(String email, String password) {
                mAuth.signInWithEmailAndPassword(email, password).
                        addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(LoginActivity.this, DetailsInput.class));
                                }
                            }
                        });
            }
        });


        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Configure Google Sign In


            }
        });

        signupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linkSignup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(linkSignup);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuth != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
