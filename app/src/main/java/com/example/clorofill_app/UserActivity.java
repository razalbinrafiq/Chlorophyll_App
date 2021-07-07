package com.example.clorofill_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }





    public void logout(){

        SharedPreferences loginDetails = getSharedPreferences("loginDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginDetails.edit();
        editor.putString("id","0" );
        editor.putString("password","0" );
        editor.putString("mode","0" );
        editor.commit();

        Intent login=new Intent(UserActivity.this,MainActivity.class);
        finish();
        startActivity(login);
        System.exit(0);

    }

    public void profiles()
    {
////        String sendingprofile="users/"+user+"/userDetails";
////
//        Intent login=new Intent(UserActivity.this,UserActivityJewellery.class);
//        login.putExtra("user_id",user);
//        startActivity(login);
//        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.agent2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:logout();
                return  true;
            case R.id.profile:profiles();
                return  true;
            default: return super.onOptionsItemSelected(item);
        }

    }

 /*   @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }*/


    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
            System.exit(0);// finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

}