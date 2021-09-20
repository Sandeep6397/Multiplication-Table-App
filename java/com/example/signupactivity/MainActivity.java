package com.example.signupactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    EditText email,pass1,pass2;
    Button signup,go_log;
    FirebaseAuth mFireBase;
    FirebaseUser firebaseUser;
    ProgressDialog progressDialog;
    String re="^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        mFireBase = FirebaseAuth.getInstance();
        firebaseUser = mFireBase.getCurrentUser();
        email=findViewById(R.id.email1);
        pass1=findViewById(R.id.pass1);
        pass2=findViewById(R.id.pass2);
        signup=findViewById(R.id.signup);
        go_log=findViewById(R.id.go_log);


        go_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait......");
                progressDialog.setTitle("Loding");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                Intent intent = new Intent(MainActivity.this,signinActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Pass1 = pass1.getText().toString();
                String Pass2 = pass2.getText().toString();
                if(!(Email.matches(re)))
                {
                    email.setError("Please enter valid Email ID");
                }
                else if(Pass1.isEmpty() || Pass1.length()<5)
                {
                    pass1.setError("Enter Password of length 5");
                }
                else if(! Pass1.equals(Pass2))
                {
                    pass2.setError("Password mismatch");
                }
                else
                {
                    progressDialog.setMessage("Please Wait......");
                    progressDialog.setTitle("Loding");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mFireBase.createUserWithEmailAndPassword(Email,Pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                sendUserToNextActivity();
                                Toast.makeText(MainActivity.this,"Signup Successfull",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }

            private void sendUserToNextActivity() {
                Intent intent = new Intent(MainActivity.this,signinActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}