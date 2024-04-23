package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Election extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    Button b1;
    SharedPreferences sh;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        t1 = findViewById(R.id.textView2);
        t2 = findViewById(R.id.textView7);
        t3 = findViewById(R.id.textView8);
        t4 = findViewById(R.id.textView11);
        t5 = findViewById(R.id.textView15);
        b1 = findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Viewpost.class));
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        url = sh.getString("url", "") + "/and_election";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject json = new JSONObject(response);


                    String res = json.getString("task");

                    if (res.equalsIgnoreCase("valid")) {

                        t1.setText(json.getString("t"));
                        t2.setText(json.getString("e"));
                        t3.setText(json.getString("n"));
                        t4.setText(json.getString("c"));
                        t5.setText(json.getString("r"));

                    } else {

                        Toast.makeText(getApplicationContext(), "No election for current process", Toast.LENGTH_SHORT).show();
startActivity(new Intent(getApplicationContext(),Election.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        queue.add(stringRequest);





    }
}