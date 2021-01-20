package com.marcos_narvaez.examen_practico_android_marcos_narvaez;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonaRepository {

    private PersonaDao personaDao;
    private LiveData<List<Persona>> allPersonas;

    public PersonaRepository(Application application){

        PersonaDatabase personaDatabase = PersonaDatabase.getInstance(application);
        personaDao = personaDatabase.personaDao();
        allPersonas = personaDao.getAllPersonas();
    }

    public void insert(Persona persona){
        new InsertPersonaAsyncTask(personaDao).execute(persona);
    }

    public void update(Persona persona){
        new UpdatePersonaAsyncTask(personaDao).execute(persona);
    }

    public void delete(Persona persona){
        new DeletePersonaAsyncTask(personaDao).execute(persona);
    }

    public LiveData<List<Persona>> getAllPersonas(){
        return allPersonas;
    }

    private static class InsertPersonaAsyncTask extends AsyncTask<Persona, Void, Void>{

        private PersonaDao personaDao;

        private InsertPersonaAsyncTask(PersonaDao personaDao){
            this.personaDao = personaDao;
        }

        @Override
        protected Void doInBackground(Persona... personas) {
            personaDao.insert(personas[0]);
            return null;
        }
    }

    private static class DeletePersonaAsyncTask extends AsyncTask<Persona, Void, Void>{

        private PersonaDao personaDao;

        private DeletePersonaAsyncTask(PersonaDao personaDao){
            this.personaDao = personaDao;
        }

        @Override
        protected Void doInBackground(Persona... personas) {
            personaDao.delete(personas[0]);
            return null;
        }
    }

    private static class UpdatePersonaAsyncTask extends AsyncTask<Persona, Void, Void>{

        private PersonaDao personaDao;

        private UpdatePersonaAsyncTask(PersonaDao personaDao){
            this.personaDao = personaDao;
        }

        @Override
        protected Void doInBackground(Persona... personas) {
            personaDao.update(personas[0]);
            return null;
        }
    }

}
