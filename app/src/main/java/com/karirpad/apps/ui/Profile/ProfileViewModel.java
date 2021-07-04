package com.karirpad.apps.ui.Profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karirpad.apps.base.BaseViewModel;
import com.karirpad.apps.manager.database.repository.IDatabaseRepository;
import com.karirpad.apps.manager.preferences.ISharePreferences;
import com.karirpad.apps.model.internal.UserData;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created By reynard on 7/4/21.
 */
public class ProfileViewModel extends BaseViewModel {
    MutableLiveData<UserData> responseUserData = new MutableLiveData<>();

    @Inject
    public ProfileViewModel(ISharePreferences sharePreferences, IDatabaseRepository databaseRepository) {
        super(sharePreferences, databaseRepository);
    }

    public void getUserFromDb() {
        setLoading(true);
        disposable.add(databaseRepository.findUserData(sharePreferences.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(e -> setLoading(true))
                .subscribe(userData -> {
                    setLoading(false);
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
}
