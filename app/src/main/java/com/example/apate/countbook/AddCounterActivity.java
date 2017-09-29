package com.example.apate.countbook;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddCounterActivity extends AppCompatActivity {
    private Button saveBtn;
    private Button resetBtn;
    private Button deleteBtn;
    private Button plus;
    private Button minus;
    private EditText counterName;
    private EditText initialValue;
    private EditText currentValue;
    private EditText comment;

    Bundle bundle = getIntent().getExtras();
    ArrayList<Counter> counterList = bundle.getIntegerArrayList("counterList");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Add A Counter");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);

        saveBtn = (Button) findViewById(R.id.save);
        resetBtn = (Button) findViewById(R.id.reset);
        deleteBtn = (Button) findViewById(R.id.delete);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        counterName = (EditText) findViewById(R.id.counterName);
        initialValue = (EditText) findViewById(R.id.initialValue);
        currentValue = (EditText) findViewById(R.id.currentValue);
        comment = (EditText) findViewById(R.id.comments);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean nameCheck = false;
                boolean initialValueCheck = false;

                if (counterName.getText().toString().length() == 0) {
                    counterName.setHintTextColor(Color.RED);
                } else {
                    nameCheck = true;
                }

                if (initialValue.getText().toString().length() == 0) {
                    initialValue.setHintTextColor(Color.RED);
                } else {
                    initialValueCheck = true;
                }

                if (currentValue.getText().toString().length() == 0) {
                    currentValue.setText(initialValue.getText().toString());
                }

                if (nameCheck && initialValueCheck) {
                    Counter counter = new Counter(counterName.getText().toString(),
                            Integer.valueOf(currentValue.getText().toString()),
                            Integer.valueOf(initialValue.getText().toString()),
                            comment.getText().toString());
                    Intent intent = new Intent(AddCounterActivity.this, MainActivity.class);
                    startActivity(intent);
                }


            }
        });
    }
}
