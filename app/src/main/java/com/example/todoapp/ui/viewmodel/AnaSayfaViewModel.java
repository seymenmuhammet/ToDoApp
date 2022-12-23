package com.example.todoapp.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todoapp.data.entity.ToDo;
import com.example.todoapp.data.repo.ToDoDaoRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnaSayfaViewModel extends ViewModel {
    private ToDoDaoRepository trepo;
    public MutableLiveData<List<ToDo>> todoList = new MutableLiveData<>();

    @Inject
    public AnaSayfaViewModel(ToDoDaoRepository trepo){
        this.trepo = trepo;
        todoYukle();
        todoList = trepo.getTodoLiveDataListesi();
    }

    public void ara(String aramaKelimesi){
        trepo.ara(aramaKelimesi);
    }

    public void sil(int todo_id){
        trepo.sil(todo_id);
    }

    public void todoYukle(){
        trepo.todoYukle();
    }
}
