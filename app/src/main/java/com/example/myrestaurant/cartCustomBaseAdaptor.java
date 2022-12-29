package com.example.myrestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class cartCustomBaseAdaptor extends BaseAdapter {

    Context context;
    ArrayList<String> cartItems;
    ArrayList <Double> num;
    ArrayList<Double> itemPrice;
    ArrayList<Double> itemsPriceTotal;
    LayoutInflater inflater;

    public cartCustomBaseAdaptor(Context cxt, ArrayList<String> cartItems, ArrayList<Double> num, ArrayList<Double> itemPrice, ArrayList<Double> itemsPriceTotal) {
           this.context = cxt;
           this.cartItems = cartItems;
           this.num = num;
           this.itemPrice = itemPrice;
           this.itemsPriceTotal = itemsPriceTotal;
           inflater = LayoutInflater.from(cxt);
    }
    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_cart_list_view, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.item);
        TextView txtView1 = (TextView) convertView.findViewById(R.id.num);
        TextView txtView2 = (TextView) convertView.findViewById(R.id.price);
        TextView txtView3 = (TextView) convertView.findViewById(R.id.total);
        txtView.setText(cartItems.get(i));
        txtView1.setText(num.get(i) + "");
        txtView2.setText(itemPrice.get(i) + "");
        txtView3.setText(itemsPriceTotal.get(i) + "");
        return convertView;
    }
}
