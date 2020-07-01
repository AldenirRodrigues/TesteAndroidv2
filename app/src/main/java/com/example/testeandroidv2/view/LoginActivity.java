package com.example.testeandroidv2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.firebase.ConfiguracaoFirebase;
import com.example.testeandroidv2.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.testeandroidv2.view.CadastroActivity.setaLog;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.textCadastrar)
    public TextView cadastar;
    @BindView(R.id.editSenhaLogin)
    public EditText editSenha;
    @BindView(R.id.editEmailLogin)
    public EditText editEmail;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verificaUsuarioLogado();
        setContentView(R.layout.activity_login);
        getSupportActionBar().setElevation(0);
        ButterKnife.bind(this);

    }


    private boolean validarLogin(String email, String senha) {

        if (TextUtils.isEmpty(email)) {
            editEmail.setError("Ops, precisamos do seu email!");
        } else if (TextUtils.isEmpty(senha)) {
            editSenha.setError("Ops, não esqueça da senha!");
        } else {
            return true;
        }
        return false;
    }


    @OnClick(R.id.btnLogar)
    void setaLogin() {
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        if (validarLogin(email, senha)) {
            usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);
            concluirLogin();
        }
    }



    private void concluirLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    setaMainActivity();


                } else {
                    String excessao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        excessao = "Ops, parece que você não está cadastrado!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excessao = "Ops, Email e senha não correspondem a um usuário cadastrado!";

                    } catch (Exception e) {
                        excessao = "Erro ao realizar login, sentimos muito!";
                        e.printStackTrace();
                        setaLog(e.getMessage());
                    }

                    Toast.makeText(LoginActivity.this, excessao, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void setaMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void setaCadastro(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    private void verificaUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        if (autenticacao.getCurrentUser() != null){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }

        }
    }

}
