package com.example.currencyconverter.adapters;

import com.example.currencyconverter.fragments_activities.AvailableCurrencyFragment;
import com.example.currencyconverter.fragments_activities.CurrencyConverterFragment;
import com.example.currencyconverter.fragments_activities.HistoricalFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                return new CurrencyConverterFragment();
            case 1:
                return new HistoricalFragment();
            case 2 :
                return new AvailableCurrencyFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
