package com.example.newapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Registration extends AppCompatActivity {

    Button b1;
    EditText e1,e2,e3;
    TextView textView;
    String AES = "AES";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        textView = (TextView) findViewById(R.id.txtlogin);

        e1 = (EditText) findViewById(R.id.t1);
        e2 = (EditText) findViewById(R.id.t2);
        e3 = (EditText) findViewById(R.id.t3);

        b1 = (Button) findViewById(R.id.btn);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginOptions.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String s1 = e1.getText().toString();
            String s2 = e2.getText().toString();
            String s3 = e3.getText().toString();

            if(s1.isEmpty())
            {
                e1.setError("please fill the required filled");

            }

                else if(s2.isEmpty())
                {
                    //e1.setError("please fill the required filled");
                    e2.setError("please fill the required filled");
                }

               else if(s3.isEmpty())
                {
                    e3.setError("please fill the required filled");
                }
            else
            {
                try {
                    s3 = encrypt(e3.getText().toString());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("name",s1);
                hashMap.put("email",s2);
                hashMap.put("mobile",s3);

                databaseReference.child("BankUsers")
                        .child(s1)
                        .setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Registration.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginOptions.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Registration.this, "Data Submission Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            }
        });
    }

    private String encrypt(String password) throws Exception
    {
        SecretKeySpec secretKeySpec = generateKey(password);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encval = cipher.doFinal(password.getBytes());
        String encryptvalue = Base64.encodeToString(encval, Base64.DEFAULT);
        return encryptvalue;
    }

    private SecretKeySpec generateKey(String password) throws Exception
    {
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        messageDigest.update(bytes, 0 , bytes.length);
        byte[] key = messageDigest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
}