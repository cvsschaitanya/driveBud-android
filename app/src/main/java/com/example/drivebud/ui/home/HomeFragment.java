package com.example.drivebud.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.drivebud.R;
import com.example.drivebud.data.services.HomeSyncService;
import com.example.drivebud.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements AquireListener {

    private @NonNull
    FragmentHomeBinding binding;
    private int ON_COLOR, OFF_COLOR;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setViewModel(homeViewModel);
        homeViewModel.aquireListener = this;

        View root = binding.getRoot();

        setupBluetooth();
        homeViewModel.aquireData();

        return root;
    }

    private void setupBluetooth() {
        ON_COLOR = getResources().getColor(R.color.textThemeColor);
        OFF_COLOR = getResources().getColor(R.color.button_background_color_inactive);


        binding.bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", binding.bluetoothButton.getContentDescription().toString());
                if (binding.bluetoothButton.getContentDescription().equals("Bluetooth OFF")) {
                    binding.bluetoothButton.setColorFilter(ON_COLOR);
                    binding.bluetoothButton.setContentDescription("Bluetooth ON");
                } else {
                    binding.bluetoothButton.setColorFilter(OFF_COLOR);
                    binding.bluetoothButton.setContentDescription("Bluetooth OFF");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().startService(new Intent(getActivity(),HomeSyncService.class));

    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().stopService(new Intent(getActivity(),HomeSyncService.class));
    }

    @Override
    public void onStarted() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.rangeValue.setText("--");
    }

    @Override
    public void onSuccess(LiveData<String> rangeLiveData) {
        rangeLiveData.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.progressBar.setVisibility(View.INVISIBLE);
                binding.rangeValue.setText(s);
            }
        });
    }

    @Override
    public void onFailure() {

    }
}

