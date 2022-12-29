package com.example.myrestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myrestaurant.R;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String listItems [];
    int listIcons[];
    LayoutInflater inflater;
    public  CustomBaseAdapter(Context ctx, String [] items, int [] icons) {
        this.context = ctx;
        this.listItems = items;
        this.listIcons = icons;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {

        return listItems.length;
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
        convertView = inflater.inflate(R.layout.custom_list_view, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.textView);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageIcon);
        txtView.setText(listItems[i]);
        image.setImageResource(listIcons[i]);

        return convertView;
    }
}
