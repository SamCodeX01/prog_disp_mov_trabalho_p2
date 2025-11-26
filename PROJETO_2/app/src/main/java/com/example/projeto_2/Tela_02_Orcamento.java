package com.example.projeto_2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_02_Orcamento extends AppCompatActivity {
    private Button btnCancelarOrcamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela02_orcamento);

        mainConfig();
        listeners();
        Log.e("ERRO", "Chegou aq!");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mainConfig() {
        btnCancelarOrcamento = findViewById(R.id.btnCancelarOrcamento);
    }
    private void listeners() {

        // SAIR
        btnCancelarOrcamento.setOnClickListener(evt -> {
            finish();
        });

    }

}