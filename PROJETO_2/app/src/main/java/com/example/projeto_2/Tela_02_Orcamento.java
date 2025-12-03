package com.example.projeto_2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projeto_2.classes.Orcamento;
import com.example.projeto_2.classes.OrcamentoDAO;

public class Tela_02_Orcamento extends AppCompatActivity {
    private OrcamentoDAO orc; // Executa as querrys no SQLite
    private Button btnCancelarOrcamento, btnSolicitarOrcamento;
    private EditText txtNome, txtCpf, txtEmail, txtCelular,
            txtEndereco, txtNomePacote, txtDataEvento, txtQtdConvidados,
            txtHorarioInicio, txtHorarioTermino, txtEnderecoEvento;
    private EditText[] paraValidar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela02_orcamento);

        mainConfig();
        listeners();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mainConfig() {
        orc = new OrcamentoDAO(this);

        txtNome = findViewById(R.id.txtNome);
        txtCpf = findViewById(R.id.txtCpf);
        txtEmail = findViewById(R.id.txtEmail);
        txtCelular = findViewById(R.id.txtCelular);
        txtEndereco = findViewById(R.id.txtEndereco);
        txtNomePacote = findViewById(R.id.txtNomePacote);
        txtDataEvento = findViewById(R.id.txtDataEvento);
        txtQtdConvidados = findViewById(R.id.txtQtdConvidados);
        txtHorarioInicio = findViewById(R.id.txtHorarioInicio);
        txtHorarioTermino = findViewById(R.id.txtHorarioTermino);
        txtEnderecoEvento = findViewById(R.id.txtEnderecoEvento);

        btnSolicitarOrcamento = findViewById(R.id.btnSolicitarOrcamento);
        btnCancelarOrcamento = findViewById(R.id.btnCancelarOrcamento);

        // Serve para checar se tem algum campo vazio
        paraValidar = new EditText[] {
            txtNome, txtCpf, txtEmail, txtCelular,
            txtEndereco, txtNomePacote, txtDataEvento, txtQtdConvidados,
            txtHorarioInicio, txtHorarioTermino, txtEnderecoEvento
        };
    }
    private void listeners() {

        // SOLICITAR ORÇAMENTO
        btnSolicitarOrcamento.setOnClickListener(evt -> {
            if (validarCampos()) {
                try {
                    // VERIFICAR SE O CLIENTE JÁ EXISTE PELO CPF!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                    Orcamento obj = new Orcamento();
                    obj.setNome(txtNome.getText().toString());
                    obj.setCpf(txtCpf.getText().toString());
                    obj.setEmail(txtEmail.getText().toString());
                    obj.setCelular(txtCelular.getText().toString());
                    obj.setEndereco(txtEndereco.getText().toString());
                    obj.setNomePacote(txtNomePacote.getText().toString());
                    obj.setDataEvento(txtDataEvento.getText().toString());
                    obj.setQtdConvidados(txtQtdConvidados.getText().toString());
                    obj.setHorarioInicio(txtHorarioInicio.getText().toString());
                    obj.setHorarioTermino(txtHorarioTermino.getText().toString());
                    obj.setEnderecoEvento(txtEnderecoEvento.getText().toString());
                    obj.setStatus("aberta");
                    orc.salvarOrcamento(obj);

                    Toast.makeText(this, "Seu orçamento foi enviado!", Toast.LENGTH_SHORT).show();
                    finish();

                } catch (Exception e) {
                    Toast.makeText(this, "Algo de errado não está certo!", Toast.LENGTH_SHORT).show();
                }
            } else Toast.makeText(this, "Faltam campos a ser preenchidos!", Toast.LENGTH_SHORT).show();
        });

        // SAIR
        btnCancelarOrcamento.setOnClickListener(evt -> {
            finish();
        });
    }


    private boolean validarCampos() {
        // isEmpty() não funcionou!
        for (int i=0; i<paraValidar.length; i++) {
            if (paraValidar[i].getText().toString().equals(""))
                return false;
        }
        return true;
    }

}