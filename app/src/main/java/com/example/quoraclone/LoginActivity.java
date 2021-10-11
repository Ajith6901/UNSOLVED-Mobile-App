package com.example.quoraclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView question;
    private EditText emailEd,passworded;
    private Button login;
    private ProgressDialog loader;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        question=findViewById(R.id.loginPageQuestion);
        emailEd=findViewById(R.id.loginEmail);
        passworded=findViewById(R.id.loginPassword);
        login=findViewById(R.id.loginInBtn);
        loader=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailEd.getText().toString();
                String password=passworded.getText().toString();

                if (TextUtils.isEmpty(email)){
                    emailEd.setError("Email is required");
                }
                if (TextUtils.isEmpty(email)){
                    passworded.setError("password is required");
                }
                else{
                    loader.setMessage("Login in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login is successful logged in as "+mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "Login Failed"+task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

            }
        });
    }
}