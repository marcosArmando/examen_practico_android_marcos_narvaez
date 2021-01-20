package com.marcos_narvaez.examen_practico_android_marcos_narvaez;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_PERSONA_REQUEST = 1;

    private PersonaViewModel personaViewModel;
    private FloatingActionButton floatingActionButton;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private TextView wsResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.fabAdd);
        wsResult = findViewById(R.id.wsResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://187.188.122.85:8091/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_persona.class);
                startActivityForResult(intent, ADD_PERSONA_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rvPersonas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        PersonaAdapter personaAdapter = new PersonaAdapter();
        recyclerView.setAdapter(personaAdapter);

        personaViewModel = ViewModelProviders.of(this).get(PersonaViewModel.class);
        personaViewModel.getAllPersonas().observe(this, new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> personas) {
                personaAdapter.setPersonas(personas);
            }
        });

        getClientes();
        createCliente();
        updatePersona(59);
    }

    private void createCliente(){
        Call<Cliente> call = jsonPlaceHolderApi.createCliente("Marcos Armando", "Narvaez Aldana", "mNarvaez", "mnarvaez@email.com", "secret");
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {

                Cliente tempCliente = response.body();

                String titulo = "Éxito en el POST!";

                String content = "";

                if(!response.isSuccessful()){
                    titulo = "Falló el POST! Error: "+response.code();
                } else {
                    content += "Code: "+response.code()+"\n";
                    content += "Nombre: "+tempCliente.getNombre()+"\n";
                    content += "Apellidos: "+tempCliente.getApellidos()+"\n";
                    content += "Nombre de usuario: "+tempCliente.getNombre_Usario()+"\n";
                    content += "Correo electrónico: "+tempCliente.getCorreo_Electronico()+"\n";
                    content += "Contraseña: "+tempCliente.getContraseña()+"\n\n";
                }

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(titulo)
                        .setMessage(content)
                        .setCancelable(true)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getClientes(){

        Call<List<Cliente>> call = jsonPlaceHolderApi.getClientes();
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if(!response.isSuccessful()){
                    wsResult.setText("code: "+response.code());
                    return;
                }

                List<Cliente> clientes = response.body();

                for(Cliente cliente:clientes){

                    String content = "";
                    content += "Nombre: "+cliente.getNombre()+"\n";
                    content += "Apellidos: "+cliente.getApellidos()+"\n";
                    content += "Nombre de usuario: "+cliente.getNombre_Usario()+"\n";
                    content += "Correo electrónico: "+cliente.getCorreo_Electronico()+"\n";
                    content += "Contraseña: "+cliente.getContraseña()+"\n\n";

                    wsResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                wsResult.setText(t.getMessage());
            }
        });

    }

    private void updatePersona(int id){

        Persona persona = new Persona("Marcos", 34, true, 70, 170);

        Call<Persona> call = jsonPlaceHolderApi.putPersona(id, persona);
        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {

                Persona persona1 = response.body();
                String titulo = "Éxito en el PUT!";

                String content = "";

                if(!response.isSuccessful()){
                    titulo = "Falló el PUT!: Error: "+response.code();
                } else {
                    content += "Code: "+response.code()+"\n";
                    content += "Nombre: "+persona1.getName()+"\n";
                    content += "Edad: "+persona1.getAge()+"\n";
                    content += "Es hombre: "+persona1.isGender()+"\n";
                    content += "peso en kg: "+persona1.getWeight()+"\n";
                    content += "estatura en cm: "+persona1.getHeight()+"\n\n";
                }

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(titulo)
                        .setMessage(content)
                        .setCancelable(true)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_PERSONA_REQUEST && resultCode == RESULT_OK){

            String name = data.getStringExtra(add_persona.EXTRA_NAME);
            int age = data.getIntExtra(add_persona.EXTRA_AGE, 0);
            boolean gender = data.getBooleanExtra(add_persona.EXTRA_ISMAN, true);
            double weight = data.getDoubleExtra(add_persona.EXTRA_WEIGHT, 0.0);
            double height = data.getDoubleExtra(add_persona.EXTRA_HEIGHT, 0.0);

            Persona persona = new Persona(name, age, gender, weight, height);
            personaViewModel.insert(persona);

            Toast.makeText(this, "IMC status: " + persona.calculateIMC(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Es mayor de edad: " +persona.isOlderEnough(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, persona.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.personaNotSaved, Toast.LENGTH_SHORT).show();
        }
    }
}