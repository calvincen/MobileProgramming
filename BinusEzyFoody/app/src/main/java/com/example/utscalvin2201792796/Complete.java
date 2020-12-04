package com.example.utscalvin2201792796;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Complete extends AppCompatActivity {
    ArrayList<Drink> MyOrder = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayout;
    CompleteAdapter completeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.completeToolbar);
        setSupportActionBar(toolbar);

        //nerima data intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        MyOrder =  (ArrayList<Drink>) bundle.getSerializable("my_order");

        TextView totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText("Total Price : Rp " + getTotalPrice());

        recyclerView = findViewById(R.id.order_recycler);
        createRecycler();

        Button orderComplete = (Button) findViewById(R.id.orderCompleteBtn);
        orderComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Complete.this, MainActivity.class);
                startActivity(intent);
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

        completeAdapter = new CompleteAdapter(MyOrder);
        recyclerView.setAdapter(completeAdapter);
    }
}