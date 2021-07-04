package com.karirpad.apps.manager.preferences;

/**
 * Created By reynard on 7/3/21.
 */
public interface ISharePreferences {
    boolean isFirstRun ();

    void setFirstRun(boolean status);

    boolean isLogin ();

    void setLogin(boolean status);

    String getUserId();

    void setUserId(String userId);

    String getUserName();

    void setUserName(String userName);
}
