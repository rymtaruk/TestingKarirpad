package com.karirpad.apps.ui.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.karirpad.apps.base.BaseViewModel;
import com.karirpad.apps.manager.database.repository.IDatabaseRepository;
import com.karirpad.apps.manager.preferences.ISharePreferences;
import com.karirpad.apps.model.internal.UserData;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created By reynard on 7/4/21.
 */
public class LoginViewModel extends BaseViewModel {
    MutableLiveData<Boolean> isValid = new MutableLiveData<>();
    MutableLiveData<Boolean> errorEmail = new MutableLiveData<>();
    MutableLiveData<Boolean> errorPassword = new MutableLiveData<>();
    MutableLiveData<String> globalMessage = new MutableLiveData<>();

    @Inject
    public LoginViewModel(ISharePreferences sharePreferences, IDatabaseRepository databaseRepository) {
        super(sharePreferences, databaseRepository);
    }

    public void validateUser(@NonNull String email, @NonNull String password) {
        if (email.isEmpty()) {
            errorEmail.setValue(true);
        } else if (password.isEmpty()) {
            errorPassword.setValue(true);
        } else {
            errorEmail.setValue(false);
            errorPassword.setValue(false);
            UserData userData = databaseRepository.findUserDataByEmail(email);
            if (userData != null) {
                if (!password.equalsIgnoreCase(userData.getPassword())) {
                    isValid.setValue(false);
                } else {
                    isValid.setValue(true);
                    sharePreferences.setUserId(String.valueOf(userData.getId()));
                    sharePreferences.setLogin(true);
                }
            } else {
                globalMessage.setValue("User tidak terdaftar");
            }
        }
    }

    public LiveData<Boolean> getIsValid() {
        return isValid;
    }

    public LiveData<Boolean> getErrorEmail() {
        return errorEmail;
    }

    public LiveData<Boolean> getErrorPassword() {
        return errorPassword;
    }

    public LiveData<String> getGlobalMessage() {
        return globalMessage;
    }
}
