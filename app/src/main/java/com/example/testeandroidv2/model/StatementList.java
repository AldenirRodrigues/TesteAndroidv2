
package com.example.testeandroidv2.model;

import com.example.testeandroidv2.presenter.ExtratPresenter;
import com.google.gson.annotations.SerializedName;

import java.text.NumberFormat;
import java.util.Locale;

public class StatementList {

    @SerializedName("date")
    private String data;
    @SerializedName("desc")
    private String desc;
    @SerializedName("title")
    private String title;
    @SerializedName("value")
    public String mValue;
    private Locale locale = new Locale("pt", "BR");
    private NumberFormat real = NumberFormat.getCurrencyInstance(locale);


    public String getDate() {
        return data;
    }

    public void setDate(String date) {
        data = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        String apiData = data.replaceAll("-", "/");
        String[] s = apiData.split("/");
        String novaData = s[2] + "/" + s[1] + "/" + s[0];
        return novaData;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getmValue() {
        double value = Double.parseDouble(mValue);
        return real.format(value);
    }

}
