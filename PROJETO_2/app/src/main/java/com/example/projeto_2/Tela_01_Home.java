package com.example.projeto_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_01_Home extends AppCompatActivity {
    private Button btnOrcamento, btnIntranet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela01_home);

        mainConfig();
        listeners();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mainConfig() {
        btnOrcamento = findViewById(R.id.btnOrcamento);
        btnIntranet = findViewById(R.id.btnIntranet);
    }

    private void listeners() {

        // Botão INTRANET (login)
        btnIntranet.setOnClickListener(evt -> {
            startActivity(new Intent(this, Tela_03_Login.class));
            finish();
        });

        // Botão de SOLICITAÇÃO DE ORÇAMENTO
        btnOrcamento.setOnClickListener(evt -> {
            startActivity(new Intent(this, Tela_02_Orcamento.class));
        });

    }

}