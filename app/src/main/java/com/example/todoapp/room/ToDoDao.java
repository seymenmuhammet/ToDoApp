package com.example.todoapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.data.entity.ToDo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ToDoDao {
    @Query("SELECT * FROM todo")
    Single<List<ToDo>> toDosYukle();

    @Query("SELECT * FROM todo WHERE todo_name like '%' || :aramaKelimesi || '%'")
    Single<List<ToDo>> ara(String aramaKelimesi);

    @Delete
    Completable sil(ToDo toDo);

    @Insert
    Completable kaydet(ToDo toDo);

    @Update
    Completable guncelle(ToDo toDo);



}
