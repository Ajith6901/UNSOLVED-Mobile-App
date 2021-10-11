package com.example.quoraclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer_Layout;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawer_Layout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.nav_toolbar);    //check here may be need to change the id(important)
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Unsolved App");

        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this,drawer_Layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
          drawer_Layout.addDrawerListener(toggle);
          toggle.syncState();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_finance:
                Intent intent= new Intent(HomeActivity.this,FinanceActivity.class);
                startActivity(intent);
                break;
        }
        drawer_Layout.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed(){
        if(drawer_Layout.isDrawerOpen(GravityCompat.START)){
            drawer_Layout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

}