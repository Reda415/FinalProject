package com.codingelab.tutorial;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    RedaDatabase mydb;

    Button search12;
    TextView tId, tName, tPhone, tEmail;
    EditText finde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mydb = new RedaDatabase(this);

        search12 = (Button)findViewById(R.id.btnFind);


        tId = (TextView)findViewById(R.id.textId1);
        tName = (TextView)findViewById(R.id.textName1);
        tPhone = (TextView)findViewById(R.id.textPhone1);
        tEmail = (TextView)findViewById(R.id.textEmail1);

        finde = (EditText)findViewById(R.id.editFind);

        search12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = Integer.parseInt(finde.getText().toString());
                Cursor getRecord = mydb.getData(found);
                if(getRecord.moveToNext())
                {
                    String dId = getRecord.getString(getRecord.getColumnIndex("id"));
                    String dName = getRecord.getString(getRecord.getColumnIndex("name"));
                    String dPhone = getRecord.getString(getRecord.getColumnIndex("phone"));
                    String dEmail = getRecord.getString(getRecord.getColumnIndex("email"));

                    tId.setText(dId);
                    tName.setText(dName);
                    tPhone.setText(dPhone);
                    tEmail.setText(dEmail);
                }

            }
        });

    }
}
