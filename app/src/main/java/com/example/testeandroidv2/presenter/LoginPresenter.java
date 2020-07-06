package com.example.testeandroidv2.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.testeandroidv2.Main2Activity;
import com.example.testeandroidv2.model.Login;
import com.example.testeandroidv2.model.User;
import com.example.testeandroidv2.server.DataServer;
import com.example.testeandroidv2.view.ILoginView;
import com.example.testeandroidv2.view.MainActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter1 implements ILoginPresenter {

    private ILoginView loginView;

    public LoginPresenter1(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String email, String password) {

        User user = new User(email, password);
        int loginCode = user.isValidData();

        if (loginCode == 0) {
            loginView.onLoginError("Precisamos do seu Email ou CPF, por favor!");
        } else if (loginCode == 1) {
            loginView.onLoginError("Informe um Email válido!");
        } else if (loginCode == 2) {
            loginView.onLoginError("Informe uma  uma senha válida!");
        } else {
//            DataServer dataServer = new DataServer();
//            Observable<Login> call = dataServer.api.logaUsuario1(email, password);
//            call.subscribeOn(Schedulers.newThread()).subscribe(new Observer<Login>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//
//                }
//
//                @Override
//                public void onNext(Login value) {
                    loginView.onLoginSuccess(new Intent((Context) loginView, Main2Activity.class));

//                            SharedPref.getInstance(loginView).getSharedPref(value.getUserAccount());
//                            startActivity(new Intent(loginView, MainActivity.class));

//                }
//
//                @Override
//                public void onError(Throwable e) {
//
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });
        }
    }

}
