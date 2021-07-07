package com.example.clorofill_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbars=findViewById(R.id.toolsbars);
        setSupportActionBar(toolbars);





        drawerLayout = findViewById(R.id.F_drawer_layout);

        NavigationView navigationView = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbars,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_khome:
                Toast.makeText(this, "clicked here", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_farmers_registration:
                Toast.makeText(this, "mail_clicked second irem", Toast.LENGTH_SHORT).show();
               break;

            case R.id.nav_profile:
                Toast.makeText(this, "clicked here", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_product_to_sell:
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_mail:
                Toast.makeText(this, "mail_clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Log:

                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}