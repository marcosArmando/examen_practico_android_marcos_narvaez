package com.marcos_narvaez.examen_practico_android_marcos_narvaez;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonaViewModel extends AndroidViewModel {

    private PersonaRepository repository;
    private LiveData<List<Persona>> allPersonas;

    public PersonaViewModel(@NonNull Application application) {
        super(application);
        repository = new PersonaRepository(application);
        allPersonas = repository.getAllPersonas();
    }

    public void insert(Persona persona){
        repository.insert(persona);
    }

    public void update(Persona persona){
        repository.update(persona);
    }

    public void delete(Persona persona){
        repository.delete(persona);
    }

    public LiveData<List<Persona>> getAllPersonas(){
        return allPersonas;
    }
}
