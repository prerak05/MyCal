package com.prerak.util;

import android.arch.persistence.room.TypeConverter;
import android.provider.SyncStateContract;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by prerak on 3/29/2019.
 */

public class TimestampConverter {
    static DateFormat df = new SimpleDateFormat("YYYY-MM-DD");

    @TypeConverter
    public static String fromTimestamp(String value) {
        if (value != null) {
            try {
                return df.parse(value).toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }
}
