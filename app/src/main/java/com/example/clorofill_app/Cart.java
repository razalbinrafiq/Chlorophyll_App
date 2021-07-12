package com.example.clorofill_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassOfCart> userListCart;
    AdapterOfCart adapter;

    int i=1;
    String check_ID;
    String getItemName,getItemQuantity,getItemImage,getItemPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID= loginDetails.getString("id","0");

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

        fb_to_read.addValueEventListener(new ValueEventListener() {
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
                  //  Toast.makeText(Cart.this, getItemPrice, Toast.LENGTH_SHORT).show();
//                    String getShareType=snapshot.child(data).child("typeOfShop").getValue(String.class);
//
//                    if(getShareType.equals("Others")) {
                    i++;
                      userListCart.add(new ModelClassOfCart(R.drawable.ch4, getItemName, " ₹ "+getItemPrice, getItemQuantity, getItemName, data.toString(), String.valueOf(i)));
//                    }



                }

                  initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

}