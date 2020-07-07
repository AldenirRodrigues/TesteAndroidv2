package com.example.testeandroidv2.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.adapter.ExtratoAdapter;
import com.example.testeandroidv2.interfaces.ExtratInterface;
import com.example.testeandroidv2.model.StatementList;
import com.example.testeandroidv2.presenter.ExtratPresenter;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ExtratInterface.View{
    private ExtratPresenter presenter;

    @BindView(R.id.recyclerView)
    public RecyclerView listaGastos;
    @BindView(R.id.textConta)
    public TextView conta;
    @BindView(R.id.textSaldo)
    public TextView saldo;
    @BindView(R.id.textAgencia)
    public TextView agencia;
    @BindView(R.id.appbar)
    public CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.shimerFrameLayout)
    public ShimmerFrameLayout shimmerFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new ExtratPresenter(this, listaGastos);
    }

    @Override
    public void setName(String name) {
        toolbarLayout.setTitle(name);
    }

    @Override
    public void setCount(String count) {
        conta.setText(count);
    }

    @Override
    public void setBalance(String balance) {
        NumberFormat nFormat = NumberFormat.getCurrencyInstance();
        String value = nFormat.format(Double.parseDouble(balance));
        saldo.setText(value);
    }

    @Override
    public void setAgincy(String agency) {
        SimpleMaskFormatter maskFormatter = new SimpleMaskFormatter("/NN.NNNNNN-N");
        MaskTextWatcher textWatcher = new MaskTextWatcher(agencia, maskFormatter);
        agencia.addTextChangedListener(textWatcher);
        agencia.setText(agency);
    }

    @Override
    public void setAdapter(ExtratoAdapter adapter) {
        shimmerFrameLayout.setVisibility(View.GONE);
        shimmerFrameLayout.stopShimmer();

        listaGastos.setAdapter(adapter);

    }

    @OnClick(R.id.icLogout)
    void logout() {
        presenter.onLogout();
    }

    @Override
    public void onBackPressed() {
        presenter.onLogout();
        super.onBackPressed();
    }
}
