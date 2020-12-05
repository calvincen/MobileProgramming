package com.example.utscalvin2201792796;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {
    private long timePressed;
    GridView drinkGrid;
    ArrayList<Drink> drinks = new ArrayList<>();
    ArrayList<Drink> MyOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        //Toolbar
        Toolbar toolbar = findViewById(R.id.drinkToolbar);
        setSupportActionBar(toolbar);

        //nerima data dari yg lain
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        MyOrder =  (ArrayList<Drink>) bundle.getSerializable("my_order");

        //untuk gridView
        init();
        drinkGrid = findViewById(R.id.gridView_Drink);
        DrinkAdapter adapter = new DrinkAdapter(DrinkActivity.this, drinks);
        drinkGrid.setAdapter(adapter);

        drinkGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              Toast.makeText(getApplicationContext(), "Clicked this" + drinks.get(position).getName(), Toast.LENGTH_SHORT).show();
                Drink the_drink = drinks.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getBaseContext(), OrderActivity.class);
                bundle.putSerializable("Order", the_drink);
                bundle.putSerializable("my_order",MyOrder);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void init(){
        drinks.add(new Drink("Mineral Water", 5000, R.drawable.mineral_glass));
        drinks.add(new Drink("Orange Juice", 12000, R.drawable.juice_orange));
        drinks.add(new Drink("Apple Juice", 10000, R.drawable.juice_apple));
        drinks.add(new Drink("Avocado Juice", 2000, R.drawable.juice_avocado));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.myorder){

            if(MyOrder.isEmpty()){
                Toast.makeText(getApplicationContext(), "Order is still empty!", Toast.LENGTH_SHORT).show();
            }
            else{
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getBaseContext(), MyOrderActivity.class);
                bundle.putSerializable("my_order",MyOrder);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (timePressed + 2000 > System.currentTimeMillis()) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), "Press back again to main!", Toast.LENGTH_SHORT).show();
        }
        timePressed = System.currentTimeMillis();
    }
}