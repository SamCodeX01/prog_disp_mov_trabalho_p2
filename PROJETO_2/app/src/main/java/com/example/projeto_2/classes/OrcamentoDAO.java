package com.example.projeto_2.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrcamentoDAO extends SQLiteOpenHelper {
    public static final String TABELA_ORCAMENTO = "orcamento";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_CPF = "cpf";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_CELULAR = "celular";
    public static final String COLUNA_ENDERECO = "endereco";
    public static final String COLUNA_NOMEPACOTE = "nomePacote";
    public static final String COLUNA_DATAEVENTO = "dataEvento";
    public static final String COLUNA_QTDCONVIDADOS = "qtdConvidados";
    public static final String COLUNA_HORARIOINICIO = "horarioInicio";
    public static final String COLUNA_HORARIOTERMINO = "horarioTermino";
    public static final String COLUNA_ENDERECOEVENTO = "enderecoEvento";

    public OrcamentoDAO (Context context) {
        super(context, InfoBD.NOME_BANCO, null, InfoBD.VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        sqliteDatabase.execSQL(
                "CREATE TABLE " + TABELA_ORCAMENTO + "(" +
                        COLUNA_ID             + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_NOME           + " TEXT NOT NULL, " +
                        COLUNA_CPF            + " TEXT NOT NULL, " +
                        COLUNA_EMAIL          + " TEXT NOT NULL, " +
                        COLUNA_CELULAR        + " TEXT NOT NULL, " +
                        COLUNA_ENDERECO       + " TEXT NOT NULL, " +
                        COLUNA_NOMEPACOTE     + " TEXT NOT NULL, " +
                        COLUNA_DATAEVENTO     + " TEXT NOT NULL, " +
                        COLUNA_QTDCONVIDADOS  + " TEXT NOT NULL, " +
                        COLUNA_HORARIOINICIO  + " TEXT NOT NULL, " +
                        COLUNA_HORARIOTERMINO + " TEXT NOT NULL, " +
                        COLUNA_ENDERECOEVENTO + " TEXT NOT NULL) "
        );
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqliteDatabase, int i, int i1) {
    }

    public void salvarOrcamento (Orcamento orcamento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores;
        valores = new ContentValues();

        valores.put(COLUNA_NOME, orcamento.getNome());
        valores.put(COLUNA_CPF, orcamento.getCpf());
        valores.put(COLUNA_EMAIL, orcamento.getEmail());
        valores.put(COLUNA_CELULAR, orcamento.getCelular());
        valores.put(COLUNA_ENDERECO, orcamento.getEndereco());
        valores.put(COLUNA_NOMEPACOTE, orcamento.getNomePacote());
        valores.put(COLUNA_DATAEVENTO, orcamento.getDataEvento());
        valores.put(COLUNA_QTDCONVIDADOS, orcamento.getQtdConvidados());
        valores.put(COLUNA_HORARIOINICIO, orcamento.getHorarioInicio());
        valores.put(COLUNA_HORARIOTERMINO, orcamento.getHorarioTermino());
        valores.put(COLUNA_ENDERECOEVENTO, orcamento.getEnderecoEvento());

        db.insert(TABELA_ORCAMENTO, null, valores);
        db.close();
    }

    public void atualizarOrcamento (Orcamento orcamento) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores;
        valores = new ContentValues();

        valores.put(COLUNA_NOME, orcamento.getNome());
        valores.put(COLUNA_CPF, orcamento.getCpf());
        valores.put(COLUNA_EMAIL, orcamento.getEmail());
        valores.put(COLUNA_CELULAR, orcamento.getCelular());
        valores.put(COLUNA_ENDERECO, orcamento.getEndereco());
        valores.put(COLUNA_NOMEPACOTE, orcamento.getNomePacote());
        valores.put(COLUNA_DATAEVENTO, orcamento.getDataEvento());
        valores.put(COLUNA_QTDCONVIDADOS, orcamento.getQtdConvidados());
        valores.put(COLUNA_HORARIOINICIO, orcamento.getHorarioInicio());
        valores.put(COLUNA_HORARIOTERMINO, orcamento.getHorarioTermino());
        valores.put(COLUNA_ENDERECOEVENTO, orcamento.getEnderecoEvento());

        //db.insert(TABELA_ORCAMENTO, null, valores);
        String parametro [] = {String.valueOf(orcamento.getId())};
        db.update(TABELA_ORCAMENTO, valores, "id = ?", parametro);
        db.close();
    }

    public void excluirOrcamento (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String parametro [] = {String.valueOf(id)};
        db.delete(TABELA_ORCAMENTO, "id = ? ", parametro);
        db.close();
    }

    public Orcamento consultarOrcamentoPorNome (String pnome) {
        Orcamento orcamento;
        orcamento = new Orcamento();
        String parametro[] = { pnome };
        String campos[] = {"id, nome, cpf, email, celular, endereco, nomePacote, dataEvento, qtdConvidados, horarioInicio, horarioTermino, enderecoEvento"};
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cr = db.query (TABELA_ORCAMENTO,
                campos,
                "nome = ?",
                parametro,
                null,
                null,
                null,
                null
        );

        if (cr.moveToFirst()) {
            orcamento.setId(cr.getInt(0));
            orcamento.setNome(cr.getString(1));
            orcamento.setCpf(cr.getString(2));
            orcamento.setEmail(cr.getString(3));
            orcamento.setCelular(cr.getString(4));
            orcamento.setEndereco(cr.getString(5));
            orcamento.setNomePacote(cr.getString(6));
            orcamento.setDataEvento(cr.getString(7));
            orcamento.setQtdConvidados(cr.getString(8));
            orcamento.setHorarioInicio(cr.getString(9));
            orcamento.setHorarioTermino(cr.getString(10));
            orcamento.setEnderecoEvento(cr.getString(11));
        }

        db.close();
        return orcamento;
    }
}
