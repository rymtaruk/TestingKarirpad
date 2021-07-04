package com.karirpad.apps.ui.Profile.Edit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karirpad.apps.base.BaseViewModel;
import com.karirpad.apps.manager.database.repository.IDatabaseRepository;
import com.karirpad.apps.manager.preferences.ISharePreferences;
import com.karirpad.apps.model.internal.UserData;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created By reynard on 7/4/21.
 */
public class EditProfileViewModel extends BaseViewModel {
    MutableLiveData<UserData> responseUserData = new MutableLiveData<>();
    MutableLiveData<Boolean> isValid = new MutableLiveData<>();
    MutableLiveData<String> picture = new MutableLiveData<>();
    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<String> email = new MutableLiveData<>();
    MutableLiveData<String> mobile = new MutableLiveData<>();
    MutableLiveData<String> jobTitle = new MutableLiveData<>();
    MutableLiveData<String> expectedSalary = new MutableLiveData<>();
    MutableLiveData<String> bio = new MutableLiveData<>();

    @Inject
    public EditProfileViewModel(ISharePreferences sharePreferences, IDatabaseRepository databaseRepository) {
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

    public void saveToDb() {
        if (Objects.requireNonNull(name.getValue()).isEmpty()) {
            setMessageError("Please Input Name");
        } else if (Objects.requireNonNull(email.getValue()).isEmpty()) {
            setMessageError("Please Input Email");
        } else if (Objects.requireNonNull(mobile.getValue()).isEmpty()) {
            setMessageError("Please Input Mobile");
        } else if (Objects.requireNonNull(jobTitle.getValue()).isEmpty()) {
            setMessageError("Please Input Job Function");
        } else if (Objects.requireNonNull(expectedSalary.getValue()).isEmpty()) {
            setMessageError("Please Input Expected Salary");
        } else if (Objects.requireNonNull(bio.getValue()).isEmpty()) {
            setMessageError("Please Input Bio");
        } else {
            UserData userData = new UserData();
            userData.setId(Objects.requireNonNull(responseUserData.getValue()).getId());
            userData.setName(name.getValue());
            userData.setEmail(email.getValue());
            userData.setPhoneNumber(mobile.getValue());
            userData.setProfilePicture(picture.getValue());
            userData.setJobTitle(jobTitle.getValue());
            userData.setExpectedSalary(expectedSalary.getValue());
            userData.setBiodata(bio.getValue());
            databaseRepository.update(userData);
            isValid.setValue(true);
        }
    }

    public LiveData<Boolean> getIsValid() {
        return isValid;
    }

    public LiveData<UserData> getResponseUserData() {
        return responseUserData;
    }
}
