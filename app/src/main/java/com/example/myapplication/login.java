package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class login extends AppCompatActivity {
    EditText e1, e2;
    Button b1;
    TextView b2;
    SharedPreferences sh;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1 = findViewById(R.id.editTextTextPersonName2);
        e2 = findViewById(R.id.editTextTextPersonName3);
        b1 = findViewById(R.id.button2);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=e1.getText().toString();
                String password=e2.getText().toString();
////////////////////////

                if (uname.isEmpty()) {
                    e1.setError("Username cannot be empty");
                    e1.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    e2.setError("Password cannot be empty");
                    e2.requestFocus();
                    return;
                }


                RequestQueue queue = Volley.newRequestQueue(login.this);
                url = sh.getString("url", "") + "/and_login";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
//                        Toast.makeText(login.this, "welcome"+response, Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject json = new JSONObject(response);


                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("valid")) {

                                /////////////////////////////
                                String lid = json.getString("lid");
                                String type = json.getString("type");
                                String es = json.getString("es");
                                SharedPreferences.Editor edp = sh.edit();
                                edp.putString("lid", lid);
                                edp.putString("es", es);
                                edp.commit();

                                Toast.makeText(login.this, type, Toast.LENGTH_SHORT).show();

                                if(type.equalsIgnoreCase("user"))
                                {
                                    Toast.makeText(login.this, es, Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(),newhome.class);

                                    startActivity(ik);
                                }
                                Toast.makeText(login.this, "welcome", Toast.LENGTH_SHORT).show();


                            } else {

                                Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(login.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
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


                        params.put("uname", uname);
                        params.put("pswd", password);

                        return params;
                    }
                };
                queue.add(stringRequest);

///////////////////////////


            }
        });

    }
}