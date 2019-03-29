package com.prerak.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.prerak.database.table.TotalSum;
import com.prerak.database.table.UserData;

import java.util.List;

/**
 * Created by prerak on 22/1/19.
 */

@Dao
public interface UserDataDao {

    @Insert
    long insert(UserData userData);

    @Query("SELECT * FROM user_data")
    List<UserData> getAllData();

    @Query("SELECT * FROM user_data WHERE payment_type = :paymentType")
    UserData getData(String paymentType);

    //    @Query("SELECT * FROM user_data WHERE payment_type = :paymentType")
//    List<UserData> getDataByPaymentType(String paymentType);
    @Query("SELECT *,substr(date_time,6,2) as mm FROM user_data WHERE payment_type = :paymentType AND substr(date_time,6,2) = :month")
    List<UserData> getDataByPaymentType(String paymentType,String month);

    @Query("SELECT *, (amount * interest_amount)/100 AS interst, (((amount * interest_amount)/100) + amount) AS totalAmount FROM user_data WHERE id = :id")
    TotalSum getTotalSum(int id);

    @Query("SELECT SUM(total_amount) AS totalInterestedAmount FROM user_data WHERE payment_type = :paymentType AND substr(date_time,6,2) = :month")
    TotalSum getTotalAmount(String paymentType,String month);

    @Query("UPDATE user_data SET interest = :interest, total_amount = :totalAmount WHERE id = :id")
    int getTotalAmt(String interest, String totalAmount, int id);

    @Update
    int updateData(UserData userData);
}
