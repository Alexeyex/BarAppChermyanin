package com.example.barappchermyanin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.GregorianCalendar;

public class TermsActivity extends AppCompatActivity {
    private Button mChooseStartDate;
    private Button mChooseEndDate;
    private CalendarView mStartDateCalendar;
    private CalendarView mEndDateCalendar;
    private Button mBtnOK;
    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        initViews();
    }

    private void initViews() {
        mChooseStartDate = findViewById(R.id.chooseStartDate);
        mChooseEndDate = findViewById(R.id.chooseEndDate);
        mStartDateCalendar = findViewById(R.id.startDateCalendar);
        mEndDateCalendar = findViewById(R.id.endDateCalendar);
        mBtnOK = findViewById(R.id.btnOK);

        mStartDateCalendar.setVisibility(View.GONE);
        mEndDateCalendar.setVisibility(View.GONE);
        mChooseStartDate.setOnClickListener(view -> {
            mStartDateCalendar.setVisibility(View.VISIBLE);
            mEndDateCalendar.setVisibility(View.GONE);
        });

        mChooseEndDate.setOnClickListener(view -> {
            mEndDateCalendar.setVisibility(View.VISIBLE);
            mStartDateCalendar.setVisibility(View.GONE);
        });
        mStartDateCalendar.setOnDateChangeListener((calendarView, i, i1, i2) -> {

        });

        mEndDateCalendar.setOnDateChangeListener((calendarView, i, i1, i2) -> {

        });

        mStartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                mStartDateTxt = i + "-" + i1 + "-" + i2;
                mChooseStartDate.setText(getString(R.string.choose_StartDate) + mStartDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                mStartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        mEndDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                mEndDateTxt = i + "-" + i1 + "-" + i2;
                mChooseEndDate.setText(getString(R.string.choose_EndDate) + mEndDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                mEndDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mStartDate >= mEndDate) || ((mStartDate == 0) || (mEndDate == 0))) {
                    Toast.makeText(TermsActivity.this, "Ошибка. Введите вверные данные", Toast.LENGTH_LONG).show();
                    mChooseStartDate.setText("Дата-время старта задачи:");
                    mChooseEndDate.setText("Дата-время окончания задачи:");
                } else {
                    Toast.makeText(TermsActivity.this, "старт: " + mStartDateTxt + " окончаниe: " + mEndDateTxt, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}