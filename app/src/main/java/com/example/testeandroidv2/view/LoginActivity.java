package com.example.testeandroidv2.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.interfaces.LoginInterface;
import com.example.testeandroidv2.presenter.LoginPresenter;
import com.example.testeandroidv2.util.CPFUtil;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements LoginInterface.View {
    LoginInterface.Prsesenter loginPresenter;

    @BindView(R.id.editSenhaLogin)
    public EditText editPassword;
    @BindView(R.id.editEmailLogin)
    public EditText editEmail;
    @BindView(R.id.btnLogar)
    public Button logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onLoginSuccess(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onLoginError(String menssage) {
        Toasty.error(this, menssage, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnLogar)
    void logar() {
        loginPresenter.onLogin(editEmail, editPassword);


    }
}

