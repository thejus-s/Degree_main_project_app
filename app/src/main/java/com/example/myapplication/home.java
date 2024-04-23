package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class home extends AppCompatActivity implements View.OnClickListener {

    Button reply,election,result,verification,camera,logout,vnomi,snomi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        reply=findViewById(R.id.button11);
        election=findViewById(R.id.button12);
        verification=findViewById(R.id.button13);
        result=findViewById(R.id.button14);
        camera=findViewById(R.id.button15);
        logout=findViewById(R.id.button16);
        vnomi=findViewById(R.id.button25);
        snomi=findViewById(R.id.button21);
        vnomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Viewnominee.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });
    snomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Apply.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });

        reply.setOnClickListener(this);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), sample.class));
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), REsult.class));
            }
        });
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Verification.class));
            }
        });

        election.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Election.class));
            }
        });


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), View_reply.class));
    }
}