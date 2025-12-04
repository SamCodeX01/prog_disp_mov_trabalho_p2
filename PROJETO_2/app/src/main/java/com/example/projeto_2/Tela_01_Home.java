package com.example.projeto_2;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projeto_2.classes.OrcamentoDAO;

public class Tela_01_Home extends AppCompatActivity {
    private Button btnOrcamento, btnIntranet;
    private TextView txtSobre;


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

        txtSobre = findViewById(R.id.txtSobre);
    }

    private void listeners() {

        // Botão INTRANET (login)
        btnIntranet.setOnClickListener(evt -> {

            // Verifica se tem no mínimo 1 orçamento solicitado antes de mostrar o sistema
            if (new OrcamentoDAO(this).listarOrcamentos().isEmpty())
                Toast.makeText(this, "Nenhuma solicitação encontrada! Cadastre primeiro (min 1)", LENGTH_SHORT).show();
            else
                startActivity(new Intent(this, Tela_03_Login.class));
        });

        // Botão de SOLICITAÇÃO DE ORÇAMENTO
        btnOrcamento.setOnClickListener(evt -> {
            startActivity(new Intent(this, Tela_02_Orcamento.class));
        });


        txtSobre.setOnClickListener(evt -> {
            startActivity(new Intent(this, Tela_SobreActivity.class));
        });

    }

}