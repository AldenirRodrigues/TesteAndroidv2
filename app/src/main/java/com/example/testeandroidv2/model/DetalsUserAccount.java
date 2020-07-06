
package com.example.testeandroidv2.model;

import com.example.testeandroidv2.interfaces.ExtratInterface;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DetalsUserAccount implements ExtratInterface.Model {
    private ExtratInterface.Prsesenter iPresenter;

    @SerializedName("agency")
    private String mAgency;
    @SerializedName("balance")
    private String mBalance;
    @SerializedName("bankAccount")
    private String mBankAccount;
    @SerializedName("name")
    private String mName;
    @SerializedName("userId")
    private String mUserId;




    public DetalsUserAccount(String mAgency, String mBalance, String mBankAccount, String mName, String mUserId) {
        this.mAgency = mAgency;
        this.mBalance = mBalance;
        this.mBankAccount = mBankAccount;
        this.mName = mName;
        this.mUserId = mUserId;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getAgency() {
        return mAgency;
    }

    public void setAgency(String agency) {
        mAgency = agency;
    }

    public String getmBalance() {
        return mBalance;
    }

    public void setmBalance(String mBalance) {
        this.mBalance = mBalance;
    }

    public String getBankAccount() {
        return mBankAccount;
    }

    public void setBankAccount(String bankAccount) {
        mBankAccount = bankAccount;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }





}
