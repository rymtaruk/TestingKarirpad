package com.karirpad.apps.manager.api.repository;

import com.karirpad.apps.manager.api.IApi;
import com.karirpad.apps.model.domain.Company;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created By reynard on 7/3/21.
 */
public class CompanyRepository {
    private final IApi api;

    @Inject
    public CompanyRepository(IApi api) {
        this.api = api;
    }

    public Single<Company> getCompany(){
        return api.getUsers();
    }


}
