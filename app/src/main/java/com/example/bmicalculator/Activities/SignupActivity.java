package com.example.bmicalculator.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmicalculator.R;

public class SignupActivity extends AppCompatActivity {

    private ImageButton backBtnS;
    private EditText usernameSignup;
    private EditText emailSignup;
    private EditText passwordSignup;
    private Button signupBtn;
    private ImageButton googleSignup;
    private ImageButton facebookSignup;
    private TextView loginRedirect;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        backBtnS = (ImageButton) findViewById(R.id.backSignup);
        usernameSignup = (EditText) findViewById(R.id.usernameIdSignup);
        emailSignup = (EditText) findViewById(R.id.emailIdSignup);
        passwordSignup = (EditText) findViewById(R.id.passwordIdSignup);
        signupBtn = (Button) findViewById(R.id.signupID);
        loginRedirect = (TextView) findViewById(R.id.linkLogin);

        backBtnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivity = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(switchActivity);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                Intent linkLogin = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(linkLogin);
            }
        });

        googleSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "Google Login", Toast.LENGTH_SHORT).show();
            }
        });

        facebookSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "Facebook Login", Toast.LENGTH_LONG).show();
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linkLogin = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(linkLogin);
            }
        });

    }
}