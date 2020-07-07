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

public class HistoricalCurrencyAdapter extends ArrayAdapter<CurrencyModel> {

    public HistoricalCurrencyAdapter(@NonNull Context context, @NonNull ArrayList<CurrencyModel> objects) {
        super(context, R.layout.historical_currency_model, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.historical_currency_model,parent,false);

        TextView name , total;
        name = convertView.findViewById(R.id.name);
        total = convertView.findViewById(R.id.rate);

        CurrencyModel item = getItem(position);
        name.setText(item.getTotal_name()+" , "+item.getName());
        total.setText("rate : "+item.getRate());


        return convertView;
    }
}
