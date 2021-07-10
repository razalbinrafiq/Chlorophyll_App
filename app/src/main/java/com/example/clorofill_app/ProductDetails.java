package com.example.clorofill_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {


    String productName,id;
    TextView productNameTextView,totalAmountTextView,availableAmountTextView,descriptionTextView,deliveryTextView;
    EditText amountEditText;
    Button buyButton;
    String check_ID;
    DatabaseReference getRef1,getRef2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        Bundle login = getIntent().getExtras();
        if (login != null) {
            productName = login.getString("name");
            //  Toast.makeText(MainActivity.this,user, Toast.LENGTH_SHORT).show();
        }

        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID = loginDetails.getString("id","0");


        productNameTextView=(TextView)findViewById(R.id.productNameTextView);
        totalAmountTextView=(TextView)findViewById(R.id.totalAmountTextView);
        availableAmountTextView=(TextView)findViewById(R.id.availableAmountTextView);
        descriptionTextView=(TextView)findViewById(R.id.descriptionTextView);
        deliveryTextView=(TextView)findViewById(R.id.deliveryTextView);
        amountEditText=(EditText) findViewById(R.id.amountEditText);
        buyButton=(Button) findViewById(R.id.buyButton);




        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("product/"+productName);

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                   id=snapshot.getKey();
                }


                    String getProductName=snapshot.child("name").getValue(String.class);
                    String getProductPrice=snapshot.child("price").getValue(String.class);
                    String getProductType=snapshot.child("type").getValue(String.class);
                    String getProductQuantity=snapshot.child("quantity").getValue(String.class);
                    String getProductDescription=snapshot.child("description").getValue(String.class);


                    SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
                    String type= loginDetails.getString("type","0");



                productNameTextView.setText(getProductName);
                totalAmountTextView.setText(getProductPrice);
                descriptionTextView.setText(getProductDescription);
                availableAmountTextView.setText(getProductQuantity);
              //  productNameTextView.setText(getProductName);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getRef1 = FirebaseDatabase.getInstance().getReference("users/"+ check_ID+ "/itemsBoughtCount");
                getRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       int  num= (int) snapshot.getValue(Integer.class);
                        num=num+1;

                    //    Toast.makeText(ProductDetails.this, String.valueOf(num), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });





    }
}