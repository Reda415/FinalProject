package com.codingelab.tutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    public static  int lastrecord;

    RedaDatabase mydb;
    Button btnsave;
    Button btnclear;

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activaty_add);

        ArrayAdapter<String> arrayAdapter;

        mydb = new RedaDatabase(this);

        btnsave = (Button) findViewById(R.id.btnSave);
        btnclear = (Button) findViewById(R.id.btnClear);

        editTextName = (EditText)findViewById(R.id.editName);
        editTextPhone = (EditText)findViewById(R.id.editPhone);
        editTextEmail = (EditText)findViewById(R.id.editEmail);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();

                String getName = editTextName.getText().toString();
                String getPhone = editTextPhone.getText().toString();
                String getEmail = editTextEmail.getText().toString();

                if (mydb.insertContact(getName,getPhone,getEmail)) {
                    Log.v("georgeLog", "Successfully inserted record to db");
                    Toast.makeText(getApplicationContext(),
                            "Inserted:" + getName + ", " + getPhone + "," + getEmail + "," , Toast.LENGTH_SHORT).show();
                    editTextName.setText("");
                    editTextPhone.setText("");
                    editTextEmail.setText("");
                } else
                    Toast.makeText(getApplicationContext(), "DID NOT insert to db :-(", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
