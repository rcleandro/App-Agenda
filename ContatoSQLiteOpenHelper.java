package com.example.ava2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContatoSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String TABELA = " Contato ";
    public static final String COLUNA_ID = " id ";
    public static final String COLUNA_NOME = " nome ";
    public static final String COLUNA_TELEFONE = " telefone ";
    public static final String COLUNA_EMAIL = " email ";
    public static final String COLUNA_ANIVERSARIO = " aniversario ";
    private static final String DATABASE_NAME = "contatos.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CRIAR_BANCO = " create table "
            + TABELA + "("
            + COLUNA_ID + " integer primary key autoincrement , "
            + COLUNA_NOME + " text not null , "
            + COLUNA_TELEFONE + " text not null , "
            + COLUNA_EMAIL + " text not null , "
            + COLUNA_ANIVERSARIO + " text not null ) ;";

    public ContatoSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate ( SQLiteDatabase database ) {
        database.execSQL ( CRIAR_BANCO );
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL (" DROP TABLE IF EXISTS " + TABELA );
        onCreate (db);
    }
}

