package com.example.utscalvin2201792796;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DrinkAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Drink> drinks;

    public DrinkAdapter(Context a, ArrayList<Drink> drinks){
        context = a;
        this.drinks = drinks;
    }

    @Override
    public int getCount() {
        return drinks.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.drink_item , null);
        }

        ImageView imageView = convertView.findViewById(R.id.image_view);
        TextView drinkView = convertView.findViewById(R.id.drink_view);
        TextView priceView = convertView.findViewById(R.id.drinkPrice_view);

        Drink the_drink = drinks.get(position);
        imageView.setImageResource(the_drink.getImage());
        drinkView.setText(the_drink.getName());
        priceView.setText("Rp " + Integer.toString(the_drink.getPrice()));
        return convertView;
    }
}
