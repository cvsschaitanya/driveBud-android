package com.example.drivebud.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivebud.R;
import com.example.drivebud.databinding.StatBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class StatsRecyclerViewAdapter extends RecyclerView.Adapter<StatsRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private HashMap<String, Double> stats_data;
    private ArrayList<String> stat_titles;

    public StatsRecyclerViewAdapter(Context context, HashMap<String, Double> stats_data) {
        this.context = context;
        stat_titles = new ArrayList<>();
        setStats(stats_data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        StatBinding binding = StatBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = stat_titles.get(position);
        holder.setData(title, stats_data.get(title));
        holder.itemView.setId(4815+position);
    }

    @Override
    public int getItemCount() {
        return stat_titles.size();
    }

    public void setStats(HashMap<String, Double> stats_data) {
        this.stats_data = stats_data;
        stat_titles = new ArrayList<String>(stats_data.keySet());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleView, valueView;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("TAG", "onChanged: ");
            titleView = itemView.findViewById(R.id.titleView);
            valueView = itemView.findViewById(R.id.valueView);
            this.itemView = itemView;
        }

        public void setData(String title, Double value) {
            titleView.setText(title);
            valueView.setText(value.toString());
        }
    }
}
