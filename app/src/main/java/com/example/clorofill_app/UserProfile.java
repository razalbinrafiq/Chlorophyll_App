package com.example.clorofill_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {


    TextView nameTextView,emailTextView,phoneTextView;
    TextView addressHouseNameTextView,addressAreaTextView,addressCityTextView,addressPinTextView;
    Button passwordEditButton;
    String check_ID;
    String idString,nameString,emailString,phoneString;
    String addHN,addA,addC,addP;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);



        nameTextView=(TextView)findViewById(R.id.nameTextView);
        emailTextView=(TextView)findViewById(R.id.emailIDTextView) ;
        phoneTextView=(TextView)findViewById(R.id.phoneTextView) ;

        passwordEditButton=(Button)findViewById(R.id.editPasswordButton);

        addressHouseNameTextView=(TextView)findViewById(R.id.addressHouseNameTextView) ;
        addressAreaTextView=(TextView)findViewById(R.id.addressAreaTextView) ;
        addressCityTextView=(TextView)findViewById(R.id.addressCityTextView) ;
        addressPinTextView=(TextView)findViewById(R.id.addressPinTextView) ;


        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID = loginDetails.getString("id","0");


        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("users/"+check_ID+"/userDetails");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    idString=snapshot.getKey().toString();
                }

                nameString=snapshot.child("name").getValue(String.class).toUpperCase();
                emailString=snapshot.child("emailId").getValue(String.class);
                phoneString=snapshot.child("mobile").getValue(String.class);

                addHN=snapshot.child("addressHouseName").getValue(String.class);
                addA=snapshot.child("addressArea").getValue(String.class);
                addC=snapshot.child("addressCity").getValue(String.class);
                addP=snapshot.child("addressPin").getValue(String.class);

                nameTextView.setText(nameString);
                emailTextView.setText(emailString);
                phoneTextView.setText(phoneString);

                addressHouseNameTextView.setText(addHN);
                addressAreaTextView.setText(addA);
                addressCityTextView.setText(addC);
                addressPinTextView.setText(addP);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        passwordEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserProfile.this,EditProfile.class);
                startActivity(i);

            }
        });





    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {

        Intent i=new Intent(UserProfile.this,homepage.class);
        finish();
        startActivity(i);


    }

}