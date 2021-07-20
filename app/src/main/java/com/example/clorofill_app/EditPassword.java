package com.example.clorofill_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditPassword extends AppCompatActivity {

    String user,password,confirmpassword;
    EditText password1,password2;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        Bundle forgetPassword=getIntent().getExtras();
        if(forgetPassword!=null){
            user=forgetPassword.getString("user_id");
            //  Toast.makeText(MainActivity.this,user, Toast.LENGTH_SHORT).show();
        }
        password1=findViewById(R.id.passwordEditText);
        password2=findViewById(R.id.confirmpasswordEditText);
        save=findViewById(R.id.saveButton);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password=password1.getText().toString();
                confirmpassword=password2.getText().toString();

                if(password.equals(confirmpassword)) {
                    FirebaseDatabase regDatabase = FirebaseDatabase.getInstance();
                    String path = "users/" + user + "/userDetails/password1";
                    DatabaseReference passwordReference = regDatabase.getReference(path );
                    passwordReference.setValue(password);
                    Intent toLoginPage=new Intent(EditPassword.this,MainActivity.class);
                    startActivity(toLoginPage);
                    finish();
                }
                else
                {
                    Toast.makeText(EditPassword.this, "NOT EQUAL", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}