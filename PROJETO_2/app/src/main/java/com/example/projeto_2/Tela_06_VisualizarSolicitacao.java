package com.example.projeto_2;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
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
    private List<Musico> musicosEscolhidos;
    private TextView txtClienteSolicitacao, txtRetornoCliente, txtRetornoMusicos, txtRetornoCustos;
    private Button btnLiberarServicoParaMusicos, btnBaixarContrato, btnVoltarT6;


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
        btnVoltarT6 = findViewById(R.id.btnVoltarT6);
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

        //============= EXIBIR INFORMAÇÕES ===========//
        if (orcamento.getStatus().equals("concluida"))
            mostrarInformacoes();
        //===========================================//
    }

    private void mostrarInformacoes() {
        int qtdDeMusicos = Musico.queroQtdMusicos;

        // Puxa músicos do banco //
        MusicoDAO musicoDAO = new MusicoDAO(this);
        ArrayList<Musico> musicos = new ArrayList<>( musicoDAO.listarMusicos() );

        // Embaralha a lista para pegar N músicos aleatórios
        Collections.shuffle(musicos);
        musicosEscolhidos = new ArrayList<>( musicos.subList(0, qtdDeMusicos) );

        // Atribui músicos aleatórios ao serviço (SIMULA músicos aceitando a solicitação)
        String oqTinhaAntes = "";
        for (Musico musico : musicosEscolhidos) {
            oqTinhaAntes = txtRetornoMusicos.getText().toString();
            txtRetornoMusicos.setText( oqTinhaAntes                  + "\n" +
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

    private void listeners() {

        // LIBERAR SERVIÇO para músicos
        btnLiberarServicoParaMusicos.setOnClickListener(evt -> {

            // Atualiza o status da solicitação //
            orcamentoDAO.atualizarStatus(orcamento.getId(), "concluida");
            Toast.makeText(this, "Solicitação liberada no sistema! \n(volte para a tela anterior)", LENGTH_LONG).show();

            startActivity(new Intent(this, Tela_05_SolicitacoesGestor.class));
            finish();
        });


        // BAIXA O CONTRATO do serviço (GPT)
        btnBaixarContrato.setOnClickListener(evt -> {
            StringBuilder conteudoBuilder = new StringBuilder();

            // ----------- DADOS DO CLIENTE ----------- //
            conteudoBuilder.append("Informações do cliente:\n")
                .append("Cpf: ").append(orcamento.getCpf()).append("\n")
                .append("Email: ").append(orcamento.getEmail()).append("\n")
                .append("Celular: ").append(orcamento.getCelular()).append("\n")
                .append("Endereço: ").append(orcamento.getEndereco()).append("\n\n");

            // ----------- DADOS DO EVENTO ----------- //
            conteudoBuilder.append("Informações do evento:\n")
                .append("Pacote: ").append(orcamento.getNomePacote()).append("\n")
                .append("Data do Evento: ").append(orcamento.getDataEvento()).append("\n")
                .append("Horário de Início: ").append(orcamento.getHorarioInicio()).append("\n")
                .append("Horário de Término: ").append(orcamento.getHorarioTermino()).append("\n")
                .append("Qtd de Convidados: ").append(orcamento.getQtdConvidados()).append("\n")
                .append("Endereço do Evento: ").append(orcamento.getEnderecoEvento()).append("\n\n");

            // ----------- MÚSICOS ----------- //
            // (talvez não tão relevante)
//
//            conteudoBuilder.append("Músicos do evento:\n");
//            for (Musico musico : musicosEscolhidos) {
//                conteudoBuilder.append("Nome: ").append(musico.getNome()).append("\n")
//                    .append("Instrumento: ").append(musico.getInstrumentoQueToca()).append("\n")
//                    .append("Celular: ").append(musico.getCelular()).append("\n")
//                    .append("Email: ").append(musico.getEmail()).append("\n\n");
//            }

            // ----------- CUSTOS ----------- //
            conteudoBuilder.append("Informações de custos:\n")
                .append("Custo de transporte de equipamentos: R$XXXX,XX\n")
                .append("Custo alimentício: R$XXXX,XX\n")
                .append("Cache dos músicos: R$XXXX,XX\n")
                .append("Custo de aluguel dos equipamentos: R$XXXX,XX\n")
                .append("TOTAL: R$XXXX,XX\n");

            // ----------- ENVIO ----------- //
            conteudoBuilder.append("ENVIAR PDF:\n")
                .append("-> por email\n")
                .append("-> por whatsapp\n");

            // Centraliza a string construída em uma só
            String conteudo = conteudoBuilder.toString();


            File pdf = PdfUtils.gerarPdfSimples(
                    this,
                    "orcamento_" + orcamento.getId(),
                    conteudo
            );

            if (pdf == null || !pdf.exists()) {
                Toast.makeText(this, "Erro ao gerar PDF!", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri uri = FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".fileprovider",
                    pdf
            );

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Nenhum app de PDF encontrado.", LENGTH_LONG).show();
            }
        });


        // Voltar para a tela anterior
        btnVoltarT6.setOnClickListener(evt -> {
            startActivity(new Intent(this, Tela_05_SolicitacoesGestor.class));
            finish();
        });
    }

    private void aparicaoDosBotoes() {
        // Gerencia a aparição dos botões
        if (orcamento.getStatus().equals("aberta")) {
            btnLiberarServicoParaMusicos.setVisibility(VISIBLE);
            btnBaixarContrato.setVisibility(INVISIBLE);
        }
        else {
            btnLiberarServicoParaMusicos.setVisibility(INVISIBLE);
            btnBaixarContrato.setVisibility(VISIBLE);
        }
    }

}