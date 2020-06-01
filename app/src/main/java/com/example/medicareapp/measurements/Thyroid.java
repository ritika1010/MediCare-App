package com.example.medicareapp.measurements;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medicareapp.R;
import com.google.android.material.snackbar.Snackbar;
import com.zjun.widget.MoneySelectRuleView;
import com.zjun.widget.RuleView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Thyroid extends AppCompatActivity {
    TextView time_height;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Calendar c;
    private Context ctx = this;
    private RuleView gv1;
    private RuleView gv2;
    private RuleView gv3;
    private RuleView gv4;
    private RuleView gv5;
    private TextView t3;
    private EditText t3_edit;
    private EditText t4_edit;
    private EditText t3_free_edit;
    private EditText t4_free_edit;
    private EditText tsh_edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thyroid);
        time_height=findViewById(R.id.time_weight);
        gv1=findViewById(R.id.gv_1);
        gv2=findViewById(R.id.gv_2);
        gv3=findViewById(R.id.gv_3);
        gv4=findViewById(R.id.gv_4);
        gv5=findViewById(R.id.gv_5);

        t3=findViewById(R.id.t3);
        t3_edit=findViewById(R.id.t3_et);
        t4_edit=findViewById(R.id.t4_et);
        t3_free_edit=findViewById(R.id.t3_free_et);
        t4_free_edit=findViewById(R.id.t4_free_et);
        tsh_edit=findViewById(R.id.tsh_et);

        gv1.setValue(40, 280, 80, 1f, 10);
        gv2.setValue(0, 20, 5, 0.1f, 10);
        gv3.setValue(200, 500, 260, 1f, 10);
        gv4.setValue(0, 15, 1, 0.1f, 10);
        gv5.setValue(0, 15, 8, 0.1f, 10);

        toggleSettingsShow(R.id.gv_1);
        toggleSettingsShow(R.id.gv_2);
        toggleSettingsShow(R.id.gv_3);
        toggleSettingsShow(R.id.gv_4);
        toggleSettingsShow(R.id.gv_5);

        t3_edit.setText(Float.toString(gv1.getCurrentValue()));
        gv1.setOnValueChangedListener(new RuleView.OnValueChangedListener() {
            @Override
            public void onValueChanged(float value) {
                t3_edit.setText(Float.toString(value));
            }
        });

        t4_edit.setText(Float.toString(gv2.getCurrentValue()));
        gv2.setOnValueChangedListener(new RuleView.OnValueChangedListener() {
            @Override
            public void onValueChanged(float value) {
                t4_edit.setText(Float.toString(value));
            }
        });

        t3_free_edit.setText(Float.toString(gv3.getCurrentValue()));
        gv3.setOnValueChangedListener(new RuleView.OnValueChangedListener() {
            @Override
            public void onValueChanged(float value) {
                t3_free_edit.setText(Float.toString(value));
            }
        });

        t4_free_edit.setText(Float.toString(gv4.getCurrentValue()));
        gv4.setOnValueChangedListener(new RuleView.OnValueChangedListener() {
            @Override
            public void onValueChanged(float value) {
                t4_free_edit.setText(Float.toString(value));
            }
        });

        tsh_edit.setText(Float.toString(gv5.getCurrentValue()));
        gv5.setOnValueChangedListener(new RuleView.OnValueChangedListener() {
            @Override
            public void onValueChanged(float value) {
                tsh_edit.setText(Float.toString(value));
            }
        });


//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//        time_height.setText(formatter.format(date));
        mYear= Calendar.getInstance().get(Calendar.YEAR);
        mMonth=Calendar.getInstance().get(Calendar.MONTH)+1;
        mDay=Calendar.getInstance().get(Calendar.DAY_OF_MONTH) ;
        mHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) ;
        mMinute = Calendar.getInstance().get(Calendar.MINUTE);

        time_height.setText(mDay+"/"+mMonth+"/"+mYear+"  "+mHour+":"+mMinute);

        time_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_Datepicker();
                show_Timepicker();

            }
        });

        findViewById(R.id.show_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleSettingsShow(R.id.gv_1);

            }
        });

        findViewById(R.id.show_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleSettingsShow(R.id.gv_2);

            }
        });
        findViewById(R.id.show_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleSettingsShow(R.id.gv_3);

            }
        });

        findViewById(R.id.show_button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleSettingsShow(R.id.gv_4);

            }
        });

        findViewById(R.id.show_button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleSettingsShow(R.id.gv_5);

            }
        });




    }

    private void show_Datepicker() {
        c = Calendar.getInstance();
        int mYearParam = mYear;
        int mMonthParam = mMonth-1;
        int mDayParam = mDay;

        DatePickerDialog datePickerDialog = new DatePickerDialog(ctx,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mMonth = monthOfYear + 1;
                        mYear=year;
                        mDay=dayOfMonth;
                        time_height.setText(mDay+"/"+mMonth+"/"+mYear+"  "+mHour+":"+mMinute);
                    }
                }, mYearParam, mMonthParam, mDayParam);

        datePickerDialog.show();
        //show_Timepicker();

    }

    private void show_Timepicker() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(ctx,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int pHour,
                                          int pMinute) {

                        mHour = pHour;
                        mMinute = pMinute;
                        time_height.setText(mDay+"/"+mMonth+"/"+mYear+"  "+mHour+":"+mMinute);
                    }
                }, mHour, mMinute, true);

        timePickerDialog.show();
    }



    private void toggleSettingsShow(@IdRes int layoutId) {
        RuleView gv_setting = findViewById(layoutId);
        gv_setting.setVisibility(gv_setting.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }



}
