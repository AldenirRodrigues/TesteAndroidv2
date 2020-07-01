package com.example.testeandroidv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testeandroidv2.R;
import com.example.testeandroidv2.model.StatementList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExtratoAdapter extends RecyclerView.Adapter<ExtratoAdapter.ExtratoViewHolder> {

    private Context context;
    private List<StatementList>gastos;

    public ExtratoAdapter(Context context, List<StatementList> gastos) {
        this.context = context;
        this.gastos = gastos;
    }

    @NonNull
    @Override
    public ExtratoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_extrato, parent, false);
        return new ExtratoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtratoViewHolder holder, int position) {
        StatementList extrato = gastos.get(position);

        holder.data.setText(extrato.getData());
        holder.valor.setText(extrato.getmValue());
        holder.descricao.setText(extrato.getDesc());
    }

    @Override
    public int getItemCount() {
        return gastos.size();
    }

    public class ExtratoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textDescricao)
        TextView descricao;
        @BindView(R.id.textValor)
        TextView valor;
        @BindView(R.id.textData)
        TextView data;

        public ExtratoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
