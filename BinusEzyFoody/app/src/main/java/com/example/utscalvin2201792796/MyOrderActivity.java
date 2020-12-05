package com.example.utscalvin2201792796;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {
    ArrayList<Drink> MyOrder = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayout;
    TheOrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.myOrderToolbar);
        setSupportActionBar(toolbar);

        //nerima data intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        MyOrder =  (ArrayList<Drink>) bundle.getSerializable("my_order");

        TextView totalPrice = findViewById(R.id.totalprice);
        totalPrice.setText("Total Price : Rp " + getTotalPrice());

        recyclerView = findViewById(R.id.order_recycler);
        createRecycler();

        Button orderComplete = (Button) findViewById(R.id.orderCompleteBtn);
        orderComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyOrder.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Order is still empty! Please make order first!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(MyOrderActivity.this, Complete.class);
                    bundle.putSerializable("my_order", MyOrder);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private int getTotalPrice(){
        int totalPrice = 0;
        for(Drink drink : MyOrder){
            totalPrice += (drink.getQty() * drink.getPrice());
        }
        return totalPrice;
    }

    private void createRecycler(){
        recyclerView.setHasFixedSize(true);

        linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);

        orderAdapter = new TheOrderAdapter(MyOrder);
        recyclerView.setAdapter(orderAdapter);
    }

    @Override
    public void onBackPressed() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getBaseContext(), DrinkActivity.class);
        bundle.putSerializable("my_order",MyOrder);

        intent.putExtras(bundle);
        startActivity(intent);
    }
}