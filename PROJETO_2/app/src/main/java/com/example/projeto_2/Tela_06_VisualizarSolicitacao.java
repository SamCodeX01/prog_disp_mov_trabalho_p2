package com.example.projeto_2;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projeto_2.classes.Musico;
import com.example.projeto_2.classes.MusicoDAO;
import com.example.projeto_2.classes.Orcamento;
import com.example.projeto_2.classes.OrcamentoDAO;
import com.example.projeto_2.classes.PdfUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tela_06_VisualizarSolicitacao extends AppCompatActivity {
    private Intent intent;
    private Orcamento orcamento;
    private OrcamentoDAO orcamentoDAO;
    private TextView txtClienteSolicitacao, txtRetornoCliente, txtRetornoMusicos, txtRetornoCustos;
    private Button btnLiberarServicoParaMusicos, btnBaixarContrato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela06_visualizar_solicitacao);

        mainConfig();
        puxarSolicitacao();
        listeners();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mainConfig() {
        intent = getIntent();

        txtClienteSolicitacao = findViewById(R.id.txtClienteSolicitacao);
        txtRetornoCliente = findViewById(R.id.txtRetornoCliente);
        txtRetornoMusicos = findViewById(R.id.txtRetornoMusicos);
        txtRetornoCustos = findViewById(R.id.txtRetornoCustos);

        btnLiberarServicoParaMusicos = findViewById(R.id.btnLiberarServicoParaMusicos);
        btnBaixarContrato = findViewById(R.id.btnBaixarContrato);
    }

    private void puxarSolicitacao() {
        String nomeCliente = intent.getStringExtra("nomeCliente");

        // Puxa a solicitação do banco //
        orcamentoDAO = new OrcamentoDAO(this);
        orcamento = orcamentoDAO.consultarOrcamentoPorNome( nomeCliente );

        // Gerencia a aparição dos botões
        aparicaoDosBotoes();

        // Exibe na tela //
        txtClienteSolicitacao.setText("Nome: " + orcamento.getNome());
        txtRetornoCliente.setText(
                "Cpf: " + orcamento.getCpf() + "\n" +
                "Email: " + orcamento.getEmail() + "\n" +
                "Celular: " + orcamento.getCelular() + "\n" +
                "Endereço: " + orcamento.getEndereco() + "\n" +
                "Pacote: " + orcamento.getNomePacote() + "\n" +
                "Data do Evento: " + orcamento.getDataEvento() + "\n" +
                "Horário de Início: " + orcamento.getHorarioInicio() + "\n" +
                "Horário de Término: " + orcamento.getHorarioTermino() + "\n" +
                "Qtd de Convidados: " + orcamento.getQtdConvidados() + "\n" +
                "Endereço do Evento: " + orcamento.getEnderecoEvento() + "\n"
        );

        Log.e("ERRO", orcamento.getStatus());
        if (orcamento.getStatus().equals("concluida"))
            mostrarInformacoes();

    }

    private void mostrarInformacoes() {
        int qtdDeMusicos = 2;

        // Puxa músicos do banco //
        MusicoDAO musicoDAO = new MusicoDAO(this);
        ArrayList<Musico> musicos = new ArrayList<>( musicoDAO.listarMusicos() );

        if (musicos.size() >= qtdDeMusicos) {

            // Embaralha a lista para pegar N músicos aleatórios
            Collections.shuffle(musicos);
            List<Musico> escolhidos = musicos.subList(0, qtdDeMusicos);

            // Atribui músicos aleatórios ao serviço (SIMULA músicos aceitando a solicitação)
            String oqTinhaAntes = "";
            for (Musico musico : escolhidos) {
                oqTinhaAntes = txtRetornoMusicos.getText().toString();
                txtRetornoMusicos.setText( oqTinhaAntes              + "\n" +
                        "Nome: "        + musico.getNome()               + "\n" +
                        "Instrumento: " + musico.getInstrumentoQueToca() + "\n" +
                        "Celular: "     + musico.getCelular()            + "\n" +
                        "Email: "       + musico.getEmail()              + "\n"
                );
            }

            // Define os custos do serviço //
            txtRetornoCustos.setText(
                    "Custo de transporte de equipamentos: R$XXXX,XX \n\n" +
                    "Custo alimentício R$XXXX,XX \n\n" +
                    "Cache dos músicos R$XXXX,XX \n\n" +
                    "Custo de aluguel dos equipamentos R$XXXX,XX \n"
            );

        }
        else Toast.makeText(this, "Músicos insulficientes para o serviço. \nCadastre mais músicos! (mín " + qtdDeMusicos + ")", LENGTH_SHORT).show();
    }

    private void listeners() {

        // LIBERAR SERVIÇO para músicos
        btnLiberarServicoParaMusicos.setOnClickListener(evt -> {

            // Atualiza o status da solicitação //
            orcamentoDAO.atualizarStatus(orcamento.getId(), "concluida");
            Toast.makeText(this, "Solicitação liberada no sistema! \n(volte para a tela anterior)", LENGTH_SHORT).show();

            finish();
        });


        // BAIXAR CONTRATO do serviço
        btnBaixarContrato.setOnClickListener(evt -> {
            String conteudo = "";

            File pdf = PdfUtils.gerarPdfSimples(
                    this,
                    "orcamento_" + orcamento.getId(),
                    conteudo
            );
        });

    }

    private void aparicaoDosBotoes() {
        // Gerencia a aparição dos botões
        if (orcamento.getStatus().equals("aberta")) {
            btnLiberarServicoParaMusicos.setVisibility(VISIBLE);
            btnBaixarContrato.setVisibility(GONE);
        }
        else {
            btnLiberarServicoParaMusicos.setVisibility(GONE);
            btnBaixarContrato.setVisibility(VISIBLE);
            mostrarInformacoes();
        }

    }

}