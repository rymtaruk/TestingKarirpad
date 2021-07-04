package com.karirpad.apps.manager.database.repository;

import android.app.Application;

import com.karirpad.apps.manager.database.DatabaseManager;
import com.karirpad.apps.model.internal.CompanyData;
import com.karirpad.apps.model.internal.UserData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created By reynard on 7/4/21.
 */
public class DatabaseRepository implements IDatabaseRepository {
    private final DatabaseManager databaseManager;

    @Inject
    public DatabaseRepository(Application applicationContext) {
        this.databaseManager = DatabaseManager.getInstance(applicationContext);
    }

    @Override
    public Flowable<UserData> findUserData(String id) {
        return Flowable.just(databaseManager.databaseDao().findUserDataById(id));
    }

    @Override
    public UserData findUserDataByEmail(String email) {
        return databaseManager.databaseDao().findUserDataByEmail(email);
    }

    @Override
    public void insert(UserData userData) {
        databaseManager.databaseDao().insert(userData);
    }

    @Override
    public void update(UserData userData) {
        databaseManager.databaseDao().update(userData);
    }

    @Override
    public Flowable<List<CompanyData>> getFlowableCompanyData() {
        return (Flowable.fromArray(databaseManager.databaseDao().findAllCompanyData()));
    }

    @Override
    public void insertCompany(CompanyData companyData) {
        databaseManager.databaseDao().insertCompany(companyData);
    }
}
