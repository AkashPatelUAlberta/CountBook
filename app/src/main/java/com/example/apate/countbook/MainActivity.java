package com.example.apate.countbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Counter> counters = CounterList.counterList;
    ListView listView;
    private static CounterAdapter adapter;
    public static int totalCounters;
    private TextView numCounters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addCounterBtn = (Button) findViewById(R.id.add_counter_btn);
        listView = (ListView) findViewById(R.id.counters_list);
        numCounters = (TextView) findViewById(R.id.numOfCounters);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, AddCounterActivity.class);
                intent.putExtra("key", "Edit");
                intent.putExtra("position", position);
                startActivityForResult(intent, 2);
            }
        });

        addCounterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCounterActivity.class);
                intent.putExtra("key", "AddButton");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adapter.notifyDataSetChanged();
        if (resultCode == 1) {
            String result = data.getStringExtra("result");
        } else if (resultCode == Activity.RESULT_CANCELED) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        CounterList.load(this);
        adapter = new CounterAdapter(CounterList.counterList, getApplicationContext());
        listView.setAdapter(adapter);
        totalCounters = CounterList.counterList.size();
        numCounters.setText("Counters: " + Integer.toString(totalCounters));

    }
}
