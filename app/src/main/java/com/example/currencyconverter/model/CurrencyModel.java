package com.example.currencyconverter.model;

public class CurrencyModel {
    private String name;
    private String total_name;
    private String rate;

    public CurrencyModel(String name, String total_name) {
        this.name = name;
        this.total_name = total_name;
    }

    public CurrencyModel(String name, String total_name, String rate) {
        this.name = name;
        this.total_name = total_name;
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal_name() {
        return total_name;
    }

    public void setTotal_name(String total_name) {
        this.total_name = total_name;
    }
}
