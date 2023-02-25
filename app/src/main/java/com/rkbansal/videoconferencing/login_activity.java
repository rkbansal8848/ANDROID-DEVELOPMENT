package com.rkbansal.videoconferencing;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_activity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText mail1,pass1;
    Button login1,create1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();

        ProgressDialog progress =new ProgressDialog(login_activity.this);
        progress.setTitle("Log in");
        progress.setMessage("We are logging into your account");

        mail1=findViewById(R.id.mail1);
        pass1=findViewById(R.id.pass1);
        login1=findViewById(R.id.login1);
        create1=findViewById(R.id.create1);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    progress.show();
                    String email="a",password="a";
                    email=mail1.getText().toString();
                    password=pass1.getText().toString();
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progress.dismiss();
                            if(task.isSuccessful()){
                                Toast.makeText(login_activity.this, "Logged in", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(login_activity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(login_activity.this, "Please enter the details", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            });

        create1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_activity.this,signup_activity.class));
            }
        });

    }
}