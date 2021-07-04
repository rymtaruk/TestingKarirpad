package com.karirpad.apps.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.karirpad.apps.di.utils.ViewModelFactory;
import com.karirpad.apps.di.utils.ViewModelKey;
import com.karirpad.apps.ui.Login.LoginViewModel;
import com.karirpad.apps.ui.Profile.Edit.EditProfileViewModel;
import com.karirpad.apps.ui.Profile.ProfileViewModel;
import com.karirpad.apps.ui.SplashScreen.SplashScreenViewModel;
import com.karirpad.apps.ui.Vacancy.VacancyViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created By reynard on 7/3/21.
 */
@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(value = VacancyViewModel.class)
    abstract ViewModel bindVacancyViewModel(VacancyViewModel vacancyViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(value = SplashScreenViewModel.class)
    abstract ViewModel bindSplashScreenViewModel(SplashScreenViewModel splashScreenViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(value = LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(value = ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(value = EditProfileViewModel.class)
    abstract ViewModel bindEditProfileViewModel(EditProfileViewModel editProfileViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
