package com.karirpad.apps.ui.Profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.karirpad.apps.R;
import com.karirpad.apps.base.BaseActivity;
import com.karirpad.apps.databinding.ActivityProfileBinding;
import com.karirpad.apps.ui.Profile.Edit.EditProfileActivity;

public class ProfileActivity extends BaseActivity {
    ActivityProfileBinding binding;
    ProfileViewModel viewModel;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK){
            onResume();
        }
    });

    @SuppressLint({"NewApi", "UseSupportActionBar"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = getViewModelProvider().get(ProfileViewModel.class);
        viewModel.getUserFromDb();
        setupView();
        onViewCreated();
        observerData();
    }

    protected void setupView() {

    }

    protected void onViewCreated() {
        binding.ibEditProfile.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
            launcher.launch(intent);
        });
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    protected void observerData() {
        viewModel.getResponseUserData().observe(this, data -> {
            Glide.with(this)
                    .load(data.getProfilePicture())
                    .placeholder(R.drawable.bg_shimer_card_rounded)
                    .centerCrop()
                    .circleCrop()
                    .error(R.drawable.ic_baseline_person_24)
                    .into(binding.ivAvatar);

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < data.getPhoneNumber().length(); i++) {
                if (i == 12) {
                    result.append("-");
                } else if (i == 8) {
                    result.append("-");
                } else if (i == 4) {
                    result.append("-");
                }

                result.append(data.getPhoneNumber().charAt(i));
            }
            binding.tvName.setText(data.getName());
            binding.tvMobile.setText(result.toString());
            binding.tvEmail.setText(data.getEmail());
            binding.tvJobFunction.setText(data.getJobTitle());
            binding.tvTitle.setText(data.getJobTitle());
            binding.tvSalary.setText("IDR ".concat(String.format("%,.2f", Float.parseFloat(data.getExpectedSalary()))));
            binding.tvBio.setText(data.getBiodata());
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getUserFromDb();
    }
}