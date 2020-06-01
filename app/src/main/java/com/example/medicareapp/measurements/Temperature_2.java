package com.example.medicareapp.measurements;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.example.medicareapp.R;
import com.zjun.widget.ThermometerView;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class Temperature_2 extends AppCompatActivity {

    private ThermometerView thermometerTv;
    public EditText currVal;
    float C,F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_2);

        thermometerTv = findViewById(R.id.tv_thermometer);
        currVal=findViewById(R.id.currVal);
        C=thermometerTv.getCurValue();
        F=(C*9/5)+32;
        currVal.setText(String.valueOf(C)+"C or "+String.valueOf(F)+"F");

//        findViewById(R.id.btn_anim).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                thermometerTv.setValueAndStartAnim(getRandomValue());
//            }
//        });
//        findViewById(R.id.btn_operate).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                thermometerTv.setCurValue(getRandomValue());
//            }
//        });

        findViewById(R.id.incr_val).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C=increase_val();
                thermometerTv.setCurValue(C);
                F=(C*9/5)+32;
                currVal.setText(String.valueOf(C)+"C or "+String.valueOf(F)+"F");

            }
        });

        findViewById(R.id.decr_val).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C=decrease_val();
                thermometerTv.setCurValue(C);
                F=(C*9/5)+32;
                currVal.setText(String.valueOf(C)+"C or "+String.valueOf(F)+"F");
            }
        });

        findViewById(R.id.incr_val_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C=increase_val_1();
                thermometerTv.setCurValue(C);
                F=(C*9/5)+32;
                currVal.setText(String.valueOf(C)+"C or "+String.valueOf(F)+"F");
            }
        });

        findViewById(R.id.decr_val_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C=decrease_val_1();
                thermometerTv.setCurValue(C);
                F=(C*9/5)+32;
                currVal.setText(String.valueOf(C)+"C or "+String.valueOf(F)+"F");
            }
        });



    }

//    private float getRandomValue(){
//        float value = new Random().nextFloat() * 7 + 35;
//        Log.i("MainActivity", "current value: " + value);
//        return value;
//    }

    private float increase_val(){
        float curr=thermometerTv.getCurValue();
        curr+=1;
        return curr;

    }

    private float decrease_val(){
        float curr=thermometerTv.getCurValue();
        curr-=1;
        return curr;

    }
    private float increase_val_1(){
        float curr=thermometerTv.getCurValue();
        curr+=0.1;
        return curr;

    }

    private float decrease_val_1(){
        float curr=thermometerTv.getCurValue();
        curr-=0.1;
        return curr;

    }

    public void set(View view)
    {
        float C= Integer.parseInt(currVal.getText().toString());
        thermometerTv.setCurValue(C);
    }

}
