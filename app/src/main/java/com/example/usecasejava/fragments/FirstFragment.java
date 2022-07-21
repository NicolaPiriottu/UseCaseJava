package com.example.usecasejava.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.usecasejava.R;
import com.example.usecasejava.api.ApiWorker;
import com.example.usecasejava.api.services.PostService;
import com.example.usecasejava.databinding.FragmentFirstBinding;
import com.example.usecasejava.models.PostModel;
import com.example.usecasejava.viewmodels.FirstFragmentViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    public static PostService service;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        getPost();

        return binding.getRoot();

    }

    private void getPost() {

        FirstFragmentViewModel viewModel = new ViewModelProvider(this).get(FirstFragmentViewModel.class);

        viewModel.liveData.observe(getViewLifecycleOwner(), postList ->{
            Log.d("niKo", "Dentro il fragment postList "+postList.get(0).getBody());
        });

        viewModel.loadPosts();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}