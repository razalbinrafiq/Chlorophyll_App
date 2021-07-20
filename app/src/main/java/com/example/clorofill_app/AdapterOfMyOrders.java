package com.example.clorofill_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterOfMyOrders extends RecyclerView.Adapter<AdapterOfMyOrders.ViewHolder> {

    private List<ModelClassOfMyOrders> userListMyOrders;

    public AdapterOfMyOrders(List<ModelClassOfMyOrders>userListMyOrders){this.userListMyOrders=userListMyOrders;}


    @NonNull
    @Override
    public AdapterOfMyOrders.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design_my_orders,parent,false);
        return new AdapterOfMyOrders.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOfMyOrders.ViewHolder holder, int position) {

        String  resource= userListMyOrders.get(position).getImageView1();
        String name=userListMyOrders.get(position).getNameTextView();
        String amount=userListMyOrders.get(position).getAmountTextView();
        String date=userListMyOrders.get(position).getDateTextView();
        String id=userListMyOrders.get(position).getIdTextView();
        String shareId=userListMyOrders.get(position).getShareIdTextView();
        String number=userListMyOrders.get(position).getNumberTextView();

        holder.setData(resource,name,amount,date,id,shareId,number);
        // holder.nameTextView.setBackgroundColor(255);

        if(Integer.parseInt(number.toString())%2==0)
            holder.itemView.setBackgroundColor(Color.parseColor("#f7f7f0"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#fcfcfc"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.nameTextView.setText("UI");
//                Context u=holder.itemView.getContext();
//                Intent iu=new Intent(u,ShareDetails.class);
//                iu.putExtra("path",shareId);
//                u.startActivity(iu);
//                Toast.makeText(u, holder.shareIdTextView.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        holder.shareIdTextView.setText(id);



    }

    @Override
    public int getItemCount() {
        return userListMyOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView nameTextView;
        private TextView amountTextView;
        private TextView dateTextView;
        private TextView idTextView;
        private TextView shareIdTextView;
        private TextView numberTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView1);
            nameTextView=itemView.findViewById(R.id.nameTextView);
            amountTextView=itemView.findViewById(R.id.amountTextView);
            dateTextView=itemView.findViewById(R.id.dateTextView);
            idTextView=itemView.findViewById(R.id.idTextView);
            shareIdTextView=itemView.findViewById(R.id.shareIdTextView);
            numberTextView=itemView.findViewById(R.id.numberTextView);

        }

        public void setData(String resource, String name, String amount, String date, String id,String shareId, String number) {

            Picasso.get().load(resource).fit().centerCrop().into(imageView);
            nameTextView.setText(name);
            amountTextView.setText(amount);
            dateTextView.setText(date);
            idTextView.setText(id);
            shareIdTextView.setText("REMOVE");
            numberTextView.setText(number);
        }
    }
}
