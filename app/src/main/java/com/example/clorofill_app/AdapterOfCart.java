package com.example.clorofill_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterOfCart extends RecyclerView.Adapter<AdapterOfCart.ViewHolder> {

    private List<ModelClassOfCart> userListCart;

    public AdapterOfCart(List<ModelClassOfCart>userListCart){this.userListCart=userListCart;}


    @NonNull
    @Override
    public AdapterOfCart.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design_cart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOfCart.ViewHolder holder, int position) {

        int resource= userListCart.get(position).getImageView1();
        String name=userListCart.get(position).getNameTextView();
        String amount=userListCart.get(position).getAmountTextView();
        String date=userListCart.get(position).getDateTextView();
        String id=userListCart.get(position).getIdTextView();
        String shareId=userListCart.get(position).getShareIdTextView();
        String number=userListCart.get(position).getNumberTextView();

        holder.setData(resource,name,amount,date,id,shareId,number);
        // holder.nameTextView.setBackgroundColor(255);

        if(Integer.parseInt(number.toString())%2==0)
            holder.itemView.setBackgroundColor(Color.parseColor("#CDCFD5"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#838795"));

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

        holder.shareIdTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context u=holder.itemView.getContext();
                Toast.makeText(u, "hy", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return userListCart.size();
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

        public void setData(int resource, String name, String amount, String date, String id,String shareId, String number) {

            imageView.setImageResource(resource);
            nameTextView.setText(name);
            amountTextView.setText(amount);
            dateTextView.setText(date);
            idTextView.setText(id);
            shareIdTextView.setText("REMOVE");
            numberTextView.setText(number);
        }
    }
}
