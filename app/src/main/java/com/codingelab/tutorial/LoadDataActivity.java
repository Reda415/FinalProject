package com.codingelab.tutorial;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.codingelab.tutorial.R.layout.activity_loaddata;

public class LoadDataActivity extends AppCompatActivity {

    ListView SubjectListView;
    List<Student> usersList;
    public static String sid ;
    public static String name ;
    public static String phone ;
    public static String email ;
    Button update;
    Button deletep;
    Button searsh;

    Syn syn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_loaddata);
        this.getJSON("http://172.20.10.11:8888/sqli/json.php");
        SubjectListView = (ListView) findViewById(R.id.listuser);
        syn = new Syn();
       /* if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }*/

        SubjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                sid = ((TextView)view.findViewById(R.id.textId)).getText().toString();
                name = ((TextView)view.findViewById(R.id.textName)).getText().toString();
                phone = ((TextView)view.findViewById(R.id.textPhone)).getText().toString();
                email = ((TextView)view.findViewById(R.id.textEmail)).getText().toString();

               /* Toast.makeText(getApplicationContext(),
                        "S id : " + sid +"\n"
                                +"name : " + name +"\n"
                                +"phone : " +phone +"\n"
                                +"email : " +email + "\n"
                               , Toast.LENGTH_SHORT).show(); */
            }
        });
        usersList= new ArrayList<>();
        update = (Button)findViewById(R.id.updatep);
        deletep = (Button)findViewById(R.id.deletep);
        searsh = (Button)findViewById(R.id.searhp);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoadDataActivity.this,updatephp.class);
                intent.putExtra("id",sid);
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

        deletep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDeltetPerson ();            }
        });

        searsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoadDataActivity.this,searchphp.class);

                startActivity(intent);
            }
        });

    }
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        //String[] name = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id",obj.getString("id"));
            map.put("name",obj.getString("name"));
            map.put("phone",obj.getString("phone"));
            map.put("email",obj.getString("email"));
            Items.add(map);
        }
        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        // SubjectListView.setAdapter(arrayAdapter);
        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.list_rows, new String[]{"id","name", "phone", "email"},
                new int[]{R.id.textId,R.id.textName, R.id.textPhone, R.id.textEmail});
        SubjectListView.setAdapter(myadapter);
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
                                syn.doInBackground("delete",sid);
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

