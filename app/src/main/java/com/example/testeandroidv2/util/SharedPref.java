package com.example.testeandroidv2.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testeandroidv2.model.DetalsUserAccount;

import java.text.NumberFormat;

public class SharedPref {
    private static final String SHARED_PREF_NAME = "teste_v2";

    private static final String NOME = "nome";
    private static final String CONTA = "conta";
    private static final String AGENCIA = "agencia";
    private static final String ID = "userId";
    private static final String BALANCE= "balance";

    private NumberFormat nFormat = NumberFormat.getCurrencyInstance();
    private static SharedPref mSharedPref;
    private Context mContext;

    public SharedPref(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized SharedPref getInstance(Context mContext) {
        if (mSharedPref == null) {
            mSharedPref = new SharedPref(mContext);
        }
        return mSharedPref;
    }

    public void getSharedPref(DetalsUserAccount user) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NOME, user.getName());
        editor.putString(AGENCIA, user.getAgency());
        editor.putString(CONTA, user.getBankAccount());
        editor.putString(BALANCE, user.getmBalance());
        editor.putString(ID, user.getmUserId());
        editor.apply();
    }

    public DetalsUserAccount getUser(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new DetalsUserAccount(
                sharedPreferences.getString(AGENCIA, null),
                sharedPreferences.getString(BALANCE, "R$0,00"),
                sharedPreferences.getString(CONTA, null),
                sharedPreferences.getString(NOME, null),
                sharedPreferences.getString(ID,"-1")
        );
    }
}
