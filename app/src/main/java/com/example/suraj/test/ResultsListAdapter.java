package com.example.suraj.test;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

class ResultsListAdapter extends ArrayAdapter<Pair> {
    Map enteredNums;

    public ResultsListAdapter(Context context, Pair[] results, Map enteredNumsMap) {
        super(context, R.layout.list_row, results);
        enteredNums = enteredNumsMap;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View listRow = inflater.inflate(R.layout.list_row, parent, false);
        TextView winningNum = (TextView) listRow.findViewById(R.id.winningNum);
        TextView winningDate = (TextView) listRow.findViewById(R.id.date);
        TextView prizeMoney = (TextView) listRow.findViewById(R.id.winningAmt);
        Pair singleResult = getItem(position);

        winningNum.setText(singleResult.num);
        if(singleResult.num.equals("Total:")){
            //TableLayout.LayoutParams llp = new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //llp.setMargins(100,0,0,0);
            //prizeMoney.setLayoutParams(llp);
            winningDate.setText("1111111111111111111");
            winningDate.setAlpha(0);
        }
        else {
            long time = Long.parseLong((String) enteredNums.get(singleResult.num));
            Date savedTime = new Date(time);
            DateFormat format = new SimpleDateFormat("EEE, MMM d, yyyy");
            String formattedTime = format.format(time);
            winningDate.setText("Date: " + formattedTime);
        }
        prizeMoney.setText(Integer.toString(singleResult.prizeMoney)+"nt");
        return listRow;
    }
}
