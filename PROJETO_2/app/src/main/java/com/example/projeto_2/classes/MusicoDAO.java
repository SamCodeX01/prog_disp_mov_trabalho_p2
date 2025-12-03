package com.example.projeto_2.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class MusicoDAO extends SQLiteOpenHelper {
    public static final String TABELA_MUSICO = "musico";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_CPF = "cpf";
    public static final String COLUNA_GENERO = "genero";
    public static final String COLUNA_INSTRUMENTOQUETOCA = "instrumentoQueToca";
    public static final String COLUNA_CELULAR = "celular";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_ENDERECO = "endereco";

    public MusicoDAO (Context context) {
        super(context, InfoBD.NOME_BANCO, null, InfoBD.VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        sqliteDatabase.execSQL(
                "CREATE TABLE " + TABELA_MUSICO + "(" +
                        COLUNA_ID      + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_NOME + " TEXT NOT NULL, " +
                        COLUNA_CPF + " TEXT NOT NULL, " +
                        COLUNA_GENERO + " TEXT NOT NULL, " +
                        COLUNA_INSTRUMENTOQUETOCA + " TEXT NOT NULL, " +
                        COLUNA_CELULAR + " TEXT NOT NULL, " +
                        COLUNA_EMAIL + " TEXT NOT NULL, " +
                        COLUNA_ENDERECO + " TEXT NOT NULL) "
        );
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqliteDatabase, int i, int i1) {
        sqliteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_MUSICO);
        onCreate(sqliteDatabase);
    }

    public void salvarMusico (Musico musico) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores;
        valores = new ContentValues();
        valores.put(COLUNA_NOME, musico.getNome());
        valores.put(COLUNA_CPF, musico.getCpf());
        valores.put(COLUNA_GENERO, musico.getGenero());
        valores.put(COLUNA_INSTRUMENTOQUETOCA, musico.getInstrumentoQueToca());
        valores.put(COLUNA_CELULAR, musico.getCelular());
        valores.put(COLUNA_EMAIL, musico.getEmail());
        valores.put(COLUNA_ENDERECO, musico.getEndereco());

        db.insert(TABELA_MUSICO, null, valores);
        db.close();
    }

//    HashMap<String, int> meuDicionario;
//
//    meuDicinario["nome"] = 5;
//    meuDicinario["idade"] = 5;
//
//
//    key : value
//
//
//    {
//        "nome" : "Samuel",
//        "idade" : 50
//    }

    public void atualizarOrcamento (Musico musico) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores;
        valores = new ContentValues();
        valores.put(COLUNA_NOME, musico.getNome());
        valores.put(COLUNA_CPF, musico.getCpf());
        valores.put(COLUNA_GENERO, musico.getGenero());
        valores.put(COLUNA_INSTRUMENTOQUETOCA, musico.getInstrumentoQueToca());
        valores.put(COLUNA_CELULAR, musico.getCelular());
        valores.put(COLUNA_EMAIL, musico.getEmail());
        valores.put(COLUNA_ENDERECO, musico.getEndereco());

        //db.insert(TABELA_MUSICO, null, valores);
        String parametro [] = {String.valueOf(musico.getId())};
        db.update(TABELA_MUSICO, valores, "id = ?", parametro);
        db.close();
    }

    public void excluirOrcamento (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String parametro [] = {String.valueOf(id)};
        db.delete(TABELA_MUSICO, "id = ? ", parametro);
        db.close();
    }

    public Musico consultarMusicoPorNome (String pnome) {
        Musico musico;
        musico = null;
        String parametro[] = { pnome };
        String campos[] = {"id, nome, cpf, genero, instrumentoQueToca, celular, email, endereco"};
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cr = db.query (TABELA_MUSICO,
                campos,
                "nome = ?",
                parametro,
                null,
                null,
                null,
                null
        );

        if (cr.moveToFirst()) {
            musico = new Musico();
            musico.setId(cr.getInt(0));
            musico.setNome(cr.getString(1));
            musico.setCpf(cr.getString(2));
            musico.setGenero(cr.getString(3));
            musico.setInstrumentoQueToca(cr.getString(4));
            musico.setCelular(cr.getString(5));
            musico.setEmail(cr.getString(6));
            musico.setEndereco(cr.getString(7));
        }

        db.close();
        return musico;
    }
}
