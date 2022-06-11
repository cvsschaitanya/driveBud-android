package com.example.drivebud.ui.stats;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivebud.R;
import com.example.drivebud.adapters.StatsRecyclerViewAdapter;
import com.example.drivebud.databinding.FragmentStatsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatsFragment extends Fragment implements View.OnClickListener {

    private FragmentStatsBinding binding;
    private List<TextView> topButtons;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatsViewModel statsViewModel = new ViewModelProvider(getActivity()).get(StatsViewModel.class);

        binding = FragmentStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.recyclerView;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getLayoutDirection());

        recyclerView.addItemDecoration(dividerItemDecoration);

        statsViewModel.loadStats();

        StatsRecyclerViewAdapter statsRecyclerViewAdapter = new StatsRecyclerViewAdapter(getContext(), statsViewModel.getStats().getValue());
        recyclerView.setAdapter(statsRecyclerViewAdapter);
        statsViewModel.getStats().observe(getViewLifecycleOwner(), new Observer<HashMap<String, Double>>() {
            @Override
            public void onChanged(HashMap<String, Double> stats_data) {
                statsRecyclerViewAdapter.setStats(stats_data);
                statsRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        setTopButtons();

        return root;
    }

    private void setTopButtons() {
        topButtons = new ArrayList<>();
        topButtons.add(binding.dayButton);
        topButtons.add(binding.weekButton);
        topButtons.add(binding.monthButton);

        for (TextView button : topButtons) {
            button.setOnClickListener(this);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        int pL = v.getPaddingLeft();
        int pT = v.getPaddingTop();
        int pR = v.getPaddingRight();
        int pB = v.getPaddingBottom();

        for (TextView button : topButtons) {
            if (v.getId() == button.getId()) {
                button.setBackground(getResources().getDrawable(R.drawable.rounded_corner));
                button.setTextColor(Color.WHITE);
            }
            else {
                button.setBackground(getResources().getDrawable(R.drawable.rounded_corner_gray));
                button.setTextColor(Color.BLACK);
            }
            button.setPadding(pL, pT, pR, pB);
        }
    }
}