package com.example.projeto_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto_2.classes.Orcamento;
import com.example.projeto_2.classes.OrcamentoAdapter;
import com.example.projeto_2.classes.OrcamentoDAO;

import java.util.ArrayList;

public class Tela_05_SolicitacoesGestor extends AppCompatActivity {
    private OrcamentoDAO orc;
    private ArrayList<Orcamento> orcamentos;
    private OrcamentoAdapter adapter;
    private Button btnMostrarSolicitacoesAbertas, btnMostrarSolicitacoesConcluidas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela05_solicitacoes_gestor);

        mainConfig();
        mostrarSolicitacoes("aberta");
        listeners();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mainConfig() {
        orc = new OrcamentoDAO(this);
        orcamentos = new ArrayList<>( orc.listarOrcamentos() );

        btnMostrarSolicitacoesAbertas = findViewById(R.id.btnMostrarSolicitacoesAbertas);
        btnMostrarSolicitacoesConcluidas = findViewById(R.id.btnMostrarSolicitacoesConcluidas);
    }

    private void mostrarSolicitacoes(String queroSolicitacoesEm) {

        OrcamentoDAO dao = new OrcamentoDAO(this);
        ArrayList<Orcamento> lista = dao.listarOrcamentos();

        RecyclerView rv = findViewById(R.id.meuRecyclerId);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Orcamento> listaFiltrada = new ArrayList<>();
        for (Orcamento o : lista) {
            if (o.getStatus().equals(queroSolicitacoesEm))
                listaFiltrada.add(o);
        }

        adapter = new OrcamentoAdapter(listaFiltrada, new OrcamentoAdapter.OnCardActionListener() {
            @Override
            public void onCardClicado(Orcamento orc, int position) {
                // 👉 Aqui sim você abre outra Activity
                Intent intent = new Intent(Tela_05_SolicitacoesGestor.this, Tela_06_VisualizarSolicitacao.class);
                intent.putExtra("nomeCliente", orc.getNome());
                startActivity(intent);
            }

        });

        rv.setAdapter(adapter);
    }



    private void listeners() {
        btnMostrarSolicitacoesAbertas.setOnClickListener(evt -> mostrarSolicitacoes("aberta"));
        btnMostrarSolicitacoesConcluidas.setOnClickListener(evt -> mostrarSolicitacoes("concluida"));
    }

}