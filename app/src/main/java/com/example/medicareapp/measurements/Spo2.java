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

public class Spo2 extends AppCompatActivity {
    TextView time_height;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Calendar c;
    private Context ctx = this;
    private MoneySelectRuleView msrvMoney;
    private TextView tvMoney;
    private EditText etMoney;
    private float moneyBalance;
    private boolean isMoneySloped;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spo2);
        time_height=findViewById(R.id.time_weight);
        msrvMoney=findViewById(R.id.msrv_money);
        tvMoney=findViewById(R.id.tv_money);

        msrvMoney.setValue(100,0,0,1,10);

        tvMoney.setText(Integer.toString(msrvMoney.getValue()));
        moneyBalance = msrvMoney.getBalance();
        msrvMoney.setOnValueChangedListener(new MoneySelectRuleView.OnValueChangedListener() {
            @Override
            public void onValueChanged(int newValue) {
                tvMoney.setText(Integer.toString(newValue));

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



    private void toggleSettingsShow(@IdRes int layoutId) {
        LinearLayout llSettings = findViewById(layoutId);
        llSettings.setVisibility(llSettings.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }

//    public void onClick(View view) {
//        switch (view.getId()) {
//
//            case R.id.tv_money_indicator:
//                toggleSettingsShow(R.id.ll_money_settings);
//                break;
//            case R.id.btn_set_money:
//                float money = getMoney();
//                msrvMoney.setValue(money);
//                break;
//            case R.id.btn_set_balance:
//                moneyBalance = getMoney();
//                msrvMoney.setBalance(moneyBalance);
//                isMoneySloped = false;
//                break;
//            default: break;
//        }
//    }

    private float getMoney() {
        String moneyStr = etMoney.getText().toString();
        if (moneyStr.isEmpty()) {
            moneyStr = "0";
        }
        return Float.parseFloat(moneyStr);
    }


}
