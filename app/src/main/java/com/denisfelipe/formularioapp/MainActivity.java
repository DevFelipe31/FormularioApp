package com.denisfelipe.formularioapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;
import java.util.Objects;


@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    //Global Variables*
    TextInputEditText tietName, tietBirthDate, tietPhone, tietEmail, tietDescription;
    Button btnSave;
    final Calendar c = Calendar.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Text Inputs searched by id
        tietName = (TextInputEditText) findViewById(R.id.tietName);
        tietBirthDate = (TextInputEditText) findViewById(R.id.tietBirthDate);
        tietPhone = (TextInputEditText) findViewById(R.id.tietPhone);
        tietEmail = (TextInputEditText) findViewById(R.id.tietEmail);
        tietDescription = (TextInputEditText) findViewById(R.id.tietDescription);

        // Button searched by id
        btnSave = (Button) findViewById(R.id.btnSave);


        //Set the TextView
        Intent chechkIntent = getIntent();

        if (chechkIntent.getExtras() != null) {
            Bundle parametersConfrimData = getIntent().getExtras();

            tietName.setText(parametersConfrimData.getString("name"));
            tietBirthDate.setText(parametersConfrimData.getString("birthDate"));
            tietPhone.setText(parametersConfrimData.getString("phone"));
            tietEmail.setText(parametersConfrimData.getString("email"));
            tietDescription.setText(parametersConfrimData.getString("description"));
        }

        //OnDate Listener
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        // OnClick Listener Date Text Input
        tietBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(), date, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //OnClick Listener Button Save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Exports the data of this activity in variables
                String name = tietName.getText().toString();
                String birthDate = tietBirthDate.getText().toString();
                String phone = tietPhone.getText().toString();
                String email = tietEmail.getText().toString();
                String description = tietDescription.getText().toString();

                //Create the intent to go a ConfirmData
                Intent intentToConfirmData = new Intent(MainActivity.this, ConfirmData.class);

                //Exports the data in put extras
                intentToConfirmData.putExtra("name", name);
                intentToConfirmData.putExtra("birthDate", birthDate);
                intentToConfirmData.putExtra("phone", phone);
                intentToConfirmData.putExtra("email", email);
                intentToConfirmData.putExtra("description", description);

                intentToConfirmData.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //Start the New Activity
                startActivity(intentToConfirmData);
            }
        });

    }

    //Update Text Inputs Date
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ROOT);
        tietBirthDate.setText(sdf.format(c.getTime()));
    }


}