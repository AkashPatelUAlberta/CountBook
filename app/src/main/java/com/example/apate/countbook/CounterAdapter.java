package com.example.apate.countbook;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by Akash on 2017-09-26.
 */

public class CounterAdapter extends BaseAdapter implements DialogInterface.OnClickListener{

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    Counter tempValues=null;
    int i=0;

    public CounterAdapter(Activity activity, ArrayList data, Resources res) {
        this.activity = activity;
        this.data = data;
        this.res = res;

        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public TextView counter_name;
        public TextView current_val;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if(convertView==null) {
            vi = inflater.inflate(R.layout.tabitem, null);
            holder = new ViewHolder();
            holder.counter_name = (TextView) vi.findViewById(R.id.counter_name);
            holder.current_val = (TextView) vi.findViewById(R.id.counter_val);
            vi.setTag( holder );
        } else {
            holder=(ViewHolder)vi.getTag();
        }

        if(data.size()<=0) {
            holder.counter_name.setText("No Data");
        } else {
            tempValues = null;
            tempValues = (Counter) data.get(position);

            holder.counter_name.setText(tempValues.getName());
            holder.current_val.setText(Integer.toString(tempValues.getCurrent_val()));

            vi.setOnClickListener(new OnClickLister(position));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    private class OnItemClickListener  implements OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            CustomListViewAndroidExample sct = (CustomListViewAndroidExample)activity;
            sct.onItemClick(mPosition);
        }
    }
}
