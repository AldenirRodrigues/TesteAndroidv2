package com.example.testeandroidv2.util;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditTexttUtil {

    public static boolean onValidaInputLogin(EditText editLogin, EditText editPassword) {
        String password = editPassword.getText().toString();
        String email = editLogin.getText().toString();

        SimpleMaskFormatter maskFormatter = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher textWatcher = new MaskTextWatcher(editLogin, maskFormatter);
        editLogin.addTextChangedListener(textWatcher);

        String cpf = email.replace(".", "").replace("-", "");

        if (TextUtils.isEmpty(email)) {
            editLogin.setError("Ops, precisamos do email ou CPF!");

        } else if (cpf.matches("[0-9]*")) {

            if (!CPFUtil.isCPF(cpf)) {
                editLogin.setError("Ops, este CPF não parece válido!");
            }
            return onValidInputPaswor(editPassword, password);

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editLogin.setError("Ops, email não parece válido!");
        }
        return onValidInputPaswor(editPassword, password);
    }

    private static boolean onValidInputPaswor(EditText editPassword, String password) {

        if (TextUtils.isEmpty(password))
            editPassword.setError("Ops, não esqueça da senha!");

        else if (password.length() < 5)
            editPassword.setError("Ops, a senha não pode ser menor que 5!");
        else
            return onValidInputChar(editPassword, password);
        return false;
    }

    private static boolean onValidInputChar(EditText editPassword, String password) {

        Matcher mNumber = Pattern.compile("[\\p{Alnum}]").matcher(password);

        while (mNumber.find()) {
            return onValidInputEsp(editPassword, password);
        }
        editPassword.setError("Senha deve conter um numero!");
        return false;
    }

    private static boolean onValidInputEsp(EditText editPassword, String password) {
        Matcher mEsp = Pattern.compile("[\\p{Punct}]").matcher(password);

        while (mEsp.find()) {
            return onValidInputUper(editPassword, password);
        }
        editPassword.setError("Senha deve conter um caracter especial!");
        return false;
    }

    private static boolean onValidInputUper(EditText editPassword, String password) {
        Matcher mUper = Pattern.compile("[\\p{javaUpperCase}]").matcher(password);

        while (mUper.find()) {
            return true;
        }
        editPassword.setError("Senha deve conter um caracter maiúsculo!");
        return false;
    }


}
