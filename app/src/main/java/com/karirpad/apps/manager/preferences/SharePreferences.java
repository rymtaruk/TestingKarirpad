package com.karirpad.apps.manager.preferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created By reynard on 7/3/21.
 */
public class SharePreferences implements ISharePreferences{
    private static final String PREF_IS_LOGIN = "PREF_IS_LOGIN";
    private static final String PREF_IS_FIRST_RUN = "PREF_IS_FIRST_RUN";
    private static final String PREF_USER_ID = "PREF_USER_ID";
    private static final String PREF_USER_NAME = "PREF_USER_NAME";

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    public SharePreferences() {
    }

    @Override
    public boolean isFirstRun() {
        return sharedPreferences.getBoolean(PREF_IS_FIRST_RUN, true);
    }

    @Override
    public void setFirstRun(boolean status) {
        sharedPreferences.edit().putBoolean(PREF_IS_FIRST_RUN, status).apply();
    }

    @Override
    public boolean isLogin() {
        return sharedPreferences.getBoolean(PREF_IS_LOGIN, false);
    }

    @Override
    public void setLogin(boolean status) {
        sharedPreferences.edit().putBoolean(PREF_IS_LOGIN, status).apply();
    }

    @Override
    public String getUserId() {
        return sharedPreferences.getString(PREF_USER_ID, "PREF_USER_ID");
    }

    @Override
    public void setUserId(String userId) {
        sharedPreferences.edit().putString(PREF_USER_ID, userId).apply();
    }

    @Override
    public String getUserName() {
        return sharedPreferences.getString(PREF_USER_NAME, "PREF_USER_NAME");
    }

    @Override
    public void setUserName(String userName) {
        sharedPreferences.edit().putString(PREF_USER_NAME, userName).apply();
    }
}
