package com.prerak.util;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by prerak on 23/1/19.
 */

public class AppUtil {
    private Context mContext;
    private static AppUtil appUtil;

    public AppUtil(Context mContext) {
        this.mContext = mContext;
    }

    public synchronized static AppUtil getInstance(Context mContext) {
        if (appUtil == null) {
            appUtil = new AppUtil(mContext);
        }
        return appUtil;
    }

    public void chooseDate(final EditText editText, final BaseDate dateResponse) {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = simpleDateFormat.format(newDate.getTime());
                dateResponse.onDateSelect(date, editText);


            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime() - 1000);
        datePickerDialog.show();
    }

    /*
  *  Created by : Prerak
  *  Date : 28/12/2018
  *  Purpose : mContext method is use for get a date from date picker dialog
  * */
    public void chooseDateWithoutPreviousRestriction(final EditText editText, final BaseDate dateResponse) {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(newDate.getTime());
                dateResponse.onDateSelect(date, editText);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /*
    *  Created by : Prerak
    *  Date : 03/12/2018
    *  Purpose : mContext method is use for get a time from time picker dialog
    * */
    public void chooseTime(final EditText editText, final BaseTime dataResponse) {
        Calendar newCalendar = Calendar.getInstance();
        int hour = newCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = newCalendar.get(Calendar.MINUTE);
        int second = newCalendar.get(Calendar.SECOND);
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String min = minute + "";
                String hour = hourOfDay + "";
                if (hourOfDay < 10) {
                    hour = "0" + hourOfDay;
                }
                if (minute < 10) {
                    min = "0" + minute;
                }
                dataResponse.onTimeSelected(hour + ":" + min, editText);
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
}
