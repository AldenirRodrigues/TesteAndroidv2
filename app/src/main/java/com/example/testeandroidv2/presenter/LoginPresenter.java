package com.example.testeandroidv2.presenter;

import android.util.Log;
import android.widget.EditText;

import com.example.testeandroidv2.interfaces.LoginInterface;
import com.example.testeandroidv2.util.EditTexttUtil;

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

        EditTexttUtil.onValidaInputLogin(editEmail, editPssword);

//        editEmail.setError(EditTexttUtil.onValidaInputLogin(editEmail));



//        DataServer dataServer = new DataServer();
//        Observable<Login> call = dataServer.api.logaUsuario(email.getText().toString(), password.getText().toString());
//        call.subscribeOn(Schedulers.newThread()).subscribe(new Observer<Login>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Login value) {
//                loginView.onLoginSuccess(new Intent((Context) loginView, MainActivity.class));
//                SharedPref.getInstance((Context) loginView).getSharedPref(value.getUserAccount());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

    }

//    @Override
//    public boolean onValidPassword(EditText editEmail, EditText editPassword) {
//
//        Matcher mEsp = Pattern.compile("[\\p{Punct}]").matcher(password);
//        Matcher mUper = Pattern.compile("[\\p{Upper}]").matcher(password);
//        Matcher mNumber = Pattern.compile("[\\p{Alnum}]").matcher(password);
//
//        if (password.isEmpty()) {
//            editPassword.setError("Ops, não esqueça da senha!");
//        } else {
//            while (mEsp.find() && mUper.find() && mNumber.find()) {
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }
//
//    @Override
//    public void onValidLogin(EditText email) {
//
//    }
//
//    @Override
//    public boolean onValidPassword() {
//        return false;
//    }
//
//    @Override
//    public void validaInputLogin(EditText editLogin, EditText editPassword) {
//        editLogin.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (TextUtils.isEmpty(email)) {
//                    editLogin.setError("Ops, precisamos do sou email ou CPF!");
//                } else if (s.toString().matches("[0-9]*")) {
//                    SimpleMaskFormatter maskFormatter = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
//                    MaskTextWatcher textWatcher = new MaskTextWatcher(editLogin, maskFormatter);
//                    editLogin.addTextChangedListener(textWatcher);
//                    if (!CPFUtil.isCPF(email)) {
//                        editLogin.setError("Ops, este CPF não parece válido!");
//                    }
//                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    editLogin.setError("Ops, email não parece válido!");
//                } else if (onValidPassword(editLogin, editPassword)) {
//                    editPassword.setError("Ops, esta senha parece fraca!");
//                    loginView.onLoginError("Senha deve conter pelo menos um caracter maiúscula, especial e um numero!");
//                } else {
//
//                }
//            }
//        });
//

}
