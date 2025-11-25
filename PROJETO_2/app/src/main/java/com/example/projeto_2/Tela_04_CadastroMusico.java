package com.example.projeto_2;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_04_CadastroMusico extends AppCompatActivity {
    private Button btnCancelarCadastroMusico;

    // FALTA PEGAR OS CAMPOS


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        mainConfig();
        listeners();

        setContentView(R.layout.activity_tela04_cadastro_musico);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mainConfig() {
        btnCancelarCadastroMusico = findViewById(R.id.btnCancelarCadastroMusico);
    }
    private void listeners() {

        // SAIR
        btnCancelarCadastroMusico.setOnClickListener(evt -> {finish();});

    }

}