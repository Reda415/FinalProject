package com.codingelab.tutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class update extends AppCompatActivity {
    RedaDatabase mydb;
    Button save;
    EditText name,email,phone;
    private ArrayList<Person> personlist;
    public static String sName,sPhone,sEmail;
    public static String lname,lphone,lemil;
    public static int update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update2);
        mydb = new RedaDatabase(this);
        save = (Button) findViewById(R.id.btnSave2);
        name = (EditText)findViewById(R.id.editTextName);
        phone = (EditText)findViewById(R.id.editTextPhone);
        email = (EditText)findViewById(R.id.editTextEmail);

        personlist = new ArrayList<Person>();

        final ListView lview = (ListView) findViewById(R.id.updateList);

        listviewAdapter adapter = new listviewAdapter(this, personlist);

        ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        Log.d("Reading: ", "Reading all contacts..");
        List<Person> data = mydb.getAllcontactDetails();
        for (Person val : data) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", val.getId());
            map.put("name", val.getName());
            map.put("phone", val.getPhone());
            map.put("email", val.getEmail());

            Items.add(map);
        }

        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_row, new String[]{"id", "name", "phone", "email"},
                new int[]{R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});

        lview.setAdapter(myadapter);

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String sid = ((TextView) view.findViewById(R.id.idText)).getText().toString();
                lname = ((TextView) view.findViewById(R.id.NameText)).getText().toString();
                lphone = ((TextView) view.findViewById(R.id.PhoneText)).getText().toString();
                lemil = ((TextView) view.findViewById(R.id.EmailText)).getText().toString();
                update = Integer.parseInt(sid);
                loadIntoEditText();
            }
        });




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sName = name.getText().toString();
                sPhone = phone.getText().toString();
                sEmail = email.getText().toString();
                if (mydb.updateContact(update,sName,sPhone,sEmail))
                {
                    Toast.makeText(getApplicationContext(), "Update Compleated(", Toast.LENGTH_LONG).show();
                    clear();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Not Update it Yet!!(", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public void loadIntoEditText()
    {
        name.setText(lname);
        phone.setText(lphone);
        email.setText(lemil);
    }
    public void clear()
    {
        name.setText("");
        phone.setText("");
        email.setText("");
    }
}
