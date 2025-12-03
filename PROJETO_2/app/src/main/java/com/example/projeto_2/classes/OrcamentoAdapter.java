package com.example.projeto_2.classes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto_2.R;

import java.util.ArrayList;

public class OrcamentoAdapter extends RecyclerView.Adapter<OrcamentoAdapter.ViewHolder> {

    private ArrayList<Orcamento> lista;
    private OnCardActionListener listener;

    public interface OnCardActionListener {
        void onCardClicado(Orcamento orcamento, int position);
    }

    public OrcamentoAdapter(ArrayList<Orcamento> lista, OnCardActionListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome, cpf, email, celular, endereco, pacote, data, qtd, inicio, termino, endEvento;
        CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            nome              = itemView.findViewById(R.id.txtNome);
            cpf               = itemView.findViewById(R.id.txtCpf);
            email             = itemView.findViewById(R.id.txtEmail);
            celular           = itemView.findViewById(R.id.txtCelular);
            endereco          = itemView.findViewById(R.id.txtEndereco);
            pacote            = itemView.findViewById(R.id.txtPacote);
            data              = itemView.findViewById(R.id.txtData);
            qtd               = itemView.findViewById(R.id.txtQtd);
            inicio            = itemView.findViewById(R.id.txtInicio);
            termino           = itemView.findViewById(R.id.txtTermino);
            endEvento         = itemView.findViewById(R.id.txtEnderecoEvento);
            card = itemView.findViewById(R.id.cardOrcamento);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_orcamento, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Orcamento orc = lista.get(position);
        holder.nome.setText("Nome: " + orc.getNome());
        holder.cpf.setText("CPF: " + orc.getCpf());
        holder.email.setText("Email: " + orc.getEmail());
        holder.celular.setText("Celular: " + orc.getCelular());
        holder.endereco.setText("Endereço: " + orc.getEndereco());
        holder.pacote.setText("Pacote: " + orc.getNomePacote());
        holder.data.setText("Data: " + orc.getDataEvento());
        holder.qtd.setText("Convidados: " + orc.getQtdConvidados());
        holder.inicio.setText("Início: " + orc.getHorarioInicio());
        holder.termino.setText("Término: " + orc.getHorarioTermino());
        holder.endEvento.setText("Endereço do Evento: " + orc.getEnderecoEvento());

        // ⬇️ AQUI É ONDE VOCÊ DEVE COLOCAR O CLICK NO CARD
        holder.card.setOnClickListener(v -> {
            listener.onCardClicado(orc, position);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void atualizarItem(int position) {
        notifyItemChanged(position);
    }
}
