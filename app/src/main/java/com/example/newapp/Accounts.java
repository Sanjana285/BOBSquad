package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Accounts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        final ArrayList<NumbersView> arrayList = new ArrayList<NumbersView>();

        // add all the values from 1 to 15 to the arrayList
        // the items are of the type NumbersView
        arrayList.add(new NumbersView(R.drawable.bob2, "Savings Account", "661006478342"));
        arrayList.add(new NumbersView(R.drawable.bob2, "Current Account", "661006478346"));
        arrayList.add(new NumbersView(R.drawable.bob2, "Fixed Deposit Account", "661006478542"));
        arrayList.add(new NumbersView(R.drawable.bob2, "Recurring Deposit Account", "661005770342"));
        arrayList.add(new NumbersView(R.drawable.bob2, "Demat Account", "661006478500"));
        // Now create the instance of the NumebrsViewAdapter and pass
        // the context and arrayList created above
        NumbersViewAdapter numbersArrayAdapter = new NumbersViewAdapter(this, arrayList);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView numbersListView = findViewById(R.id.listView);

        // set the numbersViewAdapter for ListView
        numbersListView.setAdapter(numbersArrayAdapter);

        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0)
                {
                    Intent intent = new Intent(getApplicationContext(), Linking.class);
                    startActivity(intent);
                }
            }
        });
    }
}