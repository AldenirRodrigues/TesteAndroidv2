package com.example.testeandroidv2.interfaces;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import com.example.testeandroidv2.adapter.ExtratoAdapter;
import com.example.testeandroidv2.model.UserAccount;
import com.example.testeandroidv2.util.SharedPref;

public interface ExtratoInterface {
    interface View{
        void setAdapter(ExtratoAdapter adapter);
        void setName(String name);
        void setCount(String count);
        void setBalance(String balance);
        void setAgincy(String agency);
    }
    interface Prsesenter{
        void onListSpending();
        void setDataUser(SharedPref preferences);
        void setDataServer(String userId);
        void onLogout();
    }
    interface Model{
    }
}
