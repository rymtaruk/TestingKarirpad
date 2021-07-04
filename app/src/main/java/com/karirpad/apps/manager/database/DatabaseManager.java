package com.karirpad.apps.manager.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.karirpad.apps.manager.database.dao.DatabaseDao;
import com.karirpad.apps.model.internal.CompanyData;
import com.karirpad.apps.model.internal.UserData;

/**
 * Created By reynard on 7/3/21.
 */
@Database(entities = {UserData.class, CompanyData.class}, version = 1, exportSchema = false)
public abstract class DatabaseManager extends RoomDatabase {

    public abstract DatabaseDao databaseDao();

    public static DatabaseManager getInstance(Application applicationContext){
        return Room.databaseBuilder(applicationContext, DatabaseManager.class, DatabaseManager.class.getName()).allowMainThreadQueries().build();
    }
}
