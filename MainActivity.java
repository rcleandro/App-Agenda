package com.example.ava2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Iterator;
import java.util.List;


public abstract class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    Intent intent;
    public static final int ACTIVITY_REQUEST_CONTATO = 1;
    private Contato_DAO dao;
    private String[] contatos;
    private long[] idContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);
        setTitle("Banco de Dados com SQLite!");
        dao = new Contato_DAO(this);
        dao.open();
        lista.setOnItemClickListener(this);
    }

    @Override
    protected void onResume () {
        dao.open ();
        super.onResume ();
        List<Contato> listaContatos = dao.getAll();
        contatos = new String[listaContatos.size()];
        idContatos = new long[listaContatos.size()];
        int i =0;
        Iterator<Contato> iterator = listaContatos.iterator();

        while (iterator.hasNext()) {
            Contato aux = new Contato();
            aux = (Contato) iterator.next();
            contatos[i] = aux.textoLista();
            idContatos[i] = aux.getId();
            i++;
        }

        ArrayAdapter<String > adapter = new ArrayAdapter<String >(
                this ,
                android.R.layout.simple_list_item_1 , contatos);

        lista.setAdapter( adapter );
    }

    @Override
    protected void onPause () {

        dao.close ();
        super.onPause ();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long ident) {
        long id = idContatos[position];
        intent = new Intent(getApplicationContext(), TratarContato.class);
        intent.putExtra("acao", 0);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void incluirContato(View v){
        intent = new Intent(getApplicationContext(), TratarContato.class);
        intent.putExtra("acao", -1);
        intent.putExtra("id", 0L);
        startActivity(intent);
    }

    public void sair(View v){
        finish();
    }
}