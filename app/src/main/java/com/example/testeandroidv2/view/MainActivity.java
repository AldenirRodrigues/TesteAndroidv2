package com.example.testeandroidv2.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.adapter.ExtratoAdapter;
import com.example.testeandroidv2.firebase.ConfiguracaoFirebase;
import com.example.testeandroidv2.model.Extrato;
import com.example.testeandroidv2.model.Pagamentos;
import com.example.testeandroidv2.model.StatementList;
import com.example.testeandroidv2.server.DataServer;
import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.testeandroidv2.view.CadastroActivity.CAD_USER;
import static com.example.testeandroidv2.view.CadastroActivity.setaLog;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView listaGastos;
    @BindView(R.id.textConta)
    TextView conta;
    @BindView(R.id.textSaldo)
    TextView saldo;
    @BindView(R.id.icLogout)
    ImageView logout;
    @BindView(R.id.appbar)
    CollapsingToolbarLayout toolbarLayout;
    List<StatementList> lists = new ArrayList<>();
    FirebaseAuth autenticacao;
    FirebaseDatabase firebaseDatabase;
    Locale locale = new Locale("pt", "BR");
    NumberFormat real = NumberFormat.getCurrencyInstance(locale);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        logout.setOnClickListener(v -> {
            finish();
        });
        SharedPreferences preferences = getSharedPreferences(CAD_USER, 0);
        if (preferences.contains("nome")) {
            toolbarLayout.setTitle(preferences.getString("nome", "Usuário não definido"));
        }

        populaLista();
        printaConta();
    }



    private void populaLista() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        listaGastos.setLayoutManager(manager);
        listaGastos.setHasFixedSize(true);

        DataServer dataServer = new DataServer();
        Call<Pagamentos> call = dataServer.api.setaExtrato("1");
        call.enqueue(new Callback<Pagamentos>() {
            @Override
            public void onResponse(Call<Pagamentos> call, Response<Pagamentos> response) {
                if (response.isSuccessful()) {
                    lists = response.body().getStatementList();
                    for (StatementList list : lists) {
                        double soma = Double.parseDouble(String.valueOf(list.mValue));
                        if (soma > 0) {
                            soma += soma;
                        }
                        saldo.setText(real.format(soma));
                    }
                    ExtratoAdapter adapter = new ExtratoAdapter(MainActivity.this, lists);
                    listaGastos.setAdapter(adapter);
                } else {
                    setaLog("Erro! " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Pagamentos> call, Throwable t) {
                setaLog(t.getMessage());

            }
        });

    }

    private void printaConta() {
        int random1 = new Random(13).nextInt();
        SimpleMaskFormatter maskFormatter = new SimpleMaskFormatter("NNNN/NN.NNNNNN-N");
        MaskTextWatcher textWatcher = new MaskTextWatcher(conta, maskFormatter);
        conta.addTextChangedListener(textWatcher);
        conta.setText(String.valueOf(random1));
    }

}
