package com.example.projeto_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_03_Login extends AppCompatActivity {
    private Button btnLogin;
    private TextView txtCadastroMusico;


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
        btnLogin = findViewById(R.id.btnLogin);
        txtCadastroMusico = findViewById(R.id.txtCadastroMusico);
    }
    private void listeners() {

        // Botão de LOGIN
        btnLogin.setOnClickListener(evt -> {
            startActivity(new Intent(this, Tela_05_SolicitacoesGestor.class));
        });

        // Botão de CADASTRO DE MÚSICO
        txtCadastroMusico.setOnClickListener(evt -> {
            startActivity(new Intent(this, Tela_04_CadastroMusico.class));
        });

        // FAZER BOTÃO DE VOLTAR?

    }

}