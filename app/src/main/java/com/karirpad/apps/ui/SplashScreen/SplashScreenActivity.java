package com.karirpad.apps.ui.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.karirpad.apps.base.BaseActivity;
import com.karirpad.apps.databinding.ActivitySplashScreenBinding;
import com.karirpad.apps.ui.Login.LoginActivity;
import com.karirpad.apps.ui.Vacancy.VacancyActivity;

public class SplashScreenActivity extends BaseActivity {
    ActivitySplashScreenBinding binding;
    SplashScreenViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = getViewModelProvider().get(SplashScreenViewModel.class);
        setupView();
        onViewCreated();
        observerData();
    }

    protected void setupView() {
    }

    protected void onViewCreated() {
        new Handler().postDelayed(() -> {
            if (viewModel.isFirstRun()) {
                viewModel.setupUserDummy();
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            } else if (viewModel.isLogin()) {
                startActivity(new Intent(SplashScreenActivity.this, VacancyActivity.class));
            } else {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            }
            finish();
        }, 2000);
    }

    protected void observerData() {

    }
}