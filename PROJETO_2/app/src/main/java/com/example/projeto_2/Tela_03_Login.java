package com.example.projeto_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projeto_2.classes.Orcamento;
import com.example.projeto_2.classes.OrcamentoDAO;

public class Tela_03_Login extends AppCompatActivity {
    private Button btnLogin;
    private TextView txtCadastrarMusico;
    private EditText etUsuario, etPassword;
    private OrcamentoDAO orc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela03_login);

        mainConfig();
        listeners();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mainConfig() {

        // Teste de SELECT //////////////////////////////////////////////
        orc = new OrcamentoDAO(this);
        Orcamento obj = orc.consultarOrcamentoPorNome("Samuel"); //
        Log.e("TESTE_BANCO", obj.getEmail());
        // //////////////////////////////////////////////////////////////

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtCadastrarMusico = findViewById(R.id.txtCadastroMusico);
    }
    private void listeners() {

        // Botão de LOGIN
        btnLogin.setOnClickListener(evt -> {
            String user = etUsuario.getText().toString();
            String password = etPassword.getText().toString();

//            if (user.equals("Admin") && password.equals("123"))
                startActivity(new Intent(this, Tela_05_SolicitacoesGestor.class));
        });

        // Botão de CADASTRO DE MÚSICO
        txtCadastrarMusico.setOnClickListener(evt -> {
            startActivity(new Intent(this, Tela_04_CadastroMusico.class));
        });


        // FAZER BOTÃO DE VOLTAR? ///////////////////////////////

    }

}