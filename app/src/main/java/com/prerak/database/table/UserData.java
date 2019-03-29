package com.prerak.database.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by prerak on 22/1/19.
 */

@Entity(tableName = "user_data")
public class UserData {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "date_time")
    private String DateTime;
    @ColumnInfo(name = "payment_type")
    private String paymentType;
    @ColumnInfo(name = "amount")
    private String amount;
    @ColumnInfo(name = "interest")
    private String interest;
    @ColumnInfo(name = "total_amount")
    private String totalAmount;
    @ColumnInfo(name = "interest_amount")
    private String interestAmount;

    private String mm;

    public UserData() {
    }

    public UserData(String dateTime, String paymentType, String amount, String interestAmount) {
        this.DateTime = dateTime;
        this.paymentType = paymentType;
        this.amount = amount;
        this.interestAmount = interestAmount;
        this.totalAmount = String.valueOf(((Integer.valueOf(amount) * Integer.valueOf(interestAmount)) / 100) + Integer.valueOf(amount) );
    }

    public UserData(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(String interestAmount) {
        this.interestAmount = interestAmount;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }
}
