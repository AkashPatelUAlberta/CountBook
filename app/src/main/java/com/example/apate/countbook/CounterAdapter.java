package com.example.apate.countbook;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * Created by Akash on 2017-09-26.
 */

public class CounterAdapter extends ArrayAdapter<Counter> implements View.OnClickListener{
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtDate;
        TextView txtValue;
        Button increment;
        Button decrement;
    }

    public CounterAdapter(ArrayList<Counter> counters, Context mContext) {
        super(mContext, R.layout.activity_listview, counters);
        this.mContext = mContext;
    }



    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Counter counter = (Counter)object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Counter counter = getItem(position);
        final ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_listview, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.counter);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.date);
            viewHolder.txtValue = (TextView) convertView.findViewById(R.id.counterValue);
            viewHolder.increment = (Button) convertView.findViewById(R.id.increment);
            viewHolder.decrement = (Button) convertView.findViewById(R.id.decrement);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.increment();
                counter.updateDate();
                CounterList.save(view.getContext());
                viewHolder.txtName.setText(counter.getName());
                viewHolder.txtDate.setText(counter.getDate().toString());
                viewHolder.txtValue.setText(Integer.toString(counter.getCurrent_val()));
            }
        });

        viewHolder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.decrement();
                counter.updateDate();
                CounterList.save(view.getContext());
                viewHolder.txtName.setText(counter.getName());
                viewHolder.txtDate.setText(counter.getDate().toString());
                viewHolder.txtValue.setText(Integer.toString(counter.getCurrent_val()));
            }
        });

        viewHolder.txtName.setText(counter.getName());
        viewHolder.txtDate.setText(counter.getDate().toString());
        viewHolder.txtValue.setText(Integer.toString(counter.getCurrent_val()));



        return convertView;
    }

}
