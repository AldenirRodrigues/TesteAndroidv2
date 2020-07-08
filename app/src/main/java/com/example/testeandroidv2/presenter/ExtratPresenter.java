package com.example.testeandroidv2.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.adapter.ExtratoAdapter;
import com.example.testeandroidv2.interfaces.ExtratInterface;
import com.example.testeandroidv2.model.ListExtract;
import com.example.testeandroidv2.model.StatementList;
import com.example.testeandroidv2.server.DataServer;
import com.example.testeandroidv2.util.SharedPref;
import com.example.testeandroidv2.view.LoginActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtratPresenter implements ExtratInterface.Prsesenter{
    private ExtratInterface.View view;
    private ExtratInterface.Model model;
    private DataServer server;
    private List<StatementList> listSpending;
    private RecyclerView recyclerView;
    private AlertDialog alertDialog;


    public ExtratPresenter(ExtratInterface.View view, RecyclerView recyclerView) {
        this.view = view;
        this.recyclerView = recyclerView;
        onListSpending();
    }

    @Override
    public void onListSpending() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager((Context) view);
        setDataUser(SharedPref.getInstance((Context) view));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void setDataUser(SharedPref preferences) {
        view.setName(preferences.getUser().getName());
        view.setCount(preferences.getUser().getBankAccount());
        view.setAgincy(preferences.getUser().getAgency());
        view.setBalance(preferences.getUser().getmBalance());
        view.setAgincy(preferences.getUser().getAgency());
        setDataServer(preferences.getUser().getmUserId());
    }

    @Override
    public void setDataServer(String userId) {
        server = new DataServer();

        Call<ListExtract> call = server.api.setaExtrato(userId);
        call.enqueue(new Callback<ListExtract>() {
            @Override
            public void onResponse(Call<ListExtract> call, Response<ListExtract> response) {
                listSpending = new ArrayList<>();
                if (response.isSuccessful()) {
                    listSpending = response.body().getStatementList();

                    view.setAdapter(new ExtratoAdapter((Context) view, listSpending));
                }
            }

            @Override
            public void onFailure(Call<ListExtract> call, Throwable t) {


            }
        });
    }

    @Override
    public void onLogout() {
        AlertDialog.Builder alert = new AlertDialog.Builder((Context) view);
        alert.setTitle(R.string.strTitle);
        alert.setMessage(R.string.strMensage);
        alert.setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              view.onFinish();
            }
        })
                .setNegativeButton(R.string.cancel, null);
        alertDialog = alert.create();
        alertDialog.show();
    }

}
