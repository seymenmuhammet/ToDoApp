package com.example.todoapp.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.todoapp.data.repo.ToDoDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ToDoSaveViewModel extends ViewModel {
    private ToDoDaoRepository trepo;

    @Inject
    public ToDoSaveViewModel(ToDoDaoRepository trepo){
        this.trepo = trepo;
    }

    public void kaydet(String todo_name){
        trepo.kaydet(todo_name);
    }
}
