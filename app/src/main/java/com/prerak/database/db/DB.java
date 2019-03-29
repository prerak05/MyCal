package com.prerak.database.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.prerak.database.dao.UserDataDao;
import com.prerak.database.table.UserData;

/**
 * Created by prerak on 22/1/19.
 */
@Database(entities = {UserData.class}, version = 1)
public abstract class DB extends RoomDatabase {
    public static final String DB_NAME = "app_db";

    public abstract UserDataDao getDao();

    /*
      *  This method check is connection open or not.
      * */
    @Override
    public boolean isOpen() {
        return super.isOpen();
    }

    /*
    *  This method check is operation transaction is running or not.
    * */
    @Override
    public boolean inTransaction() {
        return super.inTransaction();
    }

}
