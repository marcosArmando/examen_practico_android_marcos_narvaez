package com.marcos_narvaez.examen_practico_android_marcos_narvaez;

public class Cliente {

    private String Nombre;
    private String Apellidos;
    private String Nombre_Usario;
    private String Correo_Electronico;
    private String Contraseña;

    public Cliente(String nombre, String apellidos, String nombre_Usario, String correo_Electronico, String contraseña) {
        Nombre = nombre;
        Apellidos = apellidos;
        Nombre_Usario = nombre_Usario;
        Correo_Electronico = correo_Electronico;
        Contraseña = contraseña;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getNombre_Usario() {
        return Nombre_Usario;
    }

    public String getCorreo_Electronico() {
        return Correo_Electronico;
    }

    public String getContraseña() {
        return Contraseña;
    }
}
