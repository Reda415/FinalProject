package com.codingelab.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class updatephp extends AppCompatActivity  {
    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    Syn syn;
    Button btnsave;
    public static String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatephp);
        syn = new Syn();
        Intent get =getIntent();
        btnsave = (Button) findViewById(R.id.updateb);

        editTextName = (EditText)findViewById(R.id.tname);
        editTextPhone = (EditText)findViewById(R.id.tphone);
        editTextEmail = (EditText)findViewById(R.id.temail);

        id =  get.getStringExtra("id");
        String name = get.getStringExtra("name");
        String phone = get.getStringExtra("phone");
        String email = get.getStringExtra("email");
        editTextName.setText(name);
        editTextPhone.setText(phone);
        editTextEmail.setText(email);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { syn.doInBackground("update",id,editTextName.getText().toString(),editTextPhone.getText().toString(),editTextEmail.getText().toString());

            }
        });

    } }
