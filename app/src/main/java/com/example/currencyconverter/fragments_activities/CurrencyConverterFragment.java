package com.example.currencyconverter.fragments_activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currencyconverter.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrencyConverterFragment extends Fragment {

    private TextInputLayout amount_input;
    private CountryCurrencyButton from_btn,to_btn;
    private Button btn_convert;
    private TextView rst;


    public CurrencyConverterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_currency_converter, container, false);

        amount_input = view.findViewById(R.id.amount_input);
        from_btn = view.findViewById(R.id.from_btn);
        to_btn = view.findViewById(R.id.tobtn);
        btn_convert = view.findViewById(R.id.btn_convert);
        rst = view.findViewById(R.id.rst);

        from_btn.setCountry("FR");
        from_btn.setShowCurrency(true);
        to_btn.setCountry("TN");
        to_btn.setShowCurrency(true);

        from_btn.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {

                Toast.makeText(getActivity(), "from country "+country.getCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSelectCurrency(Currency currency) {

            }
        });

        to_btn.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                Toast.makeText(getActivity(), " to country "+country.getCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSelectCurrency(Currency currency) {

            }
        });

        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = amount_input.getEditText().getText().toString();
                String from_code = from_btn.getCountry().getCurrency().getCode();
                String to_code = to_btn.getCountry().getCurrency().getCode();


                Log.w("TAG", "onClick: amount "+amount+"\nfrom_code: "+from_code+"\nto_code : "+to_code);
                if(amount.isEmpty())
                    Toast.makeText(getActivity(), "give me your amount ", Toast.LENGTH_SHORT).show();
                else
                    setCurrencyStr(amount,from_code,to_code);
            }
        });

        return view;
    }

    private void setCurrencyStr(final String amount, final String from_code, final String to_code) {
        String url="https://currency-converter5.p.rapidapi.com/currency/convert?format=json&from="+from_code+"&to="+to_code+"&amount="+amount;
        Ion.with(getActivity()).load(url)
                .setHeader("x-rapidapi-host","currency-converter5.p.rapidapi.com")
                .setHeader("x-rapidapi-key","1eca79ea2fmshb41dd269e4b7f29p16c9b3jsnc398cc5272a7")
                .setHeader("useQueryString","true")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e !=null)
                            Toast.makeText(getActivity(), "error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        else
                        {
                            String rate = result.get("rates").getAsJsonObject().get(to_code).getAsJsonObject().get("rate").getAsString();
                            rst.setText("rst : "+amount+" "+from_code+" = "+rate+" "+to_code);

                        }
                    }
                });
    }
}
