package com.example.projeto_2;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtCadastrarMusico = findViewById(R.id.txtCadastroMusico);
    }
    private void listeners() {

        // Botão de LOGIN
        btnLogin.setOnClickListener(evt -> {

            // Para facilitar e tornar menos poluído
            String user = etUsuario.getText().toString();
            String password = etPassword.getText().toString();

            // Controle para melhor funcionamento do sistema
            Musico.queroQtdMusicos = 2;
            int qtdMinMusicos = Musico.queroQtdMusicos;
            int qtdMusicosCadastrados = new MusicoDAO(this).listarMusicos().size();

            if (user.equals("Admin") && password.equals("123"))
            {
                if (qtdMusicosCadastrados >= qtdMinMusicos)
                    startActivity(new Intent(this, Tela_05_SolicitacoesGestor.class));
                else
                    Toast.makeText(this, "Músicos insulficientes (mín " + qtdMinMusicos + "). \nCadastre mais músicos no sistema!" , LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Usuário ou senha errados!", LENGTH_SHORT).show();
        });

        // Botão de CADASTRO DE MÚSICO
        txtCadastrarMusico.setOnClickListener(evt -> {
            startActivity(new Intent(this, Tela_04_CadastroMusico.class));
        });


        // FAZER BOTÃO DE VOLTAR? ///////////////////////////////

    }

}