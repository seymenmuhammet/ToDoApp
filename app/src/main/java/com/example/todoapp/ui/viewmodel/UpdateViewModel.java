package com.example.todoapp.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.todoapp.data.repo.ToDoDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UpdateViewModel extends ViewModel {
    private ToDoDaoRepository trepo;

    @Inject
    public UpdateViewModel(ToDoDaoRepository trepo){
        this.trepo = trepo;
    }

    public void guncelle(int todo_id,String todo_name){
        trepo.guncelle(todo_id,todo_name);
    }
}
