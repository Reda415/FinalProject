package com.codingelab.tutorial;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RedaDatabase mydb;

    Button add,search,delete,update,btnclear;
    public static int del;
    private ArrayList<Person> personlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mydb = new RedaDatabase(this);

        add = (Button) findViewById(R.id.btnadd);
        search = (Button) findViewById(R.id.btnsearch);
        delete = (Button) findViewById(R.id.btndelete);
        update = (Button) findViewById(R.id.btnupdate);
        btnclear = (Button) findViewById(R.id.btnClear);

        personlist = new ArrayList<Person>();

        final ListView lview = (ListView) findViewById(R.id.listview);

        listviewAdapter adapter = new listviewAdapter(this, personlist);

        ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        Log.d("Reading: ", "Reading all contacts..");
        List<Person> data = mydb.getAllcontactDetails();
        for (Person val :data)
        {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", val.getId());
            map.put("name", val.getName());
            map.put("phone", val.getPhone());
            map.put("email", val.getEmail());

            Items.add(map);
        }

        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_row,new String[] { "id", "name", "phone", "email"},
                new int[] {R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText });

        lview.setAdapter(myadapter);

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String sid = ((TextView)view.findViewById(R.id.idText)).getText().toString();
                String name = ((TextView)view.findViewById(R.id.NameText)).getText().toString();
                String phone = ((TextView)view.findViewById(R.id.PhoneText)).getText().toString();
                String email = ((TextView)view.findViewById(R.id.EmailText)).getText().toString();

                Toast.makeText(getApplicationContext(),
                        "S id : " + sid +"\n"
                                +"name : " + name +"\n"
                                +"phone : " +phone +"\n"
                                +"email : " +email + "\n"
                        , Toast.LENGTH_SHORT).show();

                del = Integer.parseInt(sid);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewPerson = new Intent(MainActivity2.this, AddActivity.class);
                startActivity(addNewPerson);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchPerson = new Intent(MainActivity2.this, SearchActivity.class);
                startActivity(searchPerson);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                OnDeltetPerson();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent UpdatePerson = new Intent(MainActivity2.this, update.class);
                startActivity(UpdatePerson);

            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on fetch");
                Cursor getData=mydb.last(); //specific record (id=1)

                if (getData.moveToNext()) {// data?
                    Log.v("georgeLog", "data found in DB...");
                    String dName = getData.getString(getData.getColumnIndex("name"));
                    String dPhone = getData.getString(getData.getColumnIndex("phone"));
                    String dEmail = getData.getString(getData.getColumnIndex("email"));
                    Toast.makeText(getApplicationContext(),
                            "rec: " +"Name: "+ dName + ", "+"Phone Number: " + dPhone + ", "+"E-mail: " + dEmail , Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "did not get any data...:-(", Toast.LENGTH_LONG).show();

                getData.close();
            }
        });

    }

    public void OnDeltetPerson ()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete person?");
        alertDialogBuilder
                .setMessage("Click yes to Delete!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(del);

                            }
                        })


                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
