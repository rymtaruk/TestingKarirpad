package com.karirpad.apps.ui.SplashScreen;


import com.karirpad.apps.base.BaseViewModel;
import com.karirpad.apps.manager.database.repository.IDatabaseRepository;
import com.karirpad.apps.manager.preferences.ISharePreferences;
import com.karirpad.apps.model.internal.UserData;

import javax.inject.Inject;

/**
 * Created By reynard on 7/4/21.
 */
public class SplashScreenViewModel extends BaseViewModel {

    @Inject
    public SplashScreenViewModel(ISharePreferences sharePreferences, IDatabaseRepository databaseRepository) {
        super(sharePreferences, databaseRepository);
    }

    public void setupUserDummy(){
        UserData data = new UserData();
        data.setName("Wachid Rahmad");
        data.setEmail("rahmad@gmail.com");
        data.setPhoneNumber("085298450450");
        data.setBiodata("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.");
        data.setExpectedSalary("20000000");
        data.setProfilePicture("https://avatars.githubusercontent.com/u/3233966?v=4");
        data.setJobTitle("Web Developer");
        data.setPassword("123456");

        UserData byEmail = databaseRepository.findUserDataByEmail(data.getEmail());
        if (databaseRepository.findUserDataByEmail(data.getEmail()) == null) {
            databaseRepository.insert(data);
        } else {
            sharePreferences.setUserId(String.valueOf(byEmail.getId()));
        }
        sharePreferences.setFirstRun(false);
    }
}
