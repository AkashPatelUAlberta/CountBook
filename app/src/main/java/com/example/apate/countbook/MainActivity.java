package com.example.apate.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Counter> counters = new ArrayList<Counter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addCounterBtn = (Button) findViewById(R.id.add_counter_btn);

        ArrayAdapter<Counter> adapter = new ArrayAdapter<Counter>(this,R.layout.activity_listview,counters);
        ListView listView = (ListView) findViewById(R.id.counters_list);
        listView.setAdapter(adapter);

        addCounterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCounterActivity.class);
                startActivity(intent);
            }
        });
    }
}
