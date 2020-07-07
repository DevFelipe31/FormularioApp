package com.denisfelipe.formularioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmData extends AppCompatActivity {

    TextView tvName, tvBirthDate, tvPhone, tvEmail, tvDescription;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_data);

        //TextViews searched by id
        tvName = (TextView) findViewById(R.id.tvName);
        tvBirthDate = (TextView) findViewById(R.id.tvBirthDate);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvDescription = (TextView) findViewById(R.id.tvDescription);

        // Button searched by id
        btnEdit = (Button) findViewById(R.id.btnEdit);

        //Set the TextView
        Intent checkIntent = getIntent();

        if (checkIntent.getExtras() != null) {
            Bundle parametersMainActivity = getIntent().getExtras();

            tvName.setText(parametersMainActivity.getString("name"));
            tvBirthDate.setText(parametersMainActivity.getString("birthDate"));
            tvPhone.setText(parametersMainActivity.getString("phone"));
            tvEmail.setText(parametersMainActivity.getString("email"));
            tvDescription.setText(parametersMainActivity.getString("description"));
        }

        //OnClick Edit Button Listener
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Exports the data of this activity in variables
                String name = tvName.getText().toString();
                String birthDate = tvBirthDate.getText().toString();
                String phone = tvPhone.getText().toString();
                String email = tvEmail.getText().toString();
                String description = tvDescription.getText().toString();

                //Create the intent to go a MainActivity
                Intent intentToMainActivity = new Intent(ConfirmData.this, MainActivity.class);

                //Exports the data in put extras
                intentToMainActivity.putExtra("name", name);
                intentToMainActivity.putExtra("birthDate", birthDate);
                intentToMainActivity.putExtra("phone", phone);
                intentToMainActivity.putExtra("email", email);
                intentToMainActivity.putExtra("description", description);

                intentToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //Start the New Activity
                startActivity(intentToMainActivity);
                finish();
            }
        });


    }

}