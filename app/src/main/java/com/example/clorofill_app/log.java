package com.example.clorofill_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class log extends  AppCompatActivity {
    //Animation top;
    //ImageView im;


    String check_ID, check_Password,check_Mode, user_id,password,getCheck_ID;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.splahlayout);
        //im=findViewById(R.id.ksf);
        //top=AnimationUtils.loadAnimation(log.this,R.anim.top_animation);
        //im.setAnimation(top);

        //startActivity(new Intent(this,LoginPage.class));
        Window window = com.example.clorofill_app.log.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.setStatusBarColor(ContextCompat.getColor(com.example.clorofill_app.log.this, R.color.white));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
                check_ID = loginDetails.getString("id","0");
                check_Password = loginDetails.getString("password","0");
                check_Mode = loginDetails.getString("mode","0");



                if(check_ID!="0"){

                    user_id=check_ID;
                    if(check_Mode.equals("user"))
                    {
                        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("users");

                        fb_to_read.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                List<String> list=new ArrayList<String>();
                                for (DataSnapshot dsp : snapshot.getChildren()){
                                    list.add(String.valueOf(dsp.getKey()));
                                }
                                for(final String data:list){
//                        Toast.makeText(LoginPage.this, data, Toast.LENGTH_SHORT).show();
                                    if(data.equals(user_id.toString()))
                                    {
                                        password=check_Password;
                                        DatabaseReference fb_read = FirebaseDatabase.getInstance().getReference("users"+"/"+user_id+"/"+"userDetails"+"/"+"password1");
                                        fb_read.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                String pass=snapshot.getValue(String.class);
//                                    Toast.makeText(LoginPage.this,pass, Toast.LENGTH_SHORT).show();
                                                if(pass.equals(password.toString())){
                                                    //Toast.makeText(LoginPage.this,"login", Toast.LENGTH_SHORT).show();
                                                    SharedPreferences loginDetails = getSharedPreferences("loginDetails", MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = loginDetails.edit();
                                                    editor.putString("id",user_id );
                                                    editor.putString("password",password );
                                                    editor.putString("mode","user" );
                                                    editor.commit();

                                                    Intent login=new Intent(com.example.clorofill_app.log.this,UserActivity.class);
                                                    login.putExtra("user_id",user_id);
                                                    startActivity(login);
                                                    finish();

                                                }
                                                else
                                                {
                                                    Toast.makeText(com.example.clorofill_app.log.this, "WRONG USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                    // Toast.makeText(LoginPage.this, "WRONG USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }



                }
                else
                {
                    Intent intent=new Intent(com.example.clorofill_app.log.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, 2000);




        // finish();
    }
}
