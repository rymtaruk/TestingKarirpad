package com.karirpad.apps.ui.Vacancy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.karirpad.apps.R;
import com.karirpad.apps.base.BaseActivity;
import com.karirpad.apps.databinding.ActivityVacancyBinding;
import com.karirpad.apps.ui.Profile.ProfileActivity;

public class VacancyActivity extends BaseActivity {
    ActivityVacancyBinding binding;
    VacancyAdapter adapter;
    VacancyViewModel viewModel;
    boolean isShowMore = false;
    boolean connected = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVacancyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = getViewModelProvider().get(VacancyViewModel.class);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        viewModel.getUserFromDb();
        if (checkConnection()) {
            viewModel.loadData();
        } else {
            viewModel.getCompanyDataFromDb();
        }

        setupView();
        onViewCreated();
        observerData();
    }

    protected void setupView() {
        binding.rvVacancy.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvVacancy.setItemAnimator(new DefaultItemAnimator());
        binding.rvVacancy.setAdapter(getAdapter());
    }

    protected void onViewCreated() {
        binding.tvSeeMore.setOnClickListener(view -> {
            if (isShowMore) {
                binding.llMore.setVisibility(View.GONE);
                isShowMore = false;
            } else {
                binding.llMore.setVisibility(View.VISIBLE);
                binding.llSeeMore.setVisibility(View.GONE);
                isShowMore = true;
            }
        });
        binding.rvVacancy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                binding.llMore.setVisibility(View.GONE);
                binding.llSeeMore.setVisibility(View.VISIBLE);
                isShowMore = false;
            }
        });
        binding.ibAvatar.setOnClickListener(view -> startActivity(new Intent(view.getContext(), ProfileActivity.class)));

        binding.btnRefresh.setOnClickListener(view -> {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
            if (checkConnection()) {
                viewModel.loadData();
            } else {
                Toast.makeText(this, "Please connect to your internet !", Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    protected void observerData() {
        viewModel.getResponseData().observe(this, data -> {
            getAdapter().setItems(data);
            getAdapter().notifyDataSetChanged();
            binding.tvCountList.setText("Semua Lowongan (" + getAdapter().getItemCount() + ")");
            binding.btnRefresh.setVisibility(View.GONE);
        });

        viewModel.isLoading().observe(this, status -> {
            if (status) {
                binding.btnRefresh.setVisibility(View.GONE);
                binding.rvVacancy.setVisibility(View.GONE);
                binding.sflLoading.setVisibility(View.VISIBLE);
            } else {
                binding.rvVacancy.setVisibility(View.VISIBLE);
                binding.sflLoading.setVisibility(View.GONE);
            }
        });

        viewModel.isNoData().observe(this, status -> {
            if (status) {
                binding.btnRefresh.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getMessageError().observe(this, message -> {
            View toast = Toast.makeText(this, message, Toast.LENGTH_LONG).getView();
            if (!toast.isShown()){
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getResponseUserData().observe(this, data ->
                Glide.with(this)
                .load(data.getProfilePicture())
                .placeholder(R.drawable.bg_shimer_card_rounded)
                .centerCrop()
                .circleCrop()
                .error(R.drawable.ic_baseline_person_24)
                .into(binding.ibAvatar));
    }

    private VacancyAdapter getAdapter() {
        if (adapter == null) {
            adapter = new VacancyAdapter();
        }
        return adapter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkConnection()) {
            if (getAdapter().getItemCount() <= 0){
                viewModel.loadData();
            }
        } else {
            viewModel.getCompanyDataFromDb();
        }
    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
    }
}