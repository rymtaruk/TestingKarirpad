package com.karirpad.apps.di.module;

import com.karirpad.apps.ui.Login.LoginActivity;
import com.karirpad.apps.ui.Profile.Edit.EditProfileActivity;
import com.karirpad.apps.ui.Profile.ProfileActivity;
import com.karirpad.apps.ui.Register.RegisterActivity;
import com.karirpad.apps.ui.SplashScreen.SplashScreenActivity;
import com.karirpad.apps.ui.Vacancy.VacancyActivity;
import com.karirpad.apps.ui.Vacancy.VacancyViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created By reynard on 7/3/21.
 */
@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();

    @ContributesAndroidInjector
    abstract SplashScreenActivity splashScreenActivity();

    @ContributesAndroidInjector
    abstract RegisterActivity registerActivity();

    @ContributesAndroidInjector
    abstract VacancyActivity vacancyActivity();

    @ContributesAndroidInjector
    abstract ProfileActivity profileActivity();

    @ContributesAndroidInjector
    abstract EditProfileActivity editProfileActivity();
}
