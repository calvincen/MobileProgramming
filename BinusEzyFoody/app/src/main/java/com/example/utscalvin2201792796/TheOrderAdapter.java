package com.example.utscalvin2201792796;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TheOrderAdapter extends RecyclerView.Adapter<TheOrderAdapter.ViewHolder> {
    public ArrayList<Drink> MyOrder;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public TheOrderAdapter(ArrayList<Drink> myOrder) {
        this.MyOrder = myOrder;
    }

    @NonNull
    @Override
    public TheOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.myorder_item, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull TheOrderAdapter.ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;

        TextView nameProduct = cardView.findViewById(R.id.drink_name);
        TextView priceProduct = cardView.findViewById(R.id.drink_price);
        TextView quantityProduct = cardView.findViewById(R.id.drink_qty);

        Drink theDrink = MyOrder.get(position);
        nameProduct.setText("Item : "+theDrink.getName());
        priceProduct.setText("Price : Rp "+Integer.toString(theDrink.getPrice()));
        quantityProduct.setText("Qty : "+Integer.toString(theDrink.getQty()));


        Button deleteBtn =cardView.findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drink toDelete = MyOrder.get(position);
                MyOrder.remove(toDelete);

                Bundle bundle = new Bundle();
                bundle.putSerializable("my_order", MyOrder);


                Intent intent = new Intent(cardView.getContext(), MyOrderActivity.class);
                intent.putExtras(bundle);
                cardView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return MyOrder.size();
    }


}
