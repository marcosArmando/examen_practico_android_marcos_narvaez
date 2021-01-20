package com.marcos_narvaez.examen_practico_android_marcos_narvaez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class add_persona extends AppCompatActivity {

    public static final String EXTRA_NAME =
            "com.marcos_narvaez.examen_practico_android_marcos_narvaez.EXTRA_NAME";
    public static final String EXTRA_AGE =
            "com.marcos_narvaez.examen_practico_android_marcos_narvaez.EXTRA_AGE";
    public static final String EXTRA_WEIGHT =
            "com.marcos_narvaez.examen_practico_android_marcos_narvaez.EXTRA_WEIGHT";
    public static final String EXTRA_HEIGHT =
            "com.marcos_narvaez.examen_practico_android_marcos_narvaez.EXTRA_HEIGHT";
    public static final String EXTRA_ISMAN =
            "com.marcos_narvaez.examen_practico_android_marcos_narvaez.EXTRA_ISMAN";

    private Spinner spinner;
    private EditText EditTextName;
    private EditText EditTextAge;
    private EditText EditTextWeight;
    private EditText EditTextHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);

        spinner = findViewById(R.id.gender_spinner);

        EditTextName = findViewById(R.id.EditText_name);
        EditTextAge = findViewById(R.id.EditText_age);
        EditTextWeight = findViewById(R.id.EditText_weight);
        EditTextHeight = findViewById(R.id.EditText_height);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle(R.string.add_persona);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void savePerson(){
        String name = EditTextName.getText().toString();
        int age = Integer.valueOf(EditTextAge.getText().toString());
        boolean gender = Boolean.valueOf(spinner.getSelectedItem().toString());
        double weight = Double.valueOf(EditTextWeight.getText().toString());
        double height = Double.valueOf(EditTextHeight.getText().toString());

        if( name.trim().isEmpty() ||
            EditTextAge.getText().toString().trim().isEmpty() ||
            EditTextWeight.getText().toString().trim().isEmpty() ||
            EditTextHeight.getText().toString().trim().isEmpty()){

            Toast.makeText(this, R.string.needData, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_AGE, age);
        data.putExtra(EXTRA_ISMAN, gender);
        data.putExtra(EXTRA_WEIGHT, weight);
        data.putExtra(EXTRA_HEIGHT, height);

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_persona_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.save_person:
                savePerson();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}