package com.example.clorofill_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassOfHomePage> userListOfHomePage;
    AdapterOfHomePage adapter;


    private GridLayout mLayout;
    Button addShareButton,test;
    int i=2;
    int num=1;
    int count;
    Context context;
    Button button;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference getRef1,getRef2;
    String sharenameEditText,sharedateEditText,shareamountEditText;
    String numOfShares;
    EditText shareProfit,sharedate,shareamount;
    String getShareName,getCurrentNum;
    String user = null;
    String nameOfShop = null;
    String addTo;



    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbars=findViewById(R.id.toolsbars);
        setSupportActionBar(toolbars);





        drawerLayout = findViewById(R.id.F_drawer_layout);

        NavigationView navigationView = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbars,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        initData("1,","2","2,3","4,","5","67");

    }






    private void initRecyclerView() {

        recyclerView=findViewById(R.id.recyclerViewOfAdminActivity);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AdapterOfHomePage(userListOfHomePage);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(String name,String amount, String date,String id,String shareId, String number) {


        userListOfHomePage = new ArrayList<>();


        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("product");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){


                   // String status=snapshot.child(data).child("status").getValue(String.class);

                    //                mLayout.addView(dmv.slnoTextVIew(getApplicationContext(),"data"),i);
                    //               mLayout.addView(dmv.chittalIDTextView(getApplicationContext(), "f"),i+1);
                    //                mLayout.addView(dmv.nameTextVIew(getApplicationContext(),data),i);


                    String getProductName=snapshot.child(data).child("name").getValue(String.class);
                    String getProductPrice=snapshot.child(data).child("price").getValue(String.class);
                    getCurrentNum=data;
                    String getProductType=snapshot.child(data).child("type").getValue(String.class);
                    String im=snapshot.child(data).child("image").getValue(String.class);
                    String getProductQuantity=snapshot.child(data).child("quantity").getValue(String.class);


                    String postFixTo;

                    SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
                    String type= loginDetails.getString("type","0");

                    if(getProductType.equals("pot")){
                        postFixTo=" grams";


                            i=i+1;

                          //  String im="https://firebasestorage.googleapis.com/v0/b/nodewithfirebase-9ad44.appspot.com/o/Products%2Fcactus-plants-500x500.jpg?alt=media&token=6bda95cc-37f6-44a7-b20b-ea7613f25424";
                            userListOfHomePage.add(new ModelClassOfHomePage(im, getProductName, getProductPrice, getProductQuantity, "3", data.toString(), String.valueOf(i)));



                    }
//                    else {
//                        postFixTo=" %";
//                        if(getShareId.equals(user)){
//
//                            i=i+1;
//
//                            userListOfHomePage.add(new ModelClassOfHomePage(R.drawable.ch4, getShareDate, getShareName, getSharePercentage+postFixTo, "3", data.toString(), String.valueOf(i)));
//
//                        }
//
//
//                    }



                    initRecyclerView();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }



    public void plants(String name,String amount, String date,String id,String shareId, String number){


        userListOfHomePage = new ArrayList<>();


        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("product");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){


                    // String status=snapshot.child(data).child("status").getValue(String.class);

                    //                mLayout.addView(dmv.slnoTextVIew(getApplicationContext(),"data"),i);
                    //               mLayout.addView(dmv.chittalIDTextView(getApplicationContext(), "f"),i+1);
                    //                mLayout.addView(dmv.nameTextVIew(getApplicationContext(),data),i);


                    String getProductName=snapshot.child(data).child("name").getValue(String.class);
                    String getProductPrice=snapshot.child(data).child("price").getValue(String.class);
                    getCurrentNum=data;
                    String getProductType=snapshot.child(data).child("type").getValue(String.class);
                    String getProductQuantity=snapshot.child(data).child("quantity").getValue(String.class);
                    String im=snapshot.child(data).child("image").getValue(String.class);

                    String postFixTo;

                    SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
                    String type= loginDetails.getString("type","0");

                    if(getProductType.equals("plant")){
                        postFixTo=" grams";


                        i=i+1;

                      //  String im="https://firebasestorage.googleapis.com/v0/b/nodewithfirebase-9ad44.appspot.com/o/Products%2Fcactus-plants-500x500.jpg?alt=media&token=6bda95cc-37f6-44a7-b20b-ea7613f25424";
                        userListOfHomePage.add(new ModelClassOfHomePage(im, getProductName, getProductPrice, getProductQuantity, "3", data.toString(), String.valueOf(i)));



                    }
//                    else {
//                        postFixTo=" %";
//                        if(getShareId.equals(user)){
//
//                            i=i+1;
//
//                            userListOfHomePage.add(new ModelClassOfHomePage(R.drawable.ch4, getShareDate, getShareName, getSharePercentage+postFixTo, "3", data.toString(), String.valueOf(i)));
//
//                        }
//
//
//                    }



                    initRecyclerView();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    public void fertilizers(String name,String amount, String date,String id,String shareId, String number){


        userListOfHomePage = new ArrayList<>();


        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("product");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){

                    String getProductName=snapshot.child(data).child("name").getValue(String.class);
                    String getProductPrice=snapshot.child(data).child("price").getValue(String.class);
                    getCurrentNum=data;
                    String getProductType=snapshot.child(data).child("type").getValue(String.class);
                    String getProductQuantity=snapshot.child(data).child("quantity").getValue(String.class);
                    String im=snapshot.child(data).child("image").getValue(String.class);


                    SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
                    String type= loginDetails.getString("type","0");

                    if(getProductType.equals("fertilizers")){


                        i=i+1;
                        userListOfHomePage.add(new ModelClassOfHomePage(im, getProductName, getProductPrice, getProductQuantity, "3", data.toString(), String.valueOf(i)));


                    }

                    initRecyclerView();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void seeds(String name,String amount, String date,String id,String shareId, String number){


        userListOfHomePage = new ArrayList<>();


        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("product");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){

                    String getProductName=snapshot.child(data).child("name").getValue(String.class);
                    String getProductPrice=snapshot.child(data).child("price").getValue(String.class);
                    getCurrentNum=data;
                    String getProductType=snapshot.child(data).child("type").getValue(String.class);
                    String getProductQuantity=snapshot.child(data).child("quantity").getValue(String.class);
                    String im=snapshot.child(data).child("image").getValue(String.class);


                    SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
                    String type= loginDetails.getString("type","0");

                    if(getProductType.equals("seeds")){


                        i=i+1;
                        userListOfHomePage.add(new ModelClassOfHomePage(im, getProductName, getProductPrice, getProductQuantity, "3", data.toString(), String.valueOf(i)));


                    }

                    initRecyclerView();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }





    public void logout(){

        SharedPreferences loginDetails = getSharedPreferences("loginDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginDetails.edit();
        editor.putString("id","0" );
        editor.putString("password","0" );
        editor.putString("mode","0" );
        editor.commit();

        Intent login=new Intent(homepage.this,MainActivity.class);
        finish();
        startActivity(login);
        System.exit(0);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_khome:initData("1,","2","2,3","4,","5","67");
                Toast.makeText(this, "clicked here", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_farmers_registration:fertilizers("1,","2","2,3","4,","5","67");
                Toast.makeText(this, "mail_clicked second irem", Toast.LENGTH_SHORT).show();
               break;

            case R.id.nav_profile:plants("1,","2","2,3","4,","5","67");
                Toast.makeText(this, "clicked here", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_product_to_sell:seeds("1,","2","2,3","4,","5","67");
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_mail:
                Toast.makeText(this, "mail_clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Log:logout();

                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}