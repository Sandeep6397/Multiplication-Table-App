package com.example.signupactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signinActivity2 extends AppCompatActivity {

    EditText email1,lpass;
    Button login,go_sign;
    ProgressDialog progressDialog;
    FirebaseAuth mFireBase;
    FirebaseUser firebaseUser;
    String re="^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signin2);
        progressDialog = new ProgressDialog(this);
        mFireBase = FirebaseAuth.getInstance();
        firebaseUser = mFireBase.getCurrentUser();

        email1 = findViewById(R.id.email1);
        lpass = findViewById(R.id.lpass);
        login = findViewById(R.id.login);
        go_sign = findViewById(R.id.go_sign);


        go_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait......");
                progressDialog.setTitle("Loding");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                Intent intent = new Intent(signinActivity2.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email1.getText().toString();
                String Pass1 = lpass.getText().toString();

                if(!(Email.matches(re)))
                {
                    email1.setError("Please enter valid Email ID");
                }
                else if(Pass1.isEmpty() || Pass1.length()<5)
                {
                    lpass.setError("Enter Password of length 5");
                }
                else
                {
                    progressDialog.setMessage("Please Wait......");
                    progressDialog.setTitle("Loding");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    mFireBase.signInWithEmailAndPassword(Email,Pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                sendUserToNextActivity();
                                Toast.makeText(signinActivity2.this,"Signin Successfull",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(signinActivity2.this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }

            private void sendUserToNextActivity() {
                Intent intent = new Intent(signinActivity2.this,MultiplicationActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}