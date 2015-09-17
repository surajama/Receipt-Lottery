package com.example.suraj.test;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import android.os.Handler;

public class ResultsActivity extends Activity {
    SharedPreferences sharedPref;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String[] winningNums;
    String[] storedNums;
    Map enteredNumsMap;
    ArrayList<Pair> results = new ArrayList<Pair>();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ListAdapter resultsAdapter = new ResultsListAdapter(ResultsActivity.this, results.toArray(new Pair[results.size()]), enteredNumsMap);
            ListView resultList = (ListView) findViewById(R.id.resultsList);
            resultList.setAdapter(resultsAdapter);
            TextView left = (TextView) findViewById(R.id.leftTableTitle);
            TextView right = (TextView) findViewById(R.id.rightTableTitle);
            TextView center = (TextView) findViewById(R.id.wonOrLost);
            if(results.size() <= 1){
                left.setText("");
                right.setText("");
                center.setText("Sorry, you didn't win this time :(");
            }
            else if(results.size() > 2){
                left.setText("Winning Numbers");
                center.setText("Congratulations, you won!");
            }
            else{
                center.setText("Congratulations, you won!");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] typeExample = new String[2];
        sharedPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle data = getIntent().getExtras();
        winningNums = data.getStringArray("winningNumbers");
        storedNums = sharedPref.getAll().keySet().toArray(typeExample);
        enteredNumsMap = sharedPref.getAll();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                calculateResults();
                handler.sendEmptyMessage(0);
            }

        };
        Thread calculateThread = new Thread(r);
        calculateThread.start();
    }
    void calculateResults(){
        System.out.println(storedNums.length);
        for(int s = 0; s<storedNums.length-1; s++){
            for(int w = 0; w<winningNums.length; w++){
                switch(w){
                    case 0:
                        System.out.println(s+" "+w);
                        if(storedNums[s].equals(winningNums[w])){
                            results.add(new Pair(storedNums[s], 10000000));
                            break;
                        }
                    case 1:
                        if(storedNums[s].equals(winningNums[w])){
                            results.add(new Pair(storedNums[s], 2000000));
                            break;
                        }
                    case 2:case 3:case 4:
                        if(storedNums[s].equals(winningNums[w])){
                            results.add(new Pair(storedNums[s], 200000));
                            break;
                        }
                        if(storedNums[s].substring(1).equals(winningNums[w].substring(1))){
                            results.add(new Pair(storedNums[s], 40000));
                            break;
                        }
                        if(storedNums[s].substring(2).equals(winningNums[w].substring(2))){
                            results.add(new Pair(storedNums[s], 10000));
                            break;
                        }
                        if(storedNums[s].substring(3).equals(winningNums[w].substring(3))){
                            results.add(new Pair(storedNums[s], 4000));
                            break;
                        }
                        if(storedNums[s].substring(4).equals(winningNums[w].substring(4))){
                            results.add(new Pair(storedNums[s], 1000));
                            break;
                        }
                        if(storedNums[s].substring(5).equals(winningNums[w].substring(5))){
                            results.add(new Pair(storedNums[s], 200));
                            break;
                        }
                    case 5:case 6:case 7:
                        if(storedNums[s].substring(5).equals(winningNums[w].substring(5))){
                            results.add(new Pair(storedNums[s], 200));
                            break;
                        }
                }
            }
        }
        int total = 0;
        for(int i = 0; i<results.size(); i++){
            total += results.get(i).prizeMoney;
        }
        results.add(new Pair("Total:", total));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class Pair{
    String num;
    int prizeMoney;

    Pair(String _num, int prize){
        num = _num;
        prizeMoney = prize;
    }
}
