package com.example.todoapp.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.todoapp.data.entity.ToDo;
import com.example.todoapp.room.ToDoDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ToDoDaoRepository {
    private MutableLiveData<List<ToDo>> todoLiveDataListesi;
    private ToDoDao tdao;

    public ToDoDaoRepository(ToDoDao tdao) {
        this.tdao = tdao;
        todoLiveDataListesi = new MutableLiveData<>();
    }

    public MutableLiveData<List<ToDo>> getTodoLiveDataListesi(){
        return todoLiveDataListesi;
    }

    public void todoYukle(){
        tdao.toDosYukle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ToDo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<ToDo> toDos) {
                        todoLiveDataListesi.setValue(toDos);
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void kaydet(String todo_name){
        ToDo yeniYapilacak = new ToDo(0,todo_name);
        tdao.kaydet(yeniYapilacak)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void guncelle(int todo_id,String todo_name){
        ToDo guncellenen = new ToDo(todo_id,todo_name);
        tdao.guncelle(guncellenen)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void ara(String aramaKelimesi){
        tdao.ara(aramaKelimesi)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ToDo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<ToDo> toDos) {
                        todoLiveDataListesi.setValue(toDos);
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void sil(int todo_id){
        ToDo silinenYapilacak = new ToDo(todo_id,"");
        tdao.sil(silinenYapilacak)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {
                        todoYukle();
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

}
