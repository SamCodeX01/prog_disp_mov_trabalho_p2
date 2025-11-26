package com.example.projeto_2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projeto_2.classes.Musico;
import com.example.projeto_2.classes.MusicoDAO;
import com.example.projeto_2.classes.Orcamento;

public class Tela_04_CadastroMusico extends AppCompatActivity {
    private MusicoDAO mus; // Executa as querrys no SQLite
    private Button btnCancelarCadastroMusico, btnCadastrarMusico;
    private EditText txtCpfMusico, txtNomeMusico, txtInstrumentoMusico,
            txtGenero, txtCelularMusico, txtEmailMusico, txtEnderecoMusico;
    private EditText[] paraValidar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela04_cadastro_musico);

        mainConfig();
        listeners();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mainConfig() {
        txtCpfMusico = findViewById(R.id.txtCpfMusico);
        txtNomeMusico = findViewById(R.id.txtNomeMusico);
        txtInstrumentoMusico = findViewById(R.id.txtInstrumentoMusico);
        txtGenero = findViewById(R.id.txtGenero);
        txtCelularMusico = findViewById(R.id.txtCelularMusico);
        txtEmailMusico = findViewById(R.id.txtEmailMusico);
        txtEnderecoMusico = findViewById(R.id.txtEnderecoMusico);

        btnCancelarCadastroMusico = findViewById(R.id.btnCancelarCadastroMusico);
        btnCadastrarMusico = findViewById(R.id.btnCadastrarMusico);

        paraValidar = new EditText[] {
            txtCpfMusico, txtNomeMusico, txtInstrumentoMusico, txtGenero,
            txtCelularMusico, txtEmailMusico, txtEnderecoMusico
        };
    }
    private void listeners() {

        // CADASTRAR MÚSICO
        btnCadastrarMusico.setOnClickListener(evt -> {
            if (validarCampos()) {
                try {
                    // VERIFICAR SE O MÚSICO JÁ EXISTE PELO CPF!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                    Musico obj = new Musico();
                    obj.setNome(txtCpfMusico.getText().toString());
                    obj.setNome(txtNomeMusico.getText().toString());
                    obj.setNome(txtInstrumentoMusico.getText().toString());
                    obj.setNome(txtGenero.getText().toString());
                    obj.setNome(txtCelularMusico.getText().toString());
                    obj.setNome(txtEmailMusico.getText().toString());
                    obj.setNome(txtEnderecoMusico.getText().toString());
                    mus.salvarMusico(obj);

                    Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();

                } catch (Exception e) {
                    Toast.makeText(this, "Algo de errado não está certo!", Toast.LENGTH_SHORT).show();
                }
            } else Toast.makeText(this, "Faltam campos a ser preenchidos!", Toast.LENGTH_SHORT).show();
        });

        // SAIR
        btnCancelarCadastroMusico.setOnClickListener(evt -> {finish();});

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