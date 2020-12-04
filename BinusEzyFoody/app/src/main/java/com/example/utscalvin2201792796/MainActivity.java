package com.example.utscalvin2201792796;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private long timePressed;
    ArrayList<Drink> MyOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.theToolbar);
        setSupportActionBar(toolbar);


        Button drinkButton = (Button) findViewById(R.id.drinkBtn);
        drinkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                bundle.putSerializable("my_order", MyOrder);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button mapsButton = (Button) findViewById(R.id.locationBtn);
        mapsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent2);
            }
        });
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
            Toast.makeText(getApplicationContext(), "Order is still empty!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (timePressed + 1000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit!", Toast.LENGTH_SHORT).show();
        }
        timePressed = System.currentTimeMillis();
    }
}
