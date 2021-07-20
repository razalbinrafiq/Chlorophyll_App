package com.example.clorofill_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyOrders extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassOfMyOrders> userListMyOrders;
    AdapterOfMyOrders adapter;

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference getRef1,getRef2;



    int i=1;
    int num;
    int totalSum=0;
    String check_ID;
    String getItemName,getItemQuantity,getItemImage,getItemPrice;
    String iName,iPrice,iQuantity,iBookingStatus,iImage;
    String orderCountString;
    int orderCountInt;

    String idString,nameString,emailString,phoneString,addHN,addA,addC,addP;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);


        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID= loginDetails.getString("id","0");



//
//        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("users/"+check_ID+"/myOrders");
//
//        fb_to_read.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                List<String> list=new ArrayList<String>();
//                for (DataSnapshot dsp : snapshot.getChildren()){
//                    list.add(String.valueOf(dsp.getKey()));
//                }
//
//                for(final String data:list){
//
//
//
//
//
//                }
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        initData("1,","2","2,3","4,","5","67");

    }





    private void initRecyclerView() {

        recyclerView=findViewById(R.id.recyclerViewMyOrders);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AdapterOfMyOrders(userListMyOrders);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(String name,String amount, String date,String id,String shareId, String number) {


        userListMyOrders = new ArrayList<>();



        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("users/"+check_ID+"/myOrders");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){



                    DatabaseReference fb_to = FirebaseDatabase.getInstance().getReference("order/list/"+data);

                    fb_to.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            for (DataSnapshot dsp : snapshot2.getChildren()){
                                idString=snapshot2.getKey().toString();
                            }

                            iName=snapshot2.child("item").getValue(String.class);
                            iPrice=snapshot2.child("price").getValue(String.class);
                            iQuantity=snapshot2.child("quantity").getValue(String.class);
                            iBookingStatus=snapshot2.child("bookingStatus").getValue(String.class);
                            iImage=snapshot2.child("image").getValue(String.class);

//                            addHN=snapshot.child("addressHouseName").getValue(String.class);
//                            addA=snapshot.child("addressArea").getValue(String.class);
//                            addC=snapshot.child("addressCity").getValue(String.class);
//                            addP=snapshot.child("addressPin").getValue(String.class);



                            i++;
                            getItemImage="https://firebasestorage.googleapis.com/v0/b/nodewithfirebase-9ad44.appspot.com/o/Products%2FcardImg5.jpg?alt=media&token=b7656307-8b10-4bac-9cc5-25a6fa234c7b";

                            userListMyOrders.add(new ModelClassOfMyOrders(iImage, iName, " ₹ "+iPrice, "QTY:  "+iQuantity, iBookingStatus, data.toString(), String.valueOf(i)));
                            initRecyclerView();


                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });







                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {

        Intent i=new Intent(MyOrders.this,homepage.class);
        finish();
        startActivity(i);


    }


}