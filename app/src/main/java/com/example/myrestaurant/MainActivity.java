package com.example.myrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FrameLayout pizzaFrame;
    FrameLayout saladFrame;
    FrameLayout appetizerFrame;
    FrameLayout dessertFrame;


    //map keys for sharedpreferance file
    public static final String categoryFile = "categoryFile" ;
    public static final String category = "category";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting main page frame images.
        pizzaFrame = findViewById(R.id.pizzaMainPage);
        saladFrame = findViewById(R.id.saladMainPage);
        appetizerFrame = findViewById(R.id.appetizerMainPage);
        dessertFrame = findViewById(R.id.dessertMainPage);


        pizzaFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Choose category of food
                SharedPreferences sharedpreferences = getSharedPreferences(categoryFile, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(category, (1 +""));
                editor.apply();

                // Open next activity
                Intent intent = new Intent(getApplicationContext(), FoodListActivity.class);
                startActivity(intent);


            }
        });

        saladFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Choose category of food
                SharedPreferences sharedpreferences = getSharedPreferences(categoryFile, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(category, (2 +""));
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), FoodListActivity.class);
                startActivity(intent);
            }
        });

        appetizerFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Choose category of food
                SharedPreferences sharedpreferences = getSharedPreferences(categoryFile, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(category, (3 +""));
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), FoodListActivity.class);
                startActivity(intent);
            }
        });

        dessertFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Choose category of food
                SharedPreferences sharedpreferences = getSharedPreferences(categoryFile, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(category, (4 +""));
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), FoodListActivity.class);
                startActivity(intent);
            }
        });


    }
}