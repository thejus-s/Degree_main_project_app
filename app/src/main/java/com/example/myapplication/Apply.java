package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.Manifest;
import android.widget.Toast;;import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Apply extends AppCompatActivity {
ImageView imageView;
EditText e1,e2,e3;
Button b1;
Bitmap bitmap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        imageView = findViewById(R.id.imageView2);
        e1 = findViewById(R.id.editTextTextPersonName4);
        e2 = findViewById(R.id.editTextTextPersonName5);
        e3 = findViewById(R.id.editTextTextPersonName6);
        b1 = findViewById(R.id.button6);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                    Uri.parse("package:" + getPackageName()));
//            finish();
//            startActivity(intent);
//            return;
//        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String d  = e1.getText().toString();
                String s1 = e2.getText().toString();
                String s2 = e3.getText().toString();

                int flg = 0;

                if(d.equalsIgnoreCase("")){
                    e1.setError("*");
                    flg++;
                }
                if(s1.equalsIgnoreCase("")){
                    e2.setError("*");
                    flg ++;
                }
                if(s2.equalsIgnoreCase("")){
                    e2.setError("*");
                    flg ++;
                }

                if(flg == 0){
                    ProgressDialog pd=new ProgressDialog(Apply.this);
                    pd.setMessage("Uploading....");
                    pd.show();
                    SharedPreferences o= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String apiURL = o.getString("url", "") + "/applyascandidatepost";
                    VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, apiURL,
                            new Response.Listener<NetworkResponse>() {
                                @Override
                                public void onResponse(NetworkResponse response) {
                                    try {
                                        pd.dismiss();
                                        Intent i =new Intent(getApplicationContext(),MainActivity2.class);
                                        startActivity(i);

                                        JSONObject obj = new JSONObject(new String(response.data));
                                        Toast.makeText(getApplicationContext(), obj.getString("status"), Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }) {


                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            SharedPreferences o= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            params.put("d", d);//passing to python
                            params.put("s1", s1);//passing to python
                            params.put("s2", s2);
                            params.put("lid", o.getString("lid",""));
                            params.put("pid",  o.getString("pid",""));

                            return params;
                        }


                        @Override
                        protected Map<String, DataPart> getByteData() {
                            Map<String, DataPart> params = new HashMap<>();
                            long imagename = System.currentTimeMillis();
                            params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));

                            return params;
                        }
                    };

                    Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);
                }


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    //converting to bitarray
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}