package com.example.todoapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.R;
import com.example.todoapp.databinding.FragmentToDoSaveBinding;
import com.example.todoapp.ui.viewmodel.ToDoSaveViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ToDoSaveFragment extends Fragment {
    private FragmentToDoSaveBinding binding;
    private ToDoSaveViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_to_do_save, container, false);
        binding.setToDoSaveToolbarBaslik("Yapılacakları Kaydet");
        binding.setToDoSaveFragment(this);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ToDoSaveViewModel.class);
    }

    public void kaydet(String todo_name){
        viewModel.kaydet(todo_name);
    }
}