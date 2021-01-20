package com.marcos_narvaez.examen_practico_android_marcos_narvaez;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaHolder>{

    private List<Persona> personas = new ArrayList<>();

    @NonNull
    @Override
    public PersonaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.persona_item, parent, false);
        return new PersonaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaHolder holder, int position) {
        Persona tempPersona = personas.get(position);
        String gender;
        holder.textViewName.setText(tempPersona.getName());
        holder.textViewAge.setText(String.valueOf(tempPersona.getAge()));
        holder.textViewNss.setText(tempPersona.getNSS());
        holder.textViewWeight.setText(String.valueOf(tempPersona.getWeight()));
        holder.textViewHeight.setText(String.valueOf(tempPersona.getHeight()));

        gender = tempPersona.isGender() ? "Masculino" : "Femenino";
        holder.textViewGender.setText(gender);

    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public void setPersonas(List<Persona> personas){
        this.personas = personas;
        notifyDataSetChanged();
    }

    class PersonaHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewAge;
        private TextView textViewNss;
        private TextView textViewGender;
        private TextView textViewWeight;
        private TextView textViewHeight;

        public PersonaHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewAge = itemView.findViewById(R.id.text_view_age);
            textViewNss = itemView.findViewById(R.id.text_view_nss);
            textViewGender = itemView.findViewById(R.id.text_view_gender);
            textViewWeight = itemView.findViewById(R.id.text_view_weight);
            textViewHeight = itemView.findViewById(R.id.text_view_height);
        }
    }

}
