package com.example.todoapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todoapp.data.entity.ToDo;

@Database(entities = {ToDo.class},version = 1)
public abstract class Veritabani extends RoomDatabase{
        public abstract ToDoDao getToDoDao();

}
