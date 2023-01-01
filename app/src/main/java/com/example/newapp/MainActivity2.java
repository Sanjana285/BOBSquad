package com.example.newapp;

import static com.example.newapp.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class MainActivity2 extends AppCompatActivity {
    Button b2;
    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    String vid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main2);

        ed1 = (EditText) findViewById(id.e1);
        ed2 = (EditText) findViewById(id.e2);
        ed3 = (EditText) findViewById(id.e3);
        ed4 = (EditText) findViewById(id.e4);
        ed5 = (EditText) findViewById(id.e5);
        ed6 = (EditText) findViewById(id.e6);


        b2 = (Button) findViewById(id.button2);

        vid = getIntent().getStringExtra("verification");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
                if(ed1.getText().toString().trim().isEmpty()
                ||ed2.getText().toString().trim().isEmpty()
                ||ed3.getText().toString().trim().isEmpty()
                ||ed4.getText().toString().trim().isEmpty()
                ||ed5.getText().toString().trim().isEmpty()
                ||ed6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(MainActivity2.this, "please enter valid codes for all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = ed1.getText().toString() +
                        ed2.getText().toString() +
                        ed3.getText().toString() +
                        ed4.getText().toString() +
                        ed5.getText().toString()+
                        ed6.getText().toString();

                if (vid != null)
                {
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(vid, code);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(MainActivity2.this, "verification code is valid", Toast.LENGTH_SHORT).show();
                                Intent i1 = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(i1);
                            }
                            else
                            {
                                Toast.makeText(MainActivity2.this, "verification code is invalid", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

            }
        });

    }
}