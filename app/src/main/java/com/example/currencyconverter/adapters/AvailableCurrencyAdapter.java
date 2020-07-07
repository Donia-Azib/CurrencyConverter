package com.example.currencyconverter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.currencyconverter.R;
import com.example.currencyconverter.model.CurrencyModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AvailableCurrencyAdapter extends ArrayAdapter<CurrencyModel> {

    public AvailableCurrencyAdapter(@NonNull Context context, @NonNull ArrayList<CurrencyModel> objects) {
        super(context, R.layout.available_currency_model, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.available_currency_model,parent,false);

        TextView name , total;
        name = convertView.findViewById(R.id.name);
        total = convertView.findViewById(R.id.desc);

        CurrencyModel item = getItem(position);
        name.setText(item.getName());
        total.setText(item.getTotal_name());


        return convertView;
    }
}
