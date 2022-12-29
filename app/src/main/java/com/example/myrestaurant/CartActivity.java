package com.example.myrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ArrayList<Double> itemNum = new ArrayList<>();
    ArrayList<Double> itemPrice= new ArrayList<>();
    ArrayList<Double> itemsTotal= new ArrayList<>();
    ListView listView;
    Button clearbtn;
    TextView total;
    //keys for sharedpreferances.
    public static final String sharedPrefs = "PrefsFile" ;
    public static final String storedQuantity = "quantityItem";
    private static final String json_Key = "jsonKey";
    public static ArrayList<String> itemsOrdered = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().hide();

        loadDate();

        //Button to clear cart.
        clearbtn = findViewById(R.id.clearbtn);
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear items price and quantity
                SharedPreferences sharedpreferences = getSharedPreferences(sharedPrefs, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
               //clear total
                total.setText(0.0 + "");
                //Clear items list
                itemsOrdered.clear();

            }
        });
       //Take values in shared preferences and put them in ArrayList to add to custom ListView
      for  (int i = 0; i < itemsOrdered.size(); i ++) {
          SharedPreferences sharedpreferences = getSharedPreferences(sharedPrefs, MODE_PRIVATE);
          itemNum.add(Double.parseDouble(sharedpreferences.getString(itemsOrdered.get(i) + "Quantity", null)));
          itemPrice.add(Double.parseDouble(sharedpreferences.getString(itemsOrdered.get(i) + "Price", null)));
          itemsTotal.add(Double.parseDouble(itemNum.get(i).toString()) * Double.parseDouble(itemPrice.get(i).toString()));

        }

              listView = (ListView) findViewById(R.id.cartListview);
              cartCustomBaseAdaptor cartCustomBaseAdapter = new cartCustomBaseAdaptor(getApplicationContext(), itemsOrdered, itemNum, itemPrice, itemsTotal);
              listView.setAdapter(cartCustomBaseAdapter);



          //Create the total at bottom of screen.
        total = findViewById(R.id.total);
        SharedPreferences sharedpreferences = getSharedPreferences(sharedPrefs, MODE_PRIVATE);
        double currentTotal = (Double.parseDouble(sharedpreferences.getString(storedQuantity, "0.0"))) ;

        total.setText(currentTotal + "");


    }
    public void loadDate() {
        SharedPreferences sharedpreferences = getSharedPreferences(sharedPrefs, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonString = sharedpreferences.getString(json_Key, "");

        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        itemsOrdered= gson.fromJson(jsonString, type);

        if (itemsOrdered == null) {
            itemsOrdered = new ArrayList<>();
        }
    }
}