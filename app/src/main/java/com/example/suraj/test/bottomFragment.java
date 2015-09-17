package com.example.suraj.test;

import android.app.Fragment;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.Activity;



public class bottomFragment extends Fragment{

    bottomFragmentListener activityCommander;

    public interface bottomFragmentListener{
        public void switchActivity(String curActivity);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            activityCommander = (bottomFragmentListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.bottom_ragment,container,false);
        final Button button = (Button) view1.findViewById(R.id.check_num);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked(v);
            }
        });
        return view1;
    }

    public void buttonClicked(View v){
        activityCommander.switchActivity(v.getTag().toString());
    }
}


