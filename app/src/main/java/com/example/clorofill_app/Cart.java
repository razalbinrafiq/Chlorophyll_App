package com.example.clorofill_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassOfCart> userListCart;
    AdapterOfCart adapter;

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference getRef1,getRef2;



    int i=1;
    int num;
    int totalSum=0;
    String check_ID;
    String getItemName,getItemQuantity,getItemImage,getItemPrice;
    String orderCountString;
    int orderCountInt;

    String idString,nameString,emailString,phoneString,addHN,addA,addC,addP;

    TextView totalAmountTextView;
    Button buyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID= loginDetails.getString("id","0");
        totalAmountTextView=(TextView)findViewById(R.id.totalAmountTextView);
        buyButton=(Button) findViewById(R.id.buyButton);



        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("users/"+check_ID+"/userDetails");

        fb_to_read.addListenerForSingleValueEvent(new ValueEventListener() {
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




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation_homepage);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),homepage.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.cart:
                        return true;
                }
                return false;
            }
        });



        getRef1 = FirebaseDatabase.getInstance().getReference("order/orderCount");
        getRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               String iName=snapshot.getKey();
        //        Toast.makeText(Cart.this, "iii+"+ iName, Toast.LENGTH_SHORT).show();
                orderCountInt =snapshot.getValue(Integer.class);

                orderCountInt=orderCountInt-1;

        //        Toast.makeText(Cart.this, "iii+"+ orderCountInt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("users/"+check_ID+"/cart");

                fb_to_read.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        List<String> list=new ArrayList<String>();
                        for (DataSnapshot dsp : snapshot.getChildren()){
                            list.add(String.valueOf(dsp.getKey()));
                        }

                        for(final String data:list){





                            String fbPath="order/list/" + orderCountInt;
                            String fbOrderCount="order/orderCount";
                            String fbName=fbPath+"/name";
                            String fbAddressHN=fbPath+"/addressHouseName";
                            String fbAddressCity=fbPath+"/addressCity";
                            String fbAddressArea=fbPath+"/addressArea";
                            String fbAddressPin=fbPath+"/addressPin";
                            String fbStatus=fbPath+"/bookingStatus";
                            String fbImage=fbPath+"/image";
                            String fbOP=fbPath+"/orders";
                            String orders="users/"+check_ID+"/myOrders/"+orderCountInt;

                            DatabaseReference mDbRef0 = mDatabase.getReference(fbOrderCount);
                            DatabaseReference mDbRef1 = mDatabase.getReference(fbName);
                            DatabaseReference mDbRef2 = mDatabase.getReference(fbOP);
                            DatabaseReference mDbRef7 = mDatabase.getReference(fbAddressHN);
                            DatabaseReference mDbRef8 = mDatabase.getReference(fbAddressArea);
                            DatabaseReference mDbRef9 = mDatabase.getReference(fbAddressCity);
                            DatabaseReference mDbRef10 = mDatabase.getReference(fbAddressPin);
                            DatabaseReference mDbRef11 = mDatabase.getReference(fbStatus);
                            DatabaseReference mDbRef12 = mDatabase.getReference(orders);
                            DatabaseReference mDbRef13 = mDatabase.getReference(fbImage);






                            getItemName=snapshot.child(data).child("itemName").getValue(String.class).toUpperCase();
                            getItemQuantity=snapshot.child(data).child("quantity").getValue(String.class);
                            getItemImage=snapshot.child(data).child("image").getValue(String.class);
                            getItemPrice=snapshot.child(data).child("price").getValue(String.class);

                            totalSum=totalSum+Integer.parseInt(getItemPrice)*Integer.parseInt(getItemQuantity);
                            //  Toast.makeText(Cart.this, getItemPrice, Toast.LENGTH_SHORT).show();
//                    String getShareType=snapshot.child(data).child("typeOfShop").getValue(String.class);
//
//                    if(getShareType.equals("Others")) {
                        //    i++;
                            String fbNum=fbOP+"/"+data;
                            String fbItem=fbPath+"/item";
                            String fbQuantity=fbPath+"/quantity";
                            String fbPrice=fbPath+"/price";
                            String fbDate=fbPath+"/orderedDate";
                            String fbID=fbPath+"/orderedID";

                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");// HH:mm:ss");
                            String reg_date = df.format(c.getTime());
                            //    showtoast("Currrent Date Time : "+reg_date);

                            c.add(Calendar.DATE, 0);  // number of days to add
                            String end_date = df.format(c.getTime());
                            //   showtoast("end Time : "+end_date);

                           // DatabaseReference mDbRef3 = mDatabase.getReference(fbNum);
                            DatabaseReference mDbRef4 = mDatabase.getReference(fbItem);
                            DatabaseReference mDbRef5 = mDatabase.getReference(fbQuantity);
                            DatabaseReference mDbRef6 = mDatabase.getReference(fbPrice);
                            DatabaseReference mDbRef14 = mDatabase.getReference(fbDate);
                            DatabaseReference mDbRef15 = mDatabase.getReference(fbID);

                           // mDbRef2.setValue();
                           // mDbRef3.setValue();


                            mDbRef0.setValue(orderCountInt);
                            mDbRef1.setValue(String.valueOf(check_ID));
                            mDbRef7.setValue(String.valueOf(addHN));
                            mDbRef8.setValue(String.valueOf(addA));
                            mDbRef9.setValue(String.valueOf(addC));
                            mDbRef10.setValue(String.valueOf(addP));
                            mDbRef11.setValue("Booked");
                            mDbRef12.setValue(orderCountInt);
                            mDbRef13.setValue(getItemImage);
                            mDbRef14.setValue(reg_date);
                            mDbRef15.setValue(orderCountInt);



                            mDbRef4.setValue(getItemName);
                            mDbRef5.setValue(getItemQuantity);
                            mDbRef6.setValue(getItemPrice);

                            orderCountInt=orderCountInt-1;

                            DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("users/"+check_ID+"/cart/"+data);
                            fb_to_read.getRef().removeValue();



                        }

                        initRecyclerView();
                        totalAmountTextView.setText("  ₹ "+String.valueOf(totalSum));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });










