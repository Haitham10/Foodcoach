package com.example.FoodCoash.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfoodplaner.R;
import com.example.FoodCoash.SignUpScreen1;
import com.example.FoodCoash.home.view.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    TextView TextEmail;
    TextView TextPassoward;
    Button btnLogin;
    FirebaseAuth auth;
    TextView singup;




    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent in = new Intent(LoginScreen.this , HomeActivity.class );
            startActivity(in);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen1);
        auth =FirebaseAuth.getInstance();
         TextEmail =findViewById(R.id.emailInput);

         TextPassoward =findViewById(R.id.passwordInput);

         btnLogin=findViewById(R.id.loginButton);
         singup=findViewById(R.id.signUpText);




        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginScreen.this , SignUpScreen1.class );
                startActivity(in);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email= String.valueOf(TextEmail.getText());
                password=String.valueOf(TextPassoward.getText());
                       if(TextUtils.isEmpty(email)){

                           Toast.makeText(LoginScreen.this ,"Enter Email",Toast.LENGTH_SHORT).show();
                           return;
                                  }
                if(TextUtils.isEmpty(password)){

                    Toast.makeText(LoginScreen.this ,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(LoginScreen.this, "Login Sucsseful.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(LoginScreen.this , HomeActivity.class );
                                    startActivity(in);
                                    finish();
                                } else {


                                    Toast.makeText(LoginScreen.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}