package com.example.myrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FoodListActivity extends AppCompatActivity {

    TextView total;
    String[] itemChoice = new String[4];
    int iconChoice[] = new int[4];
    ListView listView;
    public static final String sharedPrefs = "PrefsFile" ;
    public static final String categoryFile = "categoryFile" ;
    public static final String category = "category";
    public static final String storedTotal = "quantityItem";
    public static final String orderIncrease = "updateQuantity";
    Button seeCartbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().hide();

        seeCartbtn = findViewById(R.id.seeCart);
        seeCartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent3);
            }
        });

        //setting and updating stored total.
        total = findViewById(R.id.total);
        SharedPreferences sharedpreferences = getSharedPreferences(sharedPrefs, MODE_PRIVATE);
        SharedPreferences sharedpreferences2 = getSharedPreferences(categoryFile, MODE_PRIVATE);

        double currentTotal = (Double.parseDouble(sharedpreferences.getString(storedTotal, "0.0")) + Double.parseDouble(sharedpreferences.getString(orderIncrease, "0.0"))) ;

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(storedTotal, (currentTotal +""));
        editor.commit();

        //clear updateQuantity after it is added to stored quantity
        SharedPreferences settings = this.getSharedPreferences(sharedPrefs, Context.MODE_PRIVATE);
        settings.edit().remove(orderIncrease).commit();

        //Set the total bar.
        total.setText(currentTotal + "");

        //Integer value for category of food.
        int categoryValue = Integer.parseInt(sharedpreferences2.getString(category, "0"));

        switch (categoryValue) {
            case 1:
                itemChoice[0] = "Cheese";
                itemChoice[1] = "Greek";
                itemChoice[2] = "Veggie";
                itemChoice[3] = "Supreme";
                iconChoice[0] = R.drawable.cheesepizzasmall;
                iconChoice[1] = R.drawable.greekpizzasmall;
                iconChoice[2] = R.drawable.veggiepizzasmall;
                iconChoice[3] = R.drawable.supreme;


                break;
            case 2:
                itemChoice[0] = "Bocconcini";
                itemChoice[1] = "Greek";
                itemChoice[2] = "Green";
                itemChoice[3] = "Spinach";
                iconChoice[0] = R.drawable.boccancini;
                iconChoice[1] = R.drawable.feta;
                iconChoice[2] = R.drawable.green;
                iconChoice[3] = R.drawable.spinach;
                break;
            case 3:
                itemChoice[0] = "Arancini";
                itemChoice[1] = "Grilled Halumi";
                itemChoice[2] = "Tomato Soup";
                itemChoice[3] = "Sautee Mushrooms";
                iconChoice[0] = R.drawable.arancini;
                iconChoice[1] = R.drawable.grilledhalumi;
                iconChoice[2] = R.drawable.tomatosoup;
                iconChoice[3] = R.drawable.mushroom;
                break;
            case 4:
                itemChoice[0] = "Chocolate Cake";
                itemChoice[1] = "Gelato";
                itemChoice[2] = "Creme Brulee";
                itemChoice[3] = "Apple Pie";
                iconChoice[0] = R.drawable.cake;
                iconChoice[1] = R.drawable.gelato;
                iconChoice[2] = R.drawable.brulee;
                iconChoice[3] = R.drawable.applepie;

                break;
            default:
                break;
        }

        listView = (ListView) findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),itemChoice, iconChoice);
        listView.setAdapter(customBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent1 = new Intent(FoodListActivity.this, DetailActivity.class);
                        intent1.putExtra("item", 1);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(FoodListActivity.this, DetailActivity.class);
                        intent2.putExtra("item", 2);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(FoodListActivity.this, DetailActivity.class);
                        intent3.putExtra("item", 3);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(FoodListActivity.this, DetailActivity.class);
                        intent4.putExtra("item", 4);
                        startActivity(intent4);
                        break;
                    default:
                        break;
                }
            }
        });

    }
    @Override
    public void onResume()
    {
        super.onResume();
        //Refresh your stuff here
        SharedPreferences sharedpreferences = getSharedPreferences(sharedPrefs, MODE_PRIVATE);

        total.setText(sharedpreferences.getString(storedTotal, "0.0"));
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent2);
    }
}