//                mDbRef0.setValue(orderCountInt);
//                mDbRef1.setValue(String.valueOf(check_ID));
//                mDbRef7.setValue(String.valueOf(addHN));
//                mDbRef8.setValue(String.valueOf(addA));
//                mDbRef9.setValue(String.valueOf(addC));
//                mDbRef10.setValue(String.valueOf(addP));
//                mDbRef11.setValue("Booked");
//                mDbRef12.setValue(orderCountInt);



                Intent back=new Intent(Cart.this, homepage.class);
                finish();
                startActivity(back);

            }
        });


        initData("1,","2","2,3","4,","5","67");
    }



    private void initRecyclerView() {

        recyclerView=findViewById(R.id.recyclerViewCart);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AdapterOfCart(userListCart);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(String name,String amount, String date,String id,String shareId, String number) {


        userListCart = new ArrayList<>();



        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("users/"+check_ID+"/cart");

        fb_to_read.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){

                    getItemName=snapshot.child(data).child("itemName").getValue(String.class).toUpperCase();
                    getItemQuantity=snapshot.child(data).child("quantity").getValue(String.class);
                    getItemImage=snapshot.child(data).child("image").getValue(String.class);
                    getItemPrice=snapshot.child(data).child("price").getValue(String.class);

                    totalSum=totalSum+Integer.parseInt(getItemPrice)*Integer.parseInt(getItemQuantity);
                  //  Toast.makeText(Cart.this, getItemPrice, Toast.LENGTH_SHORT).show();
//                    String getShareType=snapshot.child(data).child("typeOfShop").getValue(String.class);
//
//                    if(getShareType.equals("Others")) {
                    i++;
                      userListCart.add(new ModelClassOfCart(getItemImage, getItemName, " ₹ "+getItemPrice, getItemQuantity, getItemName, data.toString(), String.valueOf(i)));
//                    }



                }

                initRecyclerView();
                totalAmountTextView.setText("  ₹ "+String.valueOf(totalSum));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

}