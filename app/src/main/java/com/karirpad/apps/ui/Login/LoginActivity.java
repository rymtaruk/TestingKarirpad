package com.karirpad.apps.ui.Login;

import androidx.annotation.Nullable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.karirpad.apps.R;
import com.karirpad.apps.base.BaseActivity;
import com.karirpad.apps.databinding.ActivityLoginBinding;
import com.karirpad.apps.ui.Register.RegisterActivity;
import com.karirpad.apps.ui.SplashScreen.SplashScreenActivity;
import com.karirpad.apps.ui.Vacancy.VacancyActivity;

import java.util.Objects;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = getViewModelProvider().get(LoginViewModel.class);
        setupView();
        onViewCreated();
        observerData();
    }

    private void setupView() {

    }

    private void onViewCreated() {
        binding.tvRegister.setOnClickListener(view -> startActivity(new Intent(view.getContext(), RegisterActivity.class)));

        binding.btnLogin.setOnClickListener(view -> viewModel.validateUser(Objects.requireNonNull(binding.tietEmail.getText()).toString(), Objects.requireNonNull(binding.tietPassword.getText()).toString()));
    }

    @SuppressLint("ResourceAsColor")
    private void observerData() {
        viewModel.getGlobalMessage().observe(this, message ->
                Toast.makeText(this, message, Toast.LENGTH_LONG).show());

        viewModel.getIsValid().observe(this, valid -> {
            if (valid) {
                startActivity(new Intent(this, VacancyActivity.class));
                finish();
            } else {
                binding.tilEmail.setError("Email not valid");
                binding.tilPassword.setError("Password not valid");
            }
        });

        viewModel.getErrorEmail().observe(this, error -> {
            if (error) {
                binding.tilEmail.setError("Please Input Email");
            } else {
                binding.tilEmail.setError(null);
            }
        });

        viewModel.getErrorPassword().observe(this, error -> {
            if (error) {
                binding.tilPassword.setError("Please Input Password");
            } else {
                binding.tilPassword.setError(null);
            }
        });
    }
}