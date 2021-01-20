package com.marcos_narvaez.examen_practico_android_marcos_narvaez;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("NutriNET/Cliente")
    Call<List<Cliente>> getClientes();

    @FormUrlEncoded
    @POST("NutriNET/Cliente")
    Call<Cliente> createCliente(
            @Field("Nombre") String Nombre,
            @Field("Apellidos") String Apellidos,
            @Field("Nombre_Usuario") String Nombre_Usuario,
            @Field("Correo_Electronico") String Correo_Electronico,
            @Field("Contraseña") String Contraseña
    );

    @PUT("/NutriNET/Cliente/{id}")
    Call<Persona> putPersona(@Path("id") int id, @Body Persona persona);

}
