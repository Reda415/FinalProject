package com.codingelab.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    Button local, local2;
    private Syn syn;
    RedaDatabase mydb;
    public static ArrayList<String> localName = new ArrayList<>();
    public static ArrayList<String> localPhone = new ArrayList<>();
    public static ArrayList<String> localEmail = new ArrayList<>();
    public static ArrayList<String> onlineName = new ArrayList<>();
    public static ArrayList<String> onlinePhone = new ArrayList<>();
    public static ArrayList<String> onlineEmail = new ArrayList<>();
    public static String oname, ophone, oemail;
    public static String lname2, lphone2, lemail2;
    Button sync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mydb = new RedaDatabase(this);
        this.syn = new Syn();
        sync = (Button) findViewById(R.id.sek);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loc = new Intent(HomeActivity.this, SyncActivity.class);
                startActivity(loc);

            }
        });
        local = (Button) findViewById(R.id.button2);
        local2 = (Button) findViewById(R.id.button);

        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loc = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(loc);

            }
        });
        local2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loc = new Intent(HomeActivity.this, MainActivity2.class);
                startActivity(loc);

            }
        });
    }
}
