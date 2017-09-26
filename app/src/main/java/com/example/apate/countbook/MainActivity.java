package com.example.apate.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Counter> counters = new ArrayList<Counter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<Counter> adapter = new ArrayAdapter<Counter>(this,R.layout.activity_listview,counters);
        ListView listView = (ListView) findViewById(R.id.counters_list);
        listView.setAdapter(adapter);
    }

    public void addCounter(View view) {
        Intent intent = new Intent(this, AddCounterActivity.class);
        startActivity(intent);
    }
}
