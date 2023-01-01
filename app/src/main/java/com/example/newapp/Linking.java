package com.example.newapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Linking extends AppCompatActivity {
    Button btn;
    TextView t1,t2,t3,t4,t5;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linking);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        t1 = (TextView) findViewById(R.id.name);
        t2 = (TextView) findViewById(R.id.ac);
        t3 = (TextView) findViewById(R.id.mobile);
        t4 = (TextView) findViewById(R.id.mail);
        t5 = (TextView) findViewById(R.id.acno);

        btn = (Button) findViewById(R.id.link);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String s1 = t1.getText().toString();
            String s2 = t2.getText().toString();
            String s3 = t3.getText().toString();
            String s4 = t4.getText().toString();
            String s5 = t5.getText().toString();
            String ac_bal = "10000";
            String status = "linked";

                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("name",s1);
                hashMap.put("account_type",s2);
                hashMap.put("account_no",s5);
                hashMap.put("mobile_no",s3);
                hashMap.put("emailid",s4);
                hashMap.put("account_balance",ac_bal);
                hashMap.put("status",status);

                databaseReference.child("BankAccounts")
                        .child(s3)
                        .setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Intent intent = new Intent(getApplicationContext(), LinkSuccess.class);
                                startActivity(intent);
                                Toast.makeText(Linking.this, "Account Successfully Linked with BOBSquad", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Linking.this, "Account Linking Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}