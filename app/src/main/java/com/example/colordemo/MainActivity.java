
package com.example.colordemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

/*
* MainActivity.java class contains initializing view
* and core functionality of selecting colors
* created on 20 may by nikhil
* */

public class MainActivity extends Activity {
    private RadioButton viewRed,viewGreenLight,viewBlue,viewYellow,viewOrange,viewPurple,viewCyan,viewBrown,viewFuchsia,viewGreenDark;
    private Button buttonReset,buttonUndo;
    public static RadioGroup radioColorGroup;
    public static int color;
    public static final String MySharedPreference = "MyPrefs";
    public static final String KeyList = "listKey";
    public static final String KeyColor = "colorKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //init all view
        initViews();

        radioColorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //get the selected radio button
                if(checkedId == R.id.viewRed){
                    color= ContextCompat.getColor(MainActivity.this,R.color.colorRed);
                }else if(checkedId == R.id.viewGreen){
                    color=ContextCompat.getColor(MainActivity.this,R.color.colorGreenLight);
                }else if(checkedId == R.id.viewBlue){
                    color=ContextCompat.getColor(MainActivity.this,R.color.colorBlue);
                }else if(checkedId == R.id.viewYellow){
                    color=ContextCompat.getColor(MainActivity.this,R.color.colorYellow);
                }else if(checkedId == R.id.viewOrange){
                    color=ContextCompat.getColor(MainActivity.this,R.color.colorOrange);
                }else if(checkedId == R.id.viewPurple) {
                    color=ContextCompat.getColor(MainActivity.this,R.color.colorPurple);
                }else if(checkedId == R.id.viewCyan) {
                    color=ContextCompat.getColor(MainActivity.this,R.color.colorCyan);
                }else if(checkedId == R.id.viewBrown) {
                    color=ContextCompat.getColor(MainActivity.this,R.color.colorBrown);
                }else if(checkedId == R.id.viewFuchsia) {
                    color=ContextCompat.getColor(MainActivity.this,R.color.colorFuchsia);
                }else if(checkedId == R.id.viewGreenDark) {
                    color=ContextCompat.getColor(MainActivity.this,R.color.colorGreenDark);
                }
            }
        });

        //identify color and set radio button
        sharedPreferences = MainActivity.this.getSharedPreferences(MySharedPreference, MODE_PRIVATE);
        color = sharedPreferences.getInt(KeyColor,0);
        if(color == ContextCompat.getColor(MainActivity.this,R.color.colorRed)){
            viewRed.setChecked(true);
        }else if(color == ContextCompat.getColor(MainActivity.this,R.color.colorGreenLight)){
            viewGreenLight.setChecked(true);
        }else if(color == ContextCompat.getColor(MainActivity.this,R.color.colorBlue)){
            viewBlue.setChecked(true);
        }else if(color == ContextCompat.getColor(MainActivity.this,R.color.colorYellow)){
            viewYellow.setChecked(true);
        }else if(color == ContextCompat.getColor(MainActivity.this,R.color.colorOrange)){
            viewOrange.setChecked(true);
        }else if(color == ContextCompat.getColor(MainActivity.this,R.color.colorPurple)){
            viewPurple.setChecked(true);
        }else if(color == ContextCompat.getColor(MainActivity.this,R.color.colorCyan)){
            viewCyan.setChecked(true);
        }else if(color == ContextCompat.getColor(MainActivity.this,R.color.colorBrown)){
            viewBrown.setChecked(true);
        }else if(color == ContextCompat.getColor(MainActivity.this,R.color.colorFuchsia)){
            viewFuchsia.setChecked(true);
        }else if(color == ContextCompat.getColor(MainActivity.this,R.color.colorGreenDark)){
            viewGreenDark.setChecked(true);
        }

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioColorGroup.clearCheck();
                if(MultiTouchEventView.pointsList != null && !MultiTouchEventView.pointsList.isEmpty()) {
                    MultiTouchEventView.pointsList.clear();
                }
                color = Color.TRANSPARENT;
            }
        });

        buttonUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MultiTouchEventView.pointsList != null && !MultiTouchEventView.pointsList.isEmpty()) {
                    MultiTouchEventView.pointsList.remove(MultiTouchEventView.pointsList.get(MultiTouchEventView.pointsList.size() - 1));
                }else{
                    Toast.makeText(MainActivity.this,"No more circle to remove",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        //init radiobuttons
        viewRed=(RadioButton) findViewById(R.id.viewRed);
        viewGreenLight=(RadioButton)findViewById(R.id.viewGreen);
        viewBlue=(RadioButton)findViewById(R.id.viewBlue);
        viewYellow=(RadioButton)findViewById(R.id.viewYellow);
        viewOrange=(RadioButton)findViewById(R.id.viewOrange);
        viewPurple=(RadioButton)findViewById(R.id.viewPurple);
        viewCyan=(RadioButton)findViewById(R.id.viewCyan);
        viewBrown=(RadioButton)findViewById(R.id.viewBrown);
        viewFuchsia=(RadioButton)findViewById(R.id.viewFuchsia);
        viewGreenDark=(RadioButton)findViewById(R.id.viewGreenDark);
        //init buttons
        buttonReset=(Button)findViewById(R.id.buttonReset);
        buttonUndo=(Button)findViewById(R.id.buttonUndo);
        //init radiogroup
        radioColorGroup=(RadioGroup)findViewById(R.id.viewColorGroup);
        //init sharedPrefernce
        sharedPreferences = getSharedPreferences(MySharedPreference, Context.MODE_PRIVATE);

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KeyColor,color);
        Gson gson = new Gson();
        String json = gson.toJson(MultiTouchEventView.pointsList);
        editor.putString(KeyList , json);
        editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("oStop","oStop");
    }
}
