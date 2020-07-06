package com.example.testeandroidv2.interfaces;

import android.content.Intent;
import android.widget.EditText;

public interface LoginInterface {
     interface View{
         void onLoginSuccess(Intent intent);
         void onLoginError(String menssage);
     }
     interface Prsesenter{
         void onLogin(EditText email, EditText password);
         boolean onValidPassword(EditText email, EditText password);
     }
     interface Model{
     }
}
