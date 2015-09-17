package com.example.suraj.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Scene;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Color;
import android.view.View;
import android.content.SharedPreferences;
import android.content.Context;
import android.view.ViewGroup;
import android.view.MotionEvent;
import android.transition.TransitionManager;


import java.util.Calendar;

public class MainActivity extends Activity implements bottomFragment.bottomFragmentListener{

    String curNum;
    SharedPreferences sharedPref;
    public static final String MyPREFERENCES = "MyPrefs" ;
    ViewGroup mainLayout;

    @Override
    public void switchActivity(String curActivity) {
        Intent i = new Intent(this, NumCheckActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mainLayout = (ViewGroup) findViewById(R.id.activity_main);
        Button back = (Button) findViewById(R.id.backspace);
        final TextView num = (TextView) findViewById(R.id.number);
        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                num.setText("");
                curNum = "";
                return true;
            }
        });
    }
    public void onClick(View v){
        sharedPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        TextView num = (TextView) findViewById(R.id.number);
        Button save = (Button) findViewById(R.id.saveButton);
        curNum = num.getText().toString();
        if(v.getId() == save.getId()){
            Calendar c = Calendar.getInstance();
            String time = Long.toString(c.getTimeInMillis());
            prefEditor.putString(curNum.toString(), time);
            prefEditor.commit();
            num.setText("");
        }
        else if(v.getId() == R.id.backspace){
            if(curNum.length() > 0) {
                curNum = curNum.substring(0, curNum.length() - 1);
                num.setText(curNum);
            }
        }
        else {
            num.setText(num.getText() + v.getTag().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
