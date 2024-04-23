package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Vote_candidates extends AppCompatActivity {
    String[] id,name,photo,course;
    ListView l1;
    SharedPreferences sh;
    TextView t1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_candidates);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=findViewById(R.id.reply);
        t1=findViewById(R.id.textView2);


        b1 = findViewById(R.id.button10);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Vote updated", Toast.LENGTH_SHORT).show();
                int es = Integer.parseInt(sh.getString("pcount",""))+1;
                SharedPreferences.Editor edp = sh.edit();
                edp.putString("pcount", String.valueOf(es));
                edp.commit();
                startActivity(new Intent(getApplicationContext(),Vote_candidates.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });
        String hu = sh.getString("url", "");
        String url = hu + "/votingpage";




        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");

                                t1.setText(jsonObj.getString("p"));

                                id=new String[js.length()];
                                name=new String[js.length()];
                                photo=new String[js.length()];
                                course=new String[js.length()];


                                for(int i=0;i<js.length();i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    id[i] = u.getString("cid");
                                    name[i] = u.getString("Complaint");
                                    photo[i] = u.getString("Date");
                                    course[i] = u.getString("reply");



                                }

                                if (jsonObj.getString("status").equalsIgnoreCase("no")) {
                                    int es = Integer.parseInt(sh.getString("pcount",""))+1;
                                    SharedPreferences.Editor edp = sh.edit();
                                    edp.putString("pcount", String.valueOf(es));
                                    edp.commit();
                                    startActivity(new Intent(getApplicationContext(),Vote_candidates.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));


                                }


                                // ArrayAdapter<String> adpt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
                                l1.setAdapter((ListAdapter) new Customvotecand(getApplicationContext(),id,name,photo,course));
                                // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));
                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), jsonObj.getString("status"), Toast.LENGTH_LONG).show();

                                Intent ik = new Intent(getApplicationContext(), MainActivity2.class);
                                startActivity(ik);
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id=sh.getString("pcount","");
                params.put("pcount",id);
                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

    }
}