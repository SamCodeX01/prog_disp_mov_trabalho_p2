package com.example.projeto_2.classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "bdservicos";
    public static final int VERSAO_BANCO = 7;

    public BancoHelper(Context ctx) {
        super(ctx, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Cria TODAS as tabelas aqui
        db.execSQL(MusicoDAO.SQL_CREATE);
        db.execSQL(OrcamentoDAO.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS musico");
        db.execSQL("DROP TABLE IF EXISTS orcamento");
        onCreate(db);
    }
}
