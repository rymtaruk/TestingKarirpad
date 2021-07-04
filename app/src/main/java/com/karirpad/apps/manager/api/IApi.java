package com.karirpad.apps.manager.api;

import com.karirpad.apps.model.domain.Company;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created By reynard on 7/3/21.
 */
public interface IApi {
    @GET("/v3/5e218e35-b929-4f99-bfee-8977c22137ac")
    Single<Company> getUsers();
}
