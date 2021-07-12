package com.example.clorofill_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProductDetails extends AppCompatActivity {


    String productName,id;
    ImageView productImage;
    TextView productNameTextView,totalAmountTextView,availableAmountTextView,descriptionTextView,deliveryTextView;
    EditText amountEditText;
    Button buyButton;
    String check_ID;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference getRef1,getRef2;
    String getItemName,getItemCount;
    int qua;
    int num;
    int n;
    String itemName,iName;
    String getProductName,getProductPrice,getProductType,getProductQuantity,getProductDescription,getProductImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID= loginDetails.getString("id","0");

        getRef1 = FirebaseDatabase.getInstance().getReference("users/"+check_ID+"/itemsBoughtCount");
        getRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                iName=snapshot.getKey();
                Toast.makeText(ProductDetails.this, "iii+"+ iName, Toast.LENGTH_SHORT).show();
                num =snapshot.getValue(Integer.class);

                num=num+1;

                Toast.makeText(ProductDetails.this, "iii+"+ n, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });




        Bundle login = getIntent().getExtras();
        if (login != null) {
            productName = login.getString("name");
            //  Toast.makeText(MainActivity.this,user, Toast.LENGTH_SHORT).show();
        }



        productNameTextView=(TextView)findViewById(R.id.productNameTextView);
        totalAmountTextView=(TextView)findViewById(R.id.totalAmountTextView);
        availableAmountTextView=(TextView)findViewById(R.id.availableAmountTextView);
        descriptionTextView=(TextView)findViewById(R.id.descriptionTextView);
        deliveryTextView=(TextView)findViewById(R.id.deliveryTextView);
        amountEditText=(EditText) findViewById(R.id.amountEditText);
        buyButton=(Button) findViewById(R.id.buyButton);
        productImage=(ImageView) findViewById(R.id.productImage);





        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("product/"+productName);

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                   id=snapshot.getKey();
                }



                getProductName=snapshot.child("name").getValue(String.class);
                getProductPrice=snapshot.child("price").getValue(String.class);
                getProductType=snapshot.child("type").getValue(String.class);
                getProductQuantity=snapshot.child("quantity").getValue(String.class);
                getProductDescription=snapshot.child("description").getValue(String.class);
                getProductImage=snapshot.child("image").getValue(String.class);


                    SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
                    String type= loginDetails.getString("type","0");


                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");// HH:mm:ss");
                String reg_date = df.format(c.getTime());
            //    showtoast("Currrent Date Time : "+reg_date);

                c.add(Calendar.DATE, 7);  // number of days to add
                String end_date = df.format(c.getTime());
             //   showtoast("end Time : "+end_date);


                Picasso.get().load(getProductImage).fit().centerCrop().into(productImage);

                productNameTextView.setText(getProductName);
                totalAmountTextView.setText(getProductPrice);
                descriptionTextView.setText(getProductDescription);
                availableAmountTextView.setText(getProductQuantity);
                deliveryTextView.setText(deliveryTextView.getText()+end_date);
              //  productNameTextView.setText(getProductName);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
               check_ID= loginDetails.getString("id","0");
                String toAmount=amountEditText.getText().toString();
                String toName=productNameTextView.getText().toString();
                //   String chittyPaymentDate=shareamount.getText().toString();

                String fbChittynum="users/"+check_ID+"/cart/"+num;
                String fbNum="users/"+check_ID+"/itemsBoughtCount";
                String fbN=fbChittynum+"/itemName";
                String fbQ=fbChittynum +"/quantity";
                String fbI=fbChittynum +"/image";
                String fbP=fbChittynum +"/price";
                ;

                DatabaseReference mDbRef1 = mDatabase.getReference(fbN);
                DatabaseReference mDbRef2 = mDatabase.getReference(fbQ);
                DatabaseReference mDbRef3 = mDatabase.getReference(fbNum);
                DatabaseReference mDbRef4 = mDatabase.getReference(fbI);
                DatabaseReference mDbRef5 = mDatabase.getReference(fbP);


                mDbRef1.setValue(toName);
                mDbRef2.setValue(toAmount);
                mDbRef3.setValue(num);
                mDbRef4.setValue(getProductImage);
                mDbRef5.setValue(getProductPrice);

            }
        });





    }

//    public  int callfn( ){
//
//
//
//
//        return n;
//
//
//    }

}