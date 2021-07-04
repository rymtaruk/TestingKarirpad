package com.karirpad.apps.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.karirpad.apps.manager.database.repository.IDatabaseRepository;
import com.karirpad.apps.manager.preferences.ISharePreferences;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created By reynard on 7/4/21.
 */
public class BaseViewModel extends ViewModel {
    private MutableLiveData<String> messageError = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Boolean> noData = new MutableLiveData<>();
    protected final ISharePreferences sharePreferences;
    protected final IDatabaseRepository databaseRepository;
    protected CompositeDisposable disposable;

    public BaseViewModel(ISharePreferences sharePreferences, IDatabaseRepository databaseRepository) {
        this.sharePreferences = sharePreferences;
        this.databaseRepository = databaseRepository;
        this.disposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        disposable.dispose();
        disposable.clear();
        super.onCleared();
    }

    public boolean isFirstRun() {
        return sharePreferences.isFirstRun();
    }

    public boolean isLogin() {
        return sharePreferences.isLogin();
    }

    public String getUserId() {
        return sharePreferences.getUserId();
    }

    public String getUsername() {
        return sharePreferences.getUserName();
    }

    public synchronized void setMessageError(String messageError) {
        this.messageError.setValue(messageError);
    }

    public void setLoading(boolean loading) {
        this.loading.setValue(loading);
    }

    public void setNoData(boolean noData) {
        this.noData.setValue(noData);
    }

    public LiveData<String> getMessageError() {
        return messageError;
    }

    public LiveData<Boolean> isLoading() {
        return loading;
    }

    public LiveData<Boolean> isNoData() {
        return noData;
    }
}
