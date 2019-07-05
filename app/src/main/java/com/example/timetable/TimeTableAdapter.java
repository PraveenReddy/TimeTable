package com.example.timetable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.timetable.mtimes.Periods;

import java.util.List;

/**
 * Created by dharma on 9/12/2015.ou
 */
public class TimeTableAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<List<Periods>> mArrayListTimeTables;

    public TimeTableAdapter(Context context, List<List<Periods>> list) {
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
            convertView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(mArrayListTimeTables.get(position), position);
        return convertView;
    }

    class ViewHolder {
        TextView t1, t2, t3, t4, t5, t6, t7;

        ViewHolder(View view) {
            t1 = (TextView) view.findViewById(R.id.textView_sub1);
            t2 = (TextView) view.findViewById(R.id.textView_sub2);
            t3 = (TextView) view.findViewById(R.id.textView_sub3);
            t4 = (TextView) view.findViewById(R.id.textView_sub4);
            t6 = (TextView) view.findViewById(R.id.textView_sub6);
            t5 = (TextView) view.findViewById(R.id.textView_sub5);
            t7 = (TextView) view.findViewById(R.id.textView_sub7);

        }

        public void setData(final List<Periods> list, final int pos) {

            t1.setText(list.get(0).getPeriod());
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(list.get(0));
                }
            });
            t2.setText(list.get(1).getPeriod());
            t3.setText(list.get(2).getPeriod());
            t4.setText(list.get(3).getPeriod());
            t5.setText(list.get(4).getPeriod());
            t6.setText(list.get(5).getPeriod());
            t7.setText(list.get(6).getPeriod());

            t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos != 0)
                        showDialog(list.get(1));
                }
            });
            t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos != 0)
                        showDialog(list.get(2));
                }
            });
            t4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos != 0)
                        showDialog(list.get(3));
                }
            });
            t5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos != 0)
                        showDialog(list.get(4));
                }
            });
            t6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos != 0)
                        showDialog(list.get(5));
                }
            });
            t7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos != 0)
                        showDialog(list.get(6));
                }
            });

        }
    }

    private void showDialog(Periods periods) {
        new AlertDialog.Builder(context)
                .setTitle("Period Info")
                .setMessage(periods.toString())
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
