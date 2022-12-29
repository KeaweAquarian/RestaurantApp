package com.example.myrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ImageView imagePicture;
    TextView itemTitle;
    TextView itemDescription;
    TextView itemPrice;
    Button submitBtn;
    EditText quantity;
    public static final String sharedPrefs = "PrefsFile" ;
    public static final String updateQuantity = "updateQuantity";
    public static final String categoryFile = "categoryFile" ;
    public static final String category = "category";
    private static final String json_Key = "jsonKey";
    public static ArrayList<String> itemsOrdered;
    Double quantityChosen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        getSupportActionBar().hide();

        loadDate();



        imagePicture = findViewById(R.id.itemPicture);
        itemTitle = findViewById(R.id.itemTitle);
        itemDescription = findViewById(R.id.itemDescription);
        itemPrice = findViewById(R.id.itemPrice);
        submitBtn = findViewById(R.id.submitBtn);
        quantity = findViewById(R.id.quantity);

        Intent intent = getIntent();
        int intValue = intent.getIntExtra("item", 0);
        SharedPreferences sharedpreferences = getSharedPreferences(categoryFile, MODE_PRIVATE);
        int categoryValue = Integer.parseInt(sharedpreferences.getString(category, "0"));
        if (categoryValue == 1) {
            switch (intValue) {
                case 1:
                    imagePicture.setImageResource(R.drawable.pizza);
                    itemPrice.setText("16.75");
                    itemTitle.setText("Cheese Pizza");
                    itemDescription.setText("Truly original, our cheese pizza is made with the finest Italian tomato sauce, and 3 high quality cheeses");

                    break;
                case 2:
                    imagePicture.setImageResource(R.drawable.greekpizzasmall);
                    itemPrice.setText("17.75");
                    itemTitle.setText("Greek Pizza");
                    itemDescription.setText("Rich feta, fresh peppers and olives. This is a pizza everyone will love.");

                    break;
                case 3:
                    imagePicture.setImageResource(R.drawable.veggiepizzasmall);
                    itemPrice.setText("18.75");
                    itemTitle.setText("Veggie Pizza");
                    itemDescription.setText("Packed with veggies and  made with the finest Italian tomato sauce, and high quality cheeses");

                    break;
                case 4:
                    imagePicture.setImageResource(R.drawable.supreme);
                    itemPrice.setText("19.75");
                    itemTitle.setText("Supreme Pizza");
                    itemDescription.setText("Chunky vegan sausage, fresh pappers, savory mushrooms. A pizza fit for a king.");

                    break;
                default:
                    break;
            }
        }else if (categoryValue == 2) {
                switch (intValue) {
                    case 1:
                        imagePicture.setImageResource(R.drawable.boccancini);
                        itemPrice.setText("12.75");
                        itemTitle.setText("Boccancini Salad");
                        itemDescription.setText("Light and fresh, full of vine ripe cherry tomatoes and fresh boccancini.");

                        break;
                    case 2:
                        imagePicture.setImageResource(R.drawable.feta);
                        itemPrice.setText("13.75");
                        itemTitle.setText("Greek Salad");
                        itemDescription.setText("Rich feta, fresh peppers, olives and farmers lettuce .");

                        break;
                    case 3:
                        imagePicture.setImageResource(R.drawable.green);
                        itemPrice.setText("14.75");
                        itemTitle.setText("Green Salad");
                        itemDescription.setText("Healthy broccoli, green cauliflower and farmers lettuce.");

                        break;
                    case 4:
                        imagePicture.setImageResource(R.drawable.spinach);
                        itemPrice.setText("14.75");
                        itemTitle.setText("Spinach Salad");
                        itemDescription.setText("Full of calcium, our rich spinach salad will please everyone.");

                        break;
                    default:
                        break;
                }

        }else if (categoryValue == 3) {
                switch (intValue) {
                    case 1:
                        imagePicture.setImageResource(R.drawable.arancini);
                        itemPrice.setText("9.75");
                        itemTitle.setText("Arancini");
                        itemDescription.setText("Rich aborial rice balls, deep fried and stuffed with cheese.");

                        break;
                    case 2:
                        imagePicture.setImageResource(R.drawable.grilledhalumi);
                        itemPrice.setText("13.75");
                        itemTitle.setText("Grilled Halumi");
                        itemDescription.setText("Smokey, cheesy and oh so oohy gooey.");

                        break;
                    case 3:
                        imagePicture.setImageResource(R.drawable.tomatosoup);
                        itemPrice.setText("8.75");
                        itemTitle.setText("Tomato Soup");
                        itemDescription.setText("A homey classic rich with just a hint of sweetness.");

                        break;
                    case 4:
                        imagePicture.setImageResource(R.drawable.mushroom);
                        itemPrice.setText("11.75");
                        itemTitle.setText("Sauteed Mushrooms");
                        itemDescription.setText("Rich and smokey, with earthy tones of the countryside.");

                        break;
                    default:
                        break;
                }
            }else         if (categoryValue == 4) {
                switch (intValue) {
                    case 1:
                        imagePicture.setImageResource(R.drawable.chocolatecake);
                        itemPrice.setText("12.75");
                        itemTitle.setText("Chocolate Cake");
                        itemDescription.setText("Rich and decadent, this dessert will live in your dreams for years to come.");

                        break;
                    case 2:
                        imagePicture.setImageResource(R.drawable.gelato);
                        itemPrice.setText("11.75");
                        itemTitle.setText("Gelato");
                        itemDescription.setText("Light and refreshing, in chocolate, vanilla or strawberry flavor.");

                        break;
                    case 3:
                        imagePicture.setImageResource(R.drawable.brulee);
                        itemPrice.setText("13.75");
                        itemTitle.setText("Creme Brulee");
                        itemDescription.setText("Traditional French dessert, a perfect balance of textures and flavors.");

                        break;
                    case 4:
                        imagePicture.setImageResource(R.drawable.applepie);
                        itemPrice.setText("10.75");
                        itemTitle.setText("Apple Pie");
                        itemDescription.setText("Sweet sugar and cinnamon covered apple chunks with flaky crust, classic perfection.");

                        break;
                    default:
                        break;
                }
            }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             try {
                 if (!quantity.getText().toString().equals("0")) {
                     saveData();
                     Toast.makeText(DetailActivity.this, "Order added.", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(getApplicationContext(), FoodListActivity.class);
                     intent.putExtra("key",intValue);
                     startActivity(intent);

                 }else {
                     Toast.makeText(DetailActivity.this, "Please enter a quantity greater than zero.", Toast.LENGTH_SHORT).show();
                 }

             } catch (Exception e){
                 Toast.makeText(DetailActivity.this, "Please enter a quantity!", Toast.LENGTH_SHORT).show();
             }

            }
        });
    }
    public void saveData() {
        quantityChosen = (Double.parseDouble(itemPrice.getText().toString()) * Double.parseDouble(quantity.getText().toString()));
        SharedPreferences sharedpreferences = getSharedPreferences(sharedPrefs, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(updateQuantity, (quantityChosen +""));
        if (itemsOrdered.contains(itemTitle.getText().toString())) {
            double updatedItemTotal = Double.parseDouble(quantity.getText().toString()) + (Double.parseDouble(sharedpreferences.getString(itemTitle.getText().toString() + "Quantity", "0.0")));
            editor.putString(itemTitle.getText().toString() + "Quantity", updatedItemTotal +"") ;

        }else {
            itemsOrdered.add(itemTitle.getText().toString());

            Gson gson = new Gson();
            String jsonString = gson.toJson(itemsOrdered);
            editor.putString(json_Key, jsonString);


            editor.putString(itemTitle.getText().toString() + "Quantity", quantity.getText().toString()) ;
        }

        editor.putString((itemTitle.getText().toString() + "Price"), (itemPrice.getText().toString()));
        editor.commit();
    }
    public void loadDate() {

        SharedPreferences sharedpreferences = getSharedPreferences(sharedPrefs, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonString = sharedpreferences.getString(json_Key, "");

        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        itemsOrdered = gson.fromJson(jsonString, type);

        if (itemsOrdered == null) {
            itemsOrdered = new ArrayList<String>();
        }
    }
}