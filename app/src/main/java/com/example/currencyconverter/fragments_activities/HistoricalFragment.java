package com.example.currencyconverter.fragments_activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currencyconverter.R;
import com.example.currencyconverter.adapters.HistoricalCurrencyAdapter;
import com.example.currencyconverter.model.CurrencyModel;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoricalFragment extends Fragment {

    private ListView list;
    private HistoricalCurrencyAdapter adapter;
    private TextView txt,amount;
    private ArrayList<CurrencyModel> array;
    public HistoricalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_historical, container, false);

        list = view.findViewById(R.id.list);
        txt = view.findViewById(R.id.base_currency);
        amount = view.findViewById(R.id.rate);

        setHitoricalListData();

        return view;
    }

    private void setHitoricalListData() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        String date = year+"-"+month+"-"+day;

        String url = "https://currency-converter5.p.rapidapi.com/currency/historical/"+date;
        Ion.with(getActivity())
                .load(url)
                .setHeader("x-rapidapi-host","currency-converter5.p.rapidapi.com")
                .setHeader("x-rapidapi-key","1eca79ea2fmshb41dd269e4b7f29p16c9b3jsnc398cc5272a7")
                .setHeader("useQueryString","true")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e != null)
                            Toast.makeText(getActivity(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        else
                        {
                            String base_currency = result.get("base_currency_name").getAsString();
                            String base_amount = result.get("amount").getAsString();
                            txt.setText("base_currency_name : "+base_currency);
                            amount.setText("amount : "+base_amount);


                            JsonObject rates = result.get("rates").getAsJsonObject();
                            array = new ArrayList<>();
                            Log.w("TAG", "onCompleted: keys : "+rates.keySet() );

                            for (String key : rates.keySet())
                            {
                                JsonObject json_item = rates.get(key).getAsJsonObject();
                                String currency_name = json_item.get("currency_name").getAsString();
                                String currency_rate = json_item.get("rate").getAsString();
                                array.add(new CurrencyModel(key,currency_name,currency_rate));
                            }

                            if(array.size()<0)
                                Toast.makeText(getActivity(), "Empty list", Toast.LENGTH_SHORT).show();
                            else
                            {
                                adapter = new HistoricalCurrencyAdapter(getActivity(),array);
                                list.setAdapter(adapter);
                            }
                        }
                    }
                });
    }
}
