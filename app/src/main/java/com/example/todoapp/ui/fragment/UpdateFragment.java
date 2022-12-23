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
import com.example.todoapp.data.entity.ToDo;
import com.example.todoapp.databinding.FragmentUpdateBinding;
import com.example.todoapp.ui.viewmodel.ToDoSaveViewModel;
import com.example.todoapp.ui.viewmodel.UpdateViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateFragment extends Fragment {
    private FragmentUpdateBinding binding;
    private UpdateViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_update, container, false);
        binding.setToDoUpdateToolbarBaslik("Yapılacakları Güncelle");

        UpdateFragmentArgs bundle = UpdateFragmentArgs.fromBundle(getArguments());
        ToDo gelenTodo = bundle.getTodo();
        binding.setTodoNesnesi(gelenTodo);

        binding.setUpdateFragment(this);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UpdateViewModel.class);
    }

    public void guncelle(int todo_id,String todo_name){
        viewModel.guncelle(todo_id,todo_name);
    }
}