package com.example.currencyconverter.fragments_activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.currencyconverter.R;
import com.example.currencyconverter.adapters.AvailableCurrencyAdapter;
import com.example.currencyconverter.model.CurrencyModel;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class AvailableCurrencyFragment extends Fragment {

    private ListView list;
    private AvailableCurrencyAdapter adapter;
    private ArrayList<CurrencyModel> array;

    public AvailableCurrencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_available_currency, container, false);

        list = view.findViewById(R.id.list);
        setAvailableCurrencyList();


        return view;
    }

    private void setAvailableCurrencyList() {
        String url="https://currency-converter5.p.rapidapi.com/currency/list";
        Ion.with(this).load(url)
                .setHeader("x-rapidapi-host","currency-converter5.p.rapidapi.com")
                .setHeader("x-rapidapi-key","1eca79ea2fmshb41dd269e4b7f29p16c9b3jsnc398cc5272a7")
                .setHeader("useQueryString","true")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e != null)
                            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        else
                        {
                            JsonObject currencies = result.get("currencies").getAsJsonObject();
                            array = new ArrayList<>();

                            Log.w("TAG", "onCompleted: entrySet "+currencies.entrySet() );
                            for(Map.Entry<String, JsonElement> entry : currencies.entrySet()) {
                                String key =  entry.getKey();
                                String value =  entry.getValue().toString();

                                array.add(new CurrencyModel(key,value));
                            }

                            if(array.size()>0)
                            {
                                adapter = new AvailableCurrencyAdapter(getActivity(),array);
                                list.setAdapter(adapter);
                            }
                            else
                                Toast.makeText(getActivity(), "Empty List", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }
}
