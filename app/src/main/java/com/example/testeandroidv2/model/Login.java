package com.example.testeandroidv2.model;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.testeandroidv2.interfaces.LoginInterface;
import com.google.gson.annotations.SerializedName;

public class Login implements LoginInterface.Model {

    @SerializedName("userAccount")
    private DetalsUserAccount userAccount;

    public DetalsUserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(DetalsUserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
