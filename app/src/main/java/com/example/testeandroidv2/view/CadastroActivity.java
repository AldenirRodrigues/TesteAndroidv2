package com.example.testeandroidv2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.firebase.ConfiguracaoFirebase;
import com.example.testeandroidv2.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroActivity extends AppCompatActivity {

    @BindView(R.id.editEmailCadastro)
    public EditText emailCad;
    @BindView(R.id.editNomeCadastro)
    public EditText nomeCad;
    @BindView(R.id.editSenhaCadastro)
    public EditText senhaCad;
    @BindView(R.id.editConfirmaSenha)
    public EditText confirmaSenha;
    private FirebaseAuth autenticacao;
    private Usuario usuario;
    public static final String CAD_USER = "PrefUser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setElevation(0);
        ButterKnife.bind(this);

    }

    private boolean validaCadastro(String nome, String email, String senha, String confirma) {

        if (TextUtils.isEmpty(nome)) {
            nomeCad.setError("Ops, não esqueça do nome!");
        } else if (TextUtils.isEmpty(email)) {
            emailCad.setError("Ops, precisamos do seu email!");
        } else if (TextUtils.isEmpty(senha)) {
            senhaCad.setError("Ops, não esqueça da senha!");
        } else if (TextUtils.isEmpty(confirma)) {
            confirmaSenha.setError("Para finalizarmos, confirme a senha!");
        } else if (!senha.equals(confirma)) {
            confirmaSenha.setError("Ops, as senhas não parecem iguais!");
        } else {
            return true;
        }
        return false;
    }

    private void cadastrarUsuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                   guardaNome();
                    Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(CadastroActivity.this).toBundle());
                    }
                } else {
                    String excessao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        excessao = "Ops, digite uma senha mais forte!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excessao = "Ops, esta conta já foi cadastrada!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excessao = "Ops, este não parece um Email váldo!";
                    } catch (Exception e) {
                        excessao = "Erro ao realizar cadastro, sentimos muito!";
                        e.printStackTrace();
                        setaLog(e.getMessage());
                    }
                    Toast.makeText(CadastroActivity.this, excessao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public static int setaLog(String message) {
        return Log.e("TAG", message);
    }

    @OnClick(R.id.btnCadastrar)
    void concluirCadastro() {
        String nome = nomeCad.getText().toString();
        String email = emailCad.getText().toString();
        String senha = senhaCad.getText().toString();
        String confirma = confirmaSenha.getText().toString();

        if (validaCadastro(nome, email, senha, confirma)) {
            usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            cadastrarUsuario();
        }
    }
    private void guardaNome() {
        SharedPreferences preferences = getSharedPreferences(CAD_USER, 0);
        SharedPreferences.Editor editor = preferences.edit();
        String nome = nomeCad.getText().toString();
        editor.putString("nome", nome);
        editor.commit();
    }



}
