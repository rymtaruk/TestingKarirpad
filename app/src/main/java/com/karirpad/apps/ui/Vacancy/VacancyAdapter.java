package com.karirpad.apps.ui.Vacancy;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.karirpad.apps.R;
import com.karirpad.apps.databinding.AdapterVacancyBinding;
import com.karirpad.apps.model.domain.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By reynard on 7/3/21.
 */
public class VacancyAdapter extends RecyclerView.Adapter<VacancyAdapter.ViewHolder> {
    List<Data> items;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(AdapterVacancyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Data data = getItems().get(position);
        Glide.with(holder.binding.getRoot())
                .load(data.getImage())
                .placeholder(R.drawable.bg_dsb_shimer_rounded)
                .centerCrop()
                .circleCrop()
                .error(R.drawable.ic_baseline_person_24)
                .into(holder.binding.ivAvatar);

        holder.binding.tvJobName.setText(data.getJobTitle());
        holder.binding.tvCompanyName.setText(data.getCompanyName());
        holder.binding.tvCompanyInfo.setText(data.getCompanyInfo());
        holder.binding.tvLocation.setText(data.getCompanyLocation());
        holder.binding.tvPinned.setText(data.getSubmited());
        holder.binding.tvTime.setText(data.getTimePosting());
        holder.binding.tvJobType.setText(data.getJobType());

    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterVacancyBinding binding;

        public ViewHolder(@NonNull AdapterVacancyBinding view) {
            super(view.getRoot());
            binding = view;
        }
    }

    public List<Data> getItems() {
        if (items == null){
            items = new ArrayList<>(0);
        }
        return items;
    }

    public void setItems(List<Data> items) {
        this.items = items;
    }
}
