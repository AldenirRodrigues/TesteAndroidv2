package com.example.testeandroidv2.model;

import com.google.gson.annotations.SerializedName;

public class User {


    @SerializedName("userAccount")
    private UserAccount userAccount;


    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
