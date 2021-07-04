package com.karirpad.apps.manager.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.karirpad.apps.model.internal.CompanyData;
import com.karirpad.apps.model.internal.UserData;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created By reynard on 7/3/21.
 */
@Dao
public interface DatabaseDao {

    @Query("SELECT * FROM user_data WHERE id = :id")
    UserData findUserDataById(String id);

    @Query("SELECT * FROM user_data WHERE email = :email")
    UserData findUserDataByEmail(String email);

    @Insert
    void insert(UserData userData);

    @Update
    void update(UserData userData);

    @Query("SELECT * FROM company_data")
    List<CompanyData> findAllCompanyData();

    @Insert
    void insertCompany(CompanyData companyData);
}
