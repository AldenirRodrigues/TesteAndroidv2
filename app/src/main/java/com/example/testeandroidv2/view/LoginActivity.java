package com.example.testeandroidv2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.interfaces.LoginInterface;
import com.example.testeandroidv2.presenter.LoginPresenter;
import com.facebook.shimmer.ShimmerFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginInterface.View {
    LoginInterface.Prsesenter loginPresenter;

    @BindView(R.id.editSenhaLogin)
    public EditText editPassword;
    @BindView(R.id.editEmailLogin)
    public EditText editEmail;
    @BindView(R.id.btnLogar)
    public Button logar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.linearLayoutLogin)
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this, editEmail, editPassword);
    }

    @Override
    public void onLoginSuccess(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onLoginError(String menssage) {
        editEmail.setError(menssage);
    }

    @Override
    public void onPasswordError(String mensage){
        editPassword.setError(mensage);
    }

    @OnClick(R.id.btnLogar)
    void logar() {
        loginPresenter.onLogin(editEmail, editPassword);
    }
    @Override
    public void onFinish() {
        finish();
    }

    @Override
    public void onInvisibleLayout() {
        linearLayout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }
}
