package com.example.medicareapp.measurements;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.text.TextWatcher;

import com.example.medicareapp.R;
import com.zjun.widget.RuleView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Weight extends AppCompatActivity {
    TextView time_height;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Calendar c;
    private Context ctx = this;
    private TextView tvValue;
    private RuleView gvRule;
    private TextView tvUnit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        time_height=findViewById(R.id.time_weight);

        tvValue = findViewById(R.id.tv_value);
        gvRule = (RuleView) this.findViewById(R.id.gv_1);
        tvUnit=findViewById(R.id.tv_unit);

        if(gvRule==null)
        {
            Toast.makeText(this,"null gvrule",Toast.LENGTH_LONG).show();
            finish();
        }
        if(tvValue==null)
        {
            Toast.makeText(this,"null tvrule",Toast.LENGTH_LONG).show();
            finish();
        }

        gvRule.setValue(0, 150, 50, 0.1f, 10);
        tvValue.setText(Float.toString(gvRule.getCurrentValue()));
        gvRule.setOnValueChangedListener(new RuleView.OnValueChangedListener() {
            @Override
            public void onValueChanged(float value) {
                tvValue.setText(Float.toString(value));
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

    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.tv_rule_indicator:
//                toggleSettingsShow(R.id.ll_rule_settings);
//                break;
//            case R.id.btn_50:
//                gvRule.setCurrentValue(gvRule.getMinValue() == 11 ? 15 : 50);
//                break;
            case R.id.btn_change:
                toggleValue();
                break;

            default: break;
        }
    }

    private void toggleSettingsShow(@IdRes int layoutId) {
        LinearLayout llSettings = findViewById(layoutId);
        llSettings.setVisibility(llSettings.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }

    private void toggleValue() {
        if (tvUnit.getText().equals("pounds")) {
            float temp= (float) (Double.parseDouble((String) tvValue.getText().toString())/2.205);
            gvRule.setValue(0, 150, temp, 0.1f, 10);
            tvUnit.setText("kg");
        } else {
            float temp= (float) (Double.parseDouble((String) tvValue.getText().toString())*2.205);
            gvRule.setValue(0, 350,temp, 0.1f, 10);
            tvUnit.setText("pounds");

        }
    }




}
