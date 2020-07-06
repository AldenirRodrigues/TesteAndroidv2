package com.example.testeandroidv2.interfaces;

import android.content.Context;
import android.widget.EditText;
import com.example.testeandroidv2.model.UserAccount;

public interface LoginInterface {
     interface View{
     }
     interface Prsesenter{
         void onViewDestroy();
         void getUsuario(EditText editEmail, EditText editSenha, Context context);
         void onViewCreate();
     }
     interface Model{
         UserAccount loginUser();
     }
}
