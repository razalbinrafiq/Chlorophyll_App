package com.example.clorofill_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {


    String check_ID,check_Mode,path;
    EditText nameEditText,emailEditText,passwordEditText,phoneEditText,confirmEditText;
    EditText addressHouseNameEditText,addressAreaEditTextView,addressCityEditText,addressPinEditText;
    Button saveButton;
    String idString,nameString,emailString,phoneString,passwordString;
    String aHN,aA,aC,aP;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID = loginDetails.getString("id","0");
        path="users/"+check_ID+"/userDetails";


        nameEditText=(EditText)findViewById(R.id.nameEditText);
        emailEditText=(EditText)findViewById(R.id.emailEditText);
        passwordEditText=(EditText)findViewById(R.id.passwordEditText);
        phoneEditText=(EditText)findViewById(R.id.phoneEditText);
        confirmEditText=(EditText)findViewById(R.id.confirmEditText);
        addressHouseNameEditText=(EditText)findViewById(R.id.addressHouseNameEditText);
        addressAreaEditTextView=(EditText)findViewById(R.id.addressAreaEditTextView);
        addressCityEditText=(EditText)findViewById(R.id.addressCityEditText);
        addressPinEditText=(EditText)findViewById(R.id.addressPinEditText);
        saveButton=(Button)findViewById(R.id.saveButton);

        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference(path);

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
                passwordString=snapshot.child("password1").getValue(String.class);

                aHN=snapshot.child("addressHouseName").getValue(String.class);
                aA=snapshot.child("addressArea").getValue(String.class);
                aC=snapshot.child("addressCity").getValue(String.class);
                aP=snapshot.child("addressPin").getValue(String.class);



                nameEditText.setText(nameString);
                emailEditText.setText(emailString);
                phoneEditText.setText(phoneString);
                passwordEditText.setText("******");

                addressHouseNameEditText.setText(aHN);
                addressAreaEditTextView.setText(aA);
                addressCityEditText.setText(aC);
                addressPinEditText.setText(aP);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkPassword=confirmEditText.getText().toString();

                if(passwordString.equals(checkPassword)){
                    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

                    String namePath = path+ "/name";
                    String emailPath = path + "/emailId";
                    String mobilePath = path + "/mobile";
                    String passwordPath = path + "/password1";
                    String HNPath = path + "/addressHouseName";
                    String APath = path + "/addressArea";
                    String CPath = path + "/addressCity";
                    String PPath = path + "/addressPin";

                    DatabaseReference mName = mDatabase.getReference(namePath);
                    DatabaseReference mEmail = mDatabase.getReference(emailPath);
                    DatabaseReference mMobile = mDatabase.getReference(mobilePath);
                    DatabaseReference mPassword = mDatabase.getReference(passwordPath);
                    DatabaseReference mHN = mDatabase.getReference(HNPath);
                    DatabaseReference mA = mDatabase.getReference(APath);
                    DatabaseReference mC = mDatabase.getReference(CPath);
                    DatabaseReference mP = mDatabase.getReference(PPath);

                    String getName=nameEditText.getText().toString();
                    String getEmail=emailEditText.getText().toString();
                    String getMobile=phoneEditText.getText().toString();
                    String getPassword=passwordEditText.getText().toString();
                    String getHN=addressHouseNameEditText.getText().toString();
                    String getA=addressAreaEditTextView.getText().toString();
                    String getC=addressCityEditText.getText().toString();
                    String getP=addressPinEditText.getText().toString();

                    //DatabaseReference chittyCount = mDatabase.getReference( user_id + "/" + username + "/chittycount");

                    //DatabaseReference passw2 = mDatabase.getReference(password2);

                    mName.setValue(getName);
                    mEmail.setValue(getEmail);
                    mMobile.setValue(getMobile);
                    mHN.setValue(getHN);
                    mA.setValue(getA);
                    mC.setValue(getC);
                    mP.setValue(getP);
//                    if(getPassword!="******") {
//                        mPassword.setValue(getPassword);
//                    }
                    SharedPreferences loginDetails = getSharedPreferences("loginDetails", MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginDetails.edit();
                    editor.putString("id","0" );
                    editor.putString("password","0" );
                    editor.putString("mode","0" );
                    editor.commit();

                    Intent login=new Intent(EditProfile.this,MainActivity.class);
                    finish();
                    startActivity(login);
                    System.exit(0);


                }



            }
        });


    }
}