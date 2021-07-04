package com.karirpad.apps.manager.database.repository;

import com.karirpad.apps.model.internal.CompanyData;
import com.karirpad.apps.model.internal.UserData;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created By reynard on 7/4/21.
 */
public interface IDatabaseRepository {
    Flowable<UserData> findUserData(String id);

    UserData findUserDataByEmail(String email);

    void insert(UserData userData);

    void update(UserData userData);

    Flowable<List<CompanyData>> getFlowableCompanyData();

    void insertCompany(CompanyData companyData);
}
