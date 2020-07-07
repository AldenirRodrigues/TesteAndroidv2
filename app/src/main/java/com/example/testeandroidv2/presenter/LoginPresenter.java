package com.example.testeandroidv2.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.example.testeandroidv2.interfaces.LoginInterface;
import com.example.testeandroidv2.model.Login;
import com.example.testeandroidv2.server.DataServer;
import com.example.testeandroidv2.util.EditTexttUtil;
import com.example.testeandroidv2.util.SharedPref;
import com.example.testeandroidv2.view.MainActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenter implements LoginInterface.Prsesenter {
    private LoginInterface.View loginView;
    private EditText email, password;

    public LoginPresenter(LoginInterface.View loginView, EditText editEmail, EditText editPssword) {
        this.loginView = loginView;
        this.email = editEmail;
        this.password = editPssword;
    }


    @Override
    public void onLogin(EditText editEmail, EditText editPssword) {
        this.email = editEmail;
        this.password = editPssword;

        if (EditTexttUtil.onValidInputEmail(editEmail) != null) {
            loginView.onLoginError(EditTexttUtil.onValidInputEmail(editEmail));
        } else if (EditTexttUtil.onValidInputPaswor(editPssword) != null){
            loginView.onPasswordError(EditTexttUtil.onValidInputPaswor(editPssword));
        } else {
            DataServer dataServer = new DataServer();
            Observable<Login> call = dataServer.api.logaUsuario(email.getText().toString(), password.getText().toString());
            call.subscribeOn(Schedulers.newThread()).subscribe(new Observer<Login>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Login value) {
                    loginView.onLoginSuccess(new Intent((Context) loginView, MainActivity.class));
                    SharedPref.getInstance((Context) loginView).getSharedPref(value.getUserAccount());
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
}
