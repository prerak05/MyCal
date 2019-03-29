package com.prerak.database.dao;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.prerak.database.db.DB;
import com.prerak.database.table.TotalSum;
import com.prerak.database.table.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prerak on 23/1/19.
 */

public class CURDOperation {
    private DB appDatabase;
    private static final String TAG = "CURDOperation";
    private Activity activity;
    private UserData userData;

    public CURDOperation(Activity activity) {
        this.activity = activity;
        appDatabase = Room.databaseBuilder(activity, DB.class, DB.DB_NAME)
                .allowMainThreadQueries().build();
    }

    public void insertData(UserData userData) {
        long id = -1;
        id = appDatabase.getDao().insert(userData);
    }

    public List<UserData> getAllData() {
        List<UserData> userDataList = new ArrayList<>();
        userDataList = appDatabase.getDao().getAllData();
        return userDataList;
    }

    public List<UserData> getDataByPaymentType(String paymentType) {
        List<UserData> userDataList = new ArrayList<>();
        userDataList = appDatabase.getDao().getDataByPaymentType(paymentType);
        return userDataList;
    }

    public UserData getData(String paymentType) {
        UserData userData = new UserData();
        userData = appDatabase.getDao().getData(paymentType);
        return userData;
    }

    public TotalSum getTotalSum(int id) {
        TotalSum totalSum = new TotalSum();
        totalSum = appDatabase.getDao().getTotalSum(id);
        if (appDatabase.isOpen()) {
            Log.e(TAG, "isOpen");
        } else if (appDatabase.inTransaction()) {
            Log.e(TAG, "inTransaction");
        }
        return totalSum;
    }

    public TotalSum getTotalAmount(String paymentOption) {
        TotalSum totalSum = new TotalSum();
        totalSum = appDatabase.getDao().getTotalAmount(paymentOption);
        if (appDatabase.isOpen()) {
            Log.e(TAG, "isOpen");
        } else if (appDatabase.inTransaction()) {
            Log.e(TAG, "inTransaction");
        }
        return totalSum;
    }

    public void updateData(UserData userData) {
        int i = 0;
        i = appDatabase.getDao().updateData(userData);
    }

    public void updateTotalAomunt(String interest, String totalAmount, int id) {
        int i = 0;
        i = appDatabase.getDao().getTotalAmt(interest, totalAmount, id);
    }
}
