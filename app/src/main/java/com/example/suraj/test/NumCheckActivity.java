package com.example.suraj.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class NumCheckActivity extends Activity {
    SharedPreferences sharedPref;
    public static final String MyPREFERENCES = "MyPrefs" ;
    Map enteredNums;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_num_check);
        enteredNums = sharedPref.getAll();
    }
    public void onClick(View v){
        if(v.getId() == R.id.delAllButton){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            editor.commit();
            enteredNums = sharedPref.getAll();
        }
        else{
            if(enteredNums.size() == 0){
                Toast.makeText(this, "Sorry you have not saved any lottery numbers", Toast.LENGTH_LONG).show();
                return;
            }
            EditText special = (EditText) findViewById(R.id.specialPrizeNum);
            EditText grand = (EditText) findViewById(R.id.grandPrizeNum);
            EditText reg1 = (EditText) findViewById(R.id.regPrize1);
            EditText reg2 = (EditText) findViewById(R.id.regPrize2);
            EditText reg3 = (EditText) findViewById(R.id.regPrize3);
            EditText extra1 = (EditText) findViewById(R.id.lastPrize1);
            EditText extra2 = (EditText) findViewById(R.id.lastPrize2);
            EditText extra3 = (EditText) findViewById(R.id.lastPrize3);
            String[] winningNums = new String[8];
            winningNums[0] = special.getText().toString();
            winningNums[1] = grand.getText().toString();
            winningNums[2] = reg1.getText().toString();
            winningNums[3] = reg2.getText().toString();
            winningNums[4] = reg3.getText().toString();
            winningNums[5] = "00000"+extra1.getText().toString();
            winningNums[6] = "00000"+extra2.getText().toString();
            winningNums[7] = "00000"+extra3.getText().toString();
            for(int i = 0; i<winningNums.length; i++){
                if(i<5 && winningNums[i].length()<8){
                    //winningNums[i]="00000000";
                    Toast.makeText(this, "Invalid number entered in field " + (i+1), Toast.LENGTH_LONG).show();
                    return;

                }
                else if(i>4 && winningNums[i].length()<3) {
                    //winningNums[i] = "000";
                    Toast.makeText(this, "Invalid number entered in field " + i+1, Toast.LENGTH_LONG).show();
                    return;
                }
            }
            Intent i = new Intent(this, ResultsActivity.class);
            i.putExtra("winningNumbers", winningNums);
            startActivity(i);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_num_check, menu);
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
