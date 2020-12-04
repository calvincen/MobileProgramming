package com.example.utscalvin2201792796;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CompleteAdapter extends RecyclerView.Adapter<CompleteAdapter.ViewHolder> {
    public ArrayList<Drink> MyOrder;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public CompleteAdapter(ArrayList<Drink> myOrder) {
        this.MyOrder = myOrder;
    }

    @NonNull
    @Override
    public CompleteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.complete_item, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteAdapter.ViewHolder holder, int position) {
        final CardView cardView = holder.cardView;

        TextView nameProduct = cardView.findViewById(R.id.drink_name);
        TextView priceProduct = cardView.findViewById(R.id.drink_price);
        TextView quantityProduct = cardView.findViewById(R.id.drink_qty);

        Drink theDrink = MyOrder.get(position);
        nameProduct.setText("Item : "+theDrink.getName());
        priceProduct.setText("Price : Rp "+Integer.toString(theDrink.getPrice()));
        quantityProduct.setText("Qty : "+Integer.toString(theDrink.getQty()));

    }

    @Override
    public int getItemCount() {
        return MyOrder.size();
    }
}
