package com.example.ava2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class Contato_DAO {

    private SQLiteDatabase database;

    private String [] columns = { ContatoSQLiteOpenHelper.COLUNA_ID,
            ContatoSQLiteOpenHelper.COLUNA_NOME,
            ContatoSQLiteOpenHelper.COLUNA_TELEFONE,
            ContatoSQLiteOpenHelper.COLUNA_EMAIL,
            ContatoSQLiteOpenHelper.COLUNA_ANIVERSARIO };

    private ContatoSQLiteOpenHelper sqliteOpenHelper ;

    public Contato_DAO(Context context) {
        sqliteOpenHelper = new ContatoSQLiteOpenHelper(context);
    }

    public void open () throws SQLException {
        database = sqliteOpenHelper.getWritableDatabase();
    }

    public void close() {
        sqliteOpenHelper.close ();
    }

    public void inserir ( String nome, String telefone, String email, String aniversario) {
        ContentValues values = new ContentValues ();
        values.put ( ContatoSQLiteOpenHelper.COLUNA_NOME, nome);
        values.put ( ContatoSQLiteOpenHelper.COLUNA_TELEFONE, telefone);
        values.put ( ContatoSQLiteOpenHelper.COLUNA_EMAIL, email);
        values.put ( ContatoSQLiteOpenHelper.COLUNA_ANIVERSARIO, aniversario);

        long insertId = database.insert ( ContatoSQLiteOpenHelper.TABELA
                ,
                null , values );
    }

    public void alterar(long id, String nome, String telefone, String email, String aniversario){
        ContentValues values = new ContentValues ();
        values.put ( ContatoSQLiteOpenHelper.COLUNA_NOME , nome );
        values.put ( ContatoSQLiteOpenHelper.COLUNA_TELEFONE, telefone);
        values.put ( ContatoSQLiteOpenHelper.COLUNA_EMAIL, email);
        values.put ( ContatoSQLiteOpenHelper.COLUNA_ANIVERSARIO, aniversario);
        database.update(ContatoSQLiteOpenHelper.TABELA , values,
                ContatoSQLiteOpenHelper.COLUNA_ID + "=" + id, null);
    }

    public void apagar ( long id ) {
        database.delete ( ContatoSQLiteOpenHelper.TABELA ,
                ContatoSQLiteOpenHelper.COLUNA_ID
                        + " = " + id , null );
    }

    public Contato buscar ( long id ) {
        Cursor cursor = database.query( ContatoSQLiteOpenHelper.TABELA,
                columns , ContatoSQLiteOpenHelper.COLUNA_ID + " = " + id,
                null , null , null , null );
        cursor . moveToFirst ();
        Contato contato = new Contato ();
        contato.setId ( cursor.getLong (0) );
        contato.setNome ( cursor.getString (1) );
        contato.setTelefone ( cursor.getString (2) );
        contato.setEmail ( cursor.getString (3) );
        contato.setAniversario ( cursor.getString (4) );
        cursor.close();
        return contato ;
    }

    public List<Contato> getAll () {
        List <Contato> contatos = new ArrayList<Contato>() ;
        Cursor cursor = database . query ( ContatoSQLiteOpenHelper .
                TABELA , columns , null , null , null , null , null );
        cursor . moveToFirst ();

        while (!cursor.isAfterLast ()) {
            Contato contato = new Contato ();
            contato.setId ( cursor . getLong (0) );
            contato.setNome ( cursor . getString (1) );
            contato.setTelefone ( cursor.getString (2) );
            contato.setEmail ( cursor.getString (3) );
            contato.setAniversario ( cursor.getString (4) );
            contatos.add ( contato );
            cursor . moveToNext ();
        }

        cursor . close ();
        return contatos;
    }
}
