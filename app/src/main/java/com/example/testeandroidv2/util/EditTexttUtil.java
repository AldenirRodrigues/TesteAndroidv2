package com.example.testeandroidv2.util;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditTexttUtil {

    public static String onValidInputEmail(EditText editLogin) {
        String email = editLogin.getText().toString();

//        SimpleMaskFormatter maskFormatter = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
//        MaskTextWatcher textWatcher = new MaskTextWatcher(editLogin, maskFormatter);
//        editLogin.addTextChangedListener(textWatcher);

        String cpf = email.replace(".", "").replace("-", "");

        if (TextUtils.isEmpty(email)) {
            return "Ops, precisamos do email ou CPF!";

        } else if (cpf.matches("[0-9]*")) {

            if (!CPFUtil.isCPF(cpf)) {
                return "Ops, este CPF não parece válido!";
            }
            return null;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Ops, email não parece válido!";
        }
        return null;
    }

    public static String onValidInputPaswor(EditText edtiPassword) {
        String password = edtiPassword.getText().toString();

        if (TextUtils.isEmpty(password))
            return "Ops, não esqueça da senha!";

        else if (password.length() < 5)
            return "Ops, a senha não pode ser menor que 5!";

        else if (onValidInputNumber(password) != null)
            return onValidInputNumber(password);

        else if (onValidInputEsp(password) != null)
            return onValidInputEsp(password);

        else if (onValidInputUper(password) != null)
            return onValidInputUper(password);
        else
            return null;
    }

    private static String onValidInputNumber(String password) {
        Matcher mNumber = Pattern.compile("\\p{Digit}").matcher(password);

        while (mNumber.find()) {
            return null;
        }
        return "Senha deve conter um numero!";
    }

    private static String onValidInputEsp(String password) {
        Matcher mEsp = Pattern.compile("[\\p{Punct}]").matcher(password);

        while (mEsp.find()) {
            return null;
        }
        return "Senha deve conter um caracter especial!";
    }

    private static String onValidInputUper(String password) {
        Matcher mUper = Pattern.compile("[\\p{javaUpperCase}]").matcher(password);

        while (mUper.find()) {
            return null;
        }
        return "Senha deve conter um caracter maiúsculo!";
    }


}
