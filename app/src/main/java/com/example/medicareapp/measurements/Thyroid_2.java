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

public class Thyroid_2 extends AppCompatActivity {
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
    private EditText temp;
    private EditText t3_edit;
    private EditText t4_edit;
    private EditText t3_free_edit;
    private EditText t4_free_edit;
    private EditText tsh_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thyroid_2);
        time_height=findViewById(R.id.time_weight);

        t3_edit=findViewById(R.id.t3_et);
        t4_edit=findViewById(R.id.t4_et);
        t3_free_edit=findViewById(R.id.t3_free_et);
        t4_free_edit=findViewById(R.id.t4_free_et);
        tsh_edit=findViewById(R.id.tsh_et);

        temp=t3_edit;

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
                temp.setText(Float.toString(value));

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
            case R.id.show_button1:
                temp=t3_edit;
                tvUnit.setText("ng/dL");
                gvRule.setValue(40, 280, 80, 1f, 10);
                break;

            case R.id.show_button2:
                temp=t4_edit;
                tvUnit.setText("µg/dL");
                gvRule.setValue(0, 20, 5, 0.1f, 10);
                break;

            case R.id.show_button3:
                temp=t3_free_edit;
                tvUnit.setText("pg/dL");
                gvRule.setValue(200, 500, 260, 1f, 10);
                break;

            case R.id.show_button4:
                temp=t4_free_edit;
                tvUnit.setText("µg/dL");
                gvRule.setValue(0, 15, 1, 0.1f, 10);
                break;

            case R.id.show_button5:
                temp=tsh_edit;
                tvUnit.setText("µIU/ml");
               gvRule.setValue(0, 15, 8, 0.1f, 10);
                break;

            default: break;
        }
    }

    private void toggleSettingsShow(@IdRes int layoutId) {
        LinearLayout llSettings = findViewById(layoutId);
        llSettings.setVisibility(llSettings.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }

//    private void toggleValue() {
//        if (tvUnit.getText().equals("pounds")) {
//            float temp= (float) (Double.parseDouble((String) tvValue.getText().toString())/2.205);
//            gvRule.setValue(0, 150, temp, 0.1f, 10);
//            tvUnit.setText("kg");
//        } else {
//            float temp= (float) (Double.parseDouble((String) tvValue.getText().toString())*2.205);
//            gvRule.setValue(0, 350,temp, 0.1f, 10);
//            tvUnit.setText("pounds");
//
//        }
//    }




}
