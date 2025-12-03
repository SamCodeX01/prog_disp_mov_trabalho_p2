package com.example.projeto_2.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto_2.R;

import java.util.ArrayList;

public class OrcamentoAdapter extends RecyclerView.Adapter<OrcamentoAdapter.ViewHolder> {

    private ArrayList<Orcamento> lista;
    private OnCardActionListener listener;

    public interface OnCardActionListener {
        void onAbrirServico(Orcamento orcamento, int position);
        void onBaixarContrato(Orcamento orcamento, int position);
    }

    public OrcamentoAdapter(ArrayList<Orcamento> lista, OnCardActionListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome, cpf, email, celular, endereco, pacote, data, qtd, inicio, termino, endEvento;
        Button btnAbrirServico, btnBaixarContrato;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txtNome);
            cpf = itemView.findViewById(R.id.txtCpf);
            email = itemView.findViewById(R.id.txtEmail);
            celular = itemView.findViewById(R.id.txtCelular);
            endereco = itemView.findViewById(R.id.txtEndereco);
            pacote = itemView.findViewById(R.id.txtPacote);
            data = itemView.findViewById(R.id.txtData);
            qtd = itemView.findViewById(R.id.txtQtd);
            inicio = itemView.findViewById(R.id.txtInicio);
            termino = itemView.findViewById(R.id.txtTermino);
            endEvento = itemView.findViewById(R.id.txtEnderecoEvento);
            btnAbrirServico = itemView.findViewById(R.id.btnAbrirServicoParaMusicos);
            btnBaixarContrato = itemView.findViewById(R.id.btnBaixarContrato);
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
        Orcamento o = lista.get(position);
        holder.nome.setText("Nome: " + o.getNome());
        holder.cpf.setText("CPF: " + o.getCpf());
        holder.email.setText("Email: " + o.getEmail());
        holder.celular.setText("Celular: " + o.getCelular());
        holder.endereco.setText("Endereço: " + o.getEndereco());
        holder.pacote.setText("Pacote: " + o.getNomePacote());
        holder.data.setText("Data: " + o.getDataEvento());
        holder.qtd.setText("Convidados: " + o.getQtdConvidados());
        holder.inicio.setText("Início: " + o.getHorarioInicio());
        holder.termino.setText("Término: " + o.getHorarioTermino());
        holder.endEvento.setText("Endereço do Evento: " + o.getEnderecoEvento());

        holder.btnAbrirServico.setOnClickListener(v ->
                listener.onAbrirServico(o, holder.getAdapterPosition())
        );

        holder.btnBaixarContrato.setOnClickListener(v ->
                listener.onBaixarContrato(o, holder.getAdapterPosition())
        );
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void atualizarItem(int position) {
        notifyItemChanged(position);
    }
}
