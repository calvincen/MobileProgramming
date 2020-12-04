package com.example.utscalvin2201792796;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ArrayList<Drink> MyOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //untuk toolbar
        Toolbar toolbar = findViewById(R.id.orderToolbar);
        setSupportActionBar(toolbar);
        //nerima data intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final Drink the_drink =  (Drink) bundle.getSerializable("Order");
        MyOrder =  (ArrayList<Drink>) bundle.getSerializable("my_order");

        TextView productName = findViewById(R.id.name_product);
        TextView productPrice = findViewById(R.id.price_product);
        ImageView imageView = findViewById(R.id.pict_product);

        productName.setText("Product Name : " + the_drink.getName());
        productPrice.setText("Product Price : Rp " + the_drink.getPrice());
        imageView.setImageResource(the_drink.getImage());

        Button orderMore = (Button) findViewById(R.id.orderMoreBtn);
        orderMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, DrinkActivity.class));
                EditText editText = (EditText) findViewById(R.id.qty_input);
                int qty = Integer.parseInt(editText.getText().toString());

                if(qty > 0){
                    MyOrder.add(new Drink(the_drink.getName(), the_drink.getPrice(), the_drink.getImage(), qty));

                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(OrderActivity.this, DrinkActivity.class);
                    bundle.putSerializable("my_order", MyOrder);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please input quantity more than 0!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getBaseContext(), DrinkActivity.class);
        bundle.putSerializable("my_order",MyOrder);

        intent.putExtras(bundle);
        startActivity(intent);
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
}

