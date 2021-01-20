package com.marcos_narvaez.examen_practico_android_marcos_narvaez;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Persona.class}, version = 1)
public abstract class PersonaDatabase extends RoomDatabase {

    private static PersonaDatabase instance;

    public abstract PersonaDao personaDao();

    public static synchronized PersonaDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PersonaDatabase.class, "persona_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private PersonaDao personaDao;

        private PopulateDbAsyncTask(PersonaDatabase database){
            personaDao = database.personaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            personaDao.insert(new Persona("marcosArmando", 34, true, 80, 170));
            return null;
        }
    }
}
