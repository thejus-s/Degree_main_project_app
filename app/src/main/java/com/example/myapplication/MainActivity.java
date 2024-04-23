package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button b1;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editTextTextPersonName);
        e1.setText("192.168.29.179");
        b1 = findViewById(R.id.button);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipval=e1.getText().toString();
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("ip",ipval);
                ed.putString("url","http://"+ipval+":1235");
                ed.commit();

                startActivity(new Intent(getApplicationContext(),login.class));

            }
        });
    }
}