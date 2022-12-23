package com.example.todoapp.di;

import android.content.Context;

import androidx.room.Room;

import com.example.todoapp.data.repo.ToDoDaoRepository;
import com.example.todoapp.room.ToDoDao;
import com.example.todoapp.room.Veritabani;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public ToDoDaoRepository provideToDoDaoRepository(ToDoDao tdao){
        return new ToDoDaoRepository(tdao);
    }
    @Provides
    @Singleton
    public ToDoDao provideToDoDao(@ApplicationContext Context context){
        Veritabani vt = Room.databaseBuilder(context,Veritabani.class,"deneme.sqlite")
                .createFromAsset("deneme.sqlite").build();
        return vt.getToDoDao();
    }
}
