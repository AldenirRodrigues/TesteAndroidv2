package com.example.testeandroidv2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.presenter.ILoginPresenter;
import com.example.testeandroidv2.presenter.LoginPresenter;
import com.example.testeandroidv2.presenter.LoginPresenter1;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class Login1Activity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.editSenhaLogin)
    public EditText editSenha;
    @BindView(R.id.editEmailLogin)
    public EditText editEmail;
    @BindView(R.id.btnLogar)
    public Button logar;

    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter1(this);

        logar.setOnClickListener(v -> {
            loginPresenter.onLogin(editEmail.getText().toString(), editSenha.getText().toString());
        });

    }


    @Override
    public void onLoginSuccess(Intent intent) {
//        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public void onLoginError(String menssage) {
        Toasty.error(this, menssage, Toast.LENGTH_LONG).show();
    }
}
