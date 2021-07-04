package com.karirpad.apps.ui.Vacancy;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karirpad.apps.base.BaseViewModel;
import com.karirpad.apps.manager.database.repository.IDatabaseRepository;
import com.karirpad.apps.manager.preferences.ISharePreferences;
import com.karirpad.apps.model.domain.Company;
import com.karirpad.apps.model.domain.Data;
import com.karirpad.apps.manager.api.repository.CompanyRepository;
import com.karirpad.apps.model.internal.CompanyData;
import com.karirpad.apps.model.internal.UserData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created By reynard on 7/3/21.
 */
public class VacancyViewModel extends BaseViewModel {
    CompanyRepository repository;
    MutableLiveData<List<Data>> responseData = new MutableLiveData<>();
    MutableLiveData<UserData> responseUserData = new MutableLiveData<>();
    MutableLiveData<Boolean> loadingUserData = new MutableLiveData<>();

    @Inject
    public VacancyViewModel(@NonNull ISharePreferences sharePreferences, @NonNull IDatabaseRepository databaseRepository, @NonNull CompanyRepository repository) {
        super(sharePreferences, databaseRepository);
        this.repository = repository;
        disposable = new CompositeDisposable();
    }

    public void loadData() {
        setLoading(true);
        disposable.add(repository.getCompany()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Company>() {
                    @Override
                    public void onSuccess(@NonNull Company company) {
                        setLoading(false);
                        if (company.getData() != null) {
                            setNoData(false);
                            responseData.setValue(company.getData());
                            insertCompanyData(company.getData());
                        } else {
                            setNoData(true);
                            setMessageError("Data Not Found");
                            getCompanyDataFromDb();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        setLoading(false);
                        setNoData(true);
                        setMessageError(e.getLocalizedMessage());
                        getCompanyDataFromDb();
                    }
                }));
    }

    private void insertCompanyData(List<Data> items) {
        if (items != null) {
            for (Data data : items) {
                CompanyData companyData = new CompanyData();
                companyData.setCompanyInfo(data.getCompanyInfo());
                companyData.setCompanyLocation(data.getCompanyLocation());
                companyData.setCompanyName(data.getCompanyName());
                companyData.setImage(data.getImage());
                companyData.setJobTitle(data.getJobTitle());
                companyData.setJobType(data.getJobType());
                companyData.setSubmited(data.getSubmited());
                companyData.setTimePosting(data.getTimePosting());

                databaseRepository.insertCompany(companyData);
            }
        }
    }

    public void getCompanyDataFromDb() {
        setLoading(true);
        List<Data> items = new ArrayList<>();
        disposable.add(databaseRepository.getFlowableCompanyData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(e -> {
                    setLoading(false);
                    setNoData(false);
                    setMessageError("Data not updated, please connect to your Internet");
                })
                .subscribe(companyData -> {
                    setLoading(false);
                    if (companyData != null && companyData.size() > 0) {
                        for (CompanyData c : companyData) {
                            Data data = new Data();
                            data.setCompanyInfo(c.getCompanyInfo());
                            data.setCompanyLocation(c.getCompanyLocation());
                            data.setCompanyName(c.getCompanyName());
                            data.setImage(c.getImage());
                            data.setJobTitle(c.getJobTitle());
                            data.setJobType(c.getJobType());
                            data.setSubmited(c.getSubmited());
                            data.setTimePosting(c.getTimePosting());

                            items.add(data);
                        }
                        responseData.setValue(items);
                        setMessageError("Data not updated, please connect to your Internet");
                        setNoData(false);
                    } else {
                        setNoData(true);
                        setMessageError("Data not updated, please connect to your Internet");
                    }
                }));
    }

    public void getUserFromDb() {
        loadingUserData.setValue(true);
        disposable.add(databaseRepository.findUserData(sharePreferences.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(e -> loadingUserData.setValue(true))
                .subscribe(userData -> {
                    loadingUserData.setValue(false);
                    if (userData != null) {
                        responseUserData.setValue(userData);
                    } else {
                        setMessageError("Data not updated, please connect to your Internet");
                    }
                }));
    }

    public LiveData<UserData> getResponseUserData() {
        return responseUserData;
    }

    public LiveData<Boolean> getLoadingUserData() {
        return loadingUserData;
    }

    public LiveData<List<Data>> getResponseData() {
        return responseData;
    }
}
