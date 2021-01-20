package com.marcos_narvaez.examen_practico_android_marcos_narvaez;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonaDao {

    @Insert
    void insert(Persona persona);

    @Update
    void update(Persona persona);

    @Delete
    void delete(Persona persona);

    @Query("SELECT * FROM persona_table ORDER BY name DESC")
    LiveData<List<Persona>> getAllPersonas();
}
