//package com.example.myapplication;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.Menu;
//import android.widget.Button;
//
//import com.google.android.material.snackbar.Snackbar;
//import com.google.android.material.navigation.NavigationView;
//
//import androidx.annotation.NonNull;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.myapplication.databinding.ActivityMain2Binding;
//
//public class MainActivity2 extends AppCompatActivity {
//
//    private AppBarConfiguration mAppBarConfiguration;
//    private ActivityMain2Binding binding;
//Button b1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMain2Binding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        b1 = findViewById(R.id.button11);
//        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//
//        if(sh.getString("es","").equalsIgnoreCase("1")){
//            b1.setVisibility(View.VISIBLE);
//        }
//        else {
//            b1.setVisibility(View.INVISIBLE);
//        }
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences.Editor edp = sh.edit();
//                edp.putString("pcount", "0");
//                edp.commit();
//                startActivity(new Intent(getApplicationContext(),Vote_candidates.class));
//            }
//        });
//
//        setSupportActionBar(binding.appBarMain.toolbar);
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        DrawerLayout drawer = binding.drawerLayout;
//        NavigationView navigationView = binding.navView;
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//        navigationView.setItemIconTintList(null);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if(id == R.id.com){
//                    startActivity(new Intent(getApplicationContext(),View_reply.class));
//                }
//                if(id == R.id.e){
//                    startActivity(new Intent(getApplicationContext(),Election.class));
//                }
//                if(id == R.id.a){
//                    startActivity(new Intent(getApplicationContext(),Verification.class));
//                }
//
//                if(id == R.id.r){
//                    startActivity(new Intent(getApplicationContext(),REsult.class));
//                }
//
//                if(id == R.id.l){
//                    startActivity(new Intent(getApplicationContext(),login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                }
//                if(id == R.id.cam){
//                    startActivity(new Intent(getApplicationContext(),cameraopen.class));
//                }
//                return false;
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_activity2, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
//}



package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import com.example.myapplication.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMain2Binding binding;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        b1 = findViewById(R.id.button11);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(sh.getString("es","").equalsIgnoreCase("1")){
            b1.setVisibility(View.VISIBLE);
        } else {
            b1.setVisibility(View.INVISIBLE);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edp = sh.edit();
                edp.putString("pcount", "0");
                edp.apply(); // Changed from commit() to apply()
                startActivity(new Intent(getApplicationContext(), Vote_candidates.class));
            }
        });

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.com){
                    startActivity(new Intent(getApplicationContext(), View_reply.class));
                }
                if(id == R.id.e){
                    startActivity(new Intent(getApplicationContext(), Election.class));
                }
                if(id == R.id.a){
                    startActivity(new Intent(getApplicationContext(), Verification.class));
                }

                if(id == R.id.r){
                    startActivity(new Intent(getApplicationContext(), REsult.class));
                }

                if(id == R.id.l){
                    startActivity(new Intent(getApplicationContext(), login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                if(id == R.id.cam){
                    startActivity(new Intent(getApplicationContext(), cameraopen.class));
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
