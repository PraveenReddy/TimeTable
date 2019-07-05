package com.example.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.timetable.mtimes.Faculty;

import java.util.ArrayList;

/**
 * Created by dharma on 9/12/2015.
 */
public class FacultyAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private ArrayList<Faculty> mArrayListTimeTables;

    public FacultyAdapter(Context context, ArrayList<Faculty> list) {
        this.context = context;
        mArrayListTimeTables = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mArrayListTimeTables.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayListTimeTables.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewSubject.setText(mArrayListTimeTables.get(position).getFacultyName());
        return convertView;
    }

    class ViewHolder {
        TextView textViewSubject;

        ViewHolder(View view) {
            textViewSubject = (TextView) view.findViewById(R.id.textView_sub1);
        }
    }

}
