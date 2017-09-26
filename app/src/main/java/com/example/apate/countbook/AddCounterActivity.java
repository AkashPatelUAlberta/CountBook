package com.example.apate.countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddCounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Add A Counter");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);
    }
}
