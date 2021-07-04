package com.karirpad.apps.ui.Register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;

import com.karirpad.apps.base.BaseActivity;
import com.karirpad.apps.databinding.ActivityRegisterBinding;
import com.karirpad.apps.ui.Login.LoginViewModel;
import com.karirpad.apps.ui.Vacancy.VacancyActivity;

import java.util.Objects;

public class RegisterActivity extends BaseActivity {
    ActivityRegisterBinding binding;
    LoginViewModel viewModel;

    int REQUIRED_LENGTH = 8;

    int currentScore = 0;
    boolean sawUpper = false;
    boolean sawLower = false;
    boolean sawDigit = false;
    boolean sawSpecial = false;
    boolean isShowPassword = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = getViewModelProvider().get(LoginViewModel.class);
        setupView();
        onViewCreated();
        observerData();
    }

    protected void setupView() {

    }

    protected void onViewCreated() {
        binding.btnRegister.setOnClickListener(view -> viewModel.validateUser("rahmad@gmail.com", "123456"));
        binding.tietPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int score = passwordValidation(charSequence.toString());
                scoringPassword(score);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        binding.tietPassword.setOnKeyListener((view, i, keyEvent) -> {
            if (binding.tietPassword.getText() != null) {
                if (i == KeyEvent.KEYCODE_DEL) {
                    int score = passwordValidation(Objects.requireNonNull(binding.tietPassword.getText()).toString());
                    sawUpper = false;
                    sawLower = false;
                    sawDigit = false;
                    sawSpecial = false;
                    scoringPassword(score);
                }
            }
            return false;
        });

        binding.tilPassword.setEndIconOnClickListener(view -> {
            if (isShowPassword){
                binding.tietPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                isShowPassword = false;
            } else {
                binding.tietPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                isShowPassword = true;
            }
        });
    }

    protected void observerData() {
        viewModel.getIsValid().observe(this, valid -> {
            if (valid) {
                startActivity(new Intent(this, VacancyActivity.class));
                finish();
            } else {
                binding.tilEmail.setError("Email not valid");
                binding.tilPassword.setError("Password not valid");
            }
        });
    }

    private int passwordValidation(@NonNull String password) {
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!sawSpecial && !Character.isLetterOrDigit(c)) {
                sawSpecial = true;
                currentScore += 1;
            } else if (!sawDigit && Character.isDigit(c)) {
                sawDigit = true;
                currentScore += 1;
            } else {
                if (!sawUpper && !Character.isUpperCase(c)) {
                    sawUpper = true;
                    currentScore += 1;
                } else if (!sawLower && !Character.isLowerCase(c)) {
                    sawLower = true;
                    currentScore += 1;
                }
            }
        }

        if (password.length() > REQUIRED_LENGTH) {
            if (!sawSpecial || !sawLower || !sawUpper || !sawDigit) {
                currentScore = 3;
            } else {
                currentScore = 5;
            }
        } else if (sawSpecial || sawDigit) {
            sawDigit = false;
            sawSpecial = false;
            currentScore = 3;
        } else if (sawLower || sawUpper) {
            sawLower = false;
            sawUpper = false;
            currentScore = 2;
        } else {
            currentScore = 1;
        }

        if (password.isEmpty()) {
            currentScore = 0;
            sawUpper = false;
            sawLower = false;
            sawDigit = false;
            sawSpecial = false;
        }

        return currentScore;
    }

    @SuppressLint("SetTextI18n")
    private void scoringPassword(int score){
        switch (score) {
            case 0:
                binding.tvValidation.setText("None");
                binding.pbOne.setProgress(0);
                binding.pbTwo.setProgress(0);
                binding.pbThree.setProgress(0);
                binding.pbFour.setProgress(0);
                binding.pbFive.setProgress(0);
                break;
            case 1:
                binding.tvValidation.setText("Very Week");
                binding.pbOne.setProgress(100);
                binding.pbTwo.setProgress(0);
                binding.pbThree.setProgress(0);
                binding.pbFour.setProgress(0);
                binding.pbFive.setProgress(0);
                break;
            case 2:
                binding.tvValidation.setText("Weak");
                binding.pbOne.setProgress(100);
                binding.pbTwo.setProgress(100);
                binding.pbThree.setProgress(0);
                binding.pbFour.setProgress(0);
                binding.pbFive.setProgress(0);
                break;
            case 3:
                binding.tvValidation.setText("Medium");
                binding.pbOne.setProgress(100);
                binding.pbTwo.setProgress(100);
                binding.pbThree.setProgress(100);
                binding.pbFour.setProgress(0);
                binding.pbFive.setProgress(0);
                break;
            case 4:
                binding.tvValidation.setText("Strong");
                binding.pbOne.setProgress(100);
                binding.pbTwo.setProgress(100);
                binding.pbThree.setProgress(100);
                binding.pbFour.setProgress(100);
                binding.pbFive.setProgress(0);
                break;
            case 5:
                binding.tvValidation.setText("Very Strong");
                binding.pbOne.setProgress(100);
                binding.pbTwo.setProgress(100);
                binding.pbThree.setProgress(100);
                binding.pbFour.setProgress(100);
                binding.pbFive.setProgress(100);
                break;
        }
    }
}