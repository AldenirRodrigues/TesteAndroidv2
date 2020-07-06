package com.example.testeandroidv2.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testeandroidv2.interfaces.LoginInterface;
import com.example.testeandroidv2.model.Login;
import com.example.testeandroidv2.server.DataServer;
import com.example.testeandroidv2.util.CPFUtil;
import com.example.testeandroidv2.util.SharedPref;
import com.example.testeandroidv2.view.MainActivity;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginInterface.Prsesenter {
    private LoginInterface.View loginView;
    private String email, password;

    public LoginPresenter(LoginInterface.View loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(EditText editEmail, EditText editPssword) {
        this.email = editEmail.getText().toString();
        this.password = editPssword.getText().toString();

        if (!onValidPassword(editEmail, editPssword)) {
            editPssword.setError("Ops, esta senha parece fraca!");
            loginView.onLoginError("Senha deve conter pelo menos uma letra maiúscula, numero e !@#$% ");
        } else {
            DataServer dataServer = new DataServer();
            Observable<Login> call = dataServer.api.logaUsuario(email, password);
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

    @Override
    public boolean onValidPassword(EditText editEmail, EditText editPassword) {

        Matcher mEsp = Pattern.compile("[\\p{Punct}]").matcher(password);
        Matcher mUper = Pattern.compile("[\\p{Upper}]").matcher(password);
        Matcher mNumber = Pattern.compile("[\\p{Alnum}]").matcher(password);

        if (validaEmail(editEmail)) {
            if (password.isEmpty()) {
                editPassword.setError("Ops, não esqueça da senha!");
            } else {
                while (mEsp.find() && mUper.find() && mNumber.find()) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean validaEmail(EditText editText) {
        if (email.isEmpty()) {
            editText.setError("Ops, precisamos do sou email ou CPF!");
        } else {
            if (!email.matches("[0-9]")) {
                if (!CPFUtil.isCPF(email)) {
                    editText.setError("Ops, este CPF não parece válido!");
                } else {
                    return true;
                }
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editText.setError("Ops, email não parece válido!");
            } else {
                return true;
            }
        }
        return false;
    }
}
