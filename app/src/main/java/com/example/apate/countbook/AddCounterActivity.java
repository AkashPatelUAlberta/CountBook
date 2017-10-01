package com.example.apate.countbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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
    private TextView date;
    private int oldCurrentValue = 0;

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
        date = (TextView) findViewById(R.id.menuDate);

        deleteBtn.setEnabled(false);

        if (getIntent().getStringExtra("key").equals("Edit")) {
            Counter currentCounter = CounterList.counterList.get(getIntent()
                    .getIntExtra("position", 0));
            counterName.setText(currentCounter.getName());
            initialValue.setText(Integer.toString(currentCounter.getInitial_val()));
            currentValue.setText(Integer.toString(currentCounter.getCurrent_val()));
            oldCurrentValue = currentCounter.getCurrent_val();
            comment.setText(currentCounter.getComment());
            date.setText(currentCounter.getDate().toString());
            deleteBtn.setEnabled(true);
        }

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CounterList.counterList.remove(getIntent().getIntExtra("position", 0));
                CounterList.save(view.getContext());
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentValue.getText().toString().length() == 0) {
                    currentValue.setHintTextColor(Color.RED);
                } else {
                    int cv = Integer.valueOf(currentValue.getText().toString());
                    cv++;
                    currentValue.setText(Integer.toString(cv));
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentValue.getText().toString().length() == 0) {
                    currentValue.setHintTextColor(Color.RED);
                } else {
                    int cv = Integer.valueOf(currentValue.getText().toString());
                    if (cv > 0) {
                        cv--;
                    }
                    currentValue.setText(Integer.toString(cv));
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (initialValue.getText().toString().length() == 0) {
                    initialValue.setHintTextColor(Color.RED);
                } else {
                    currentValue.setText(initialValue.getText().toString());
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getIntent().getStringExtra("key").equals("Edit") &&
                        oldCurrentValue != Integer.valueOf(currentValue.getText().toString())) {
                    int position = getIntent().getIntExtra("position", 0);
                    Counter currentCounter = CounterList.counterList.get(getIntent()
                            .getIntExtra("position", 0));
                    currentCounter.setCurrent_val(Integer.valueOf(currentValue
                            .getText().toString()));
                    currentCounter.setName(counterName.getText().toString());
                    currentCounter.setInitial_val(Integer.valueOf(initialValue
                            .getText().toString()));
                    currentCounter.setComment(comment.getText().toString());
                    currentCounter.updateDate();
                    CounterList.save(view.getContext());
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", currentCounter.getName());
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                } else if (getIntent().getStringExtra("key").equals("Edit")) {
                    int position = getIntent().getIntExtra("position", 0);
                    Counter currentCounter = CounterList.counterList.get(getIntent()
                            .getIntExtra("position", 0));
                    currentCounter.setName(counterName.getText().toString());
                    currentCounter.setInitial_val(Integer.valueOf(initialValue
                            .getText().toString()));
                    currentCounter.setComment(comment.getText().toString());
                    CounterList.save(view.getContext());
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", currentCounter.getName());
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                } else {
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
                        CounterList.counterList.add(counter);
                        CounterList.save(view.getContext());
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", counter.getName());
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    }
                }
            }
        });


    }
}
