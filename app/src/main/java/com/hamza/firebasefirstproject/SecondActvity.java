package com.hamza.firebasefirstproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActvity extends AppCompatActivity {
    EditText edt1,edt2,edt3;
    Button btn;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_actvity);
        edt1=(EditText)findViewById(R.id.email);
        edt2=(EditText)findViewById(R.id.passwords);
        edt3=(EditText)findViewById(R.id.confirmpassword);
        btn=(Button)findViewById(R.id.reg_btn);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        firebaseAuth=FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtxt=edt1.getText().toString().trim();
                String pass=edt2.getText().toString().trim();
                String conpass=edt3.getText().toString().trim();

                if(TextUtils.isEmpty(emailtxt)){
                    Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(conpass)){
                    Toast.makeText(getApplicationContext(), "Please Enter confirm Password", Toast.LENGTH_SHORT).show();
                }
                if(pass.length()<6){
                    Toast.makeText(getApplicationContext(), "Pass Too Short", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);
                if(pass.equals(conpass)){

                    firebaseAuth.signInWithEmailAndPassword(emailtxt, pass)
                            .addOnCompleteListener(SecondActvity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(getApplicationContext(), "Registration Complete", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Authentication Fail", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }
            }
        });
    }



}
