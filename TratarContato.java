package com.example.ava2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TratarContato extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    Button bt1, bt2;

    private int acao;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratar_contato);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);

        acao = getIntent().getExtras().getInt("acao");
        id = getIntent().getExtras().getLong("id");

        if (acao == -1) {

            setTitle("Inserir Contato");
            bt1.setText("Incluir");
            bt2.setEnabled(false);
            ed1.setText("Nome do Contato");
            ed2.setText("Telefone");
            ed3.setText("E-Mail");
            ed4.setText("Aniversário");
        } else {

            setTitle("Alterar ou Excluir Contato");

            Contato aux = new Contato();
            Contato_DAO dao = new Contato_DAO(this);
            dao.open();
            aux = dao.buscar(id);

            ed1.setText(aux.getNome());
            ed2.setText(aux.getTelefone());
            ed3.setText(aux.getEmail());
            ed4.setText(aux.getAniversario());

            dao.close();
        }
    }

    public void alterarInserir(View v) {
        String nome, telefone, email, aniversario;

        nome = ed1.getText().toString();
        telefone = ed2.getText().toString();
        email = ed3.getText().toString();
        aniversario = ed4.getText().toString();

        Contato_DAO dao = new Contato_DAO(this);

        dao.open();


        if (acao == -1) {
            dao.inserir(nome, telefone, email, aniversario);
        }
        else{
            dao.alterar(id, nome, telefone, email, aniversario);
        }
        dao.close();
        finish();
    }

    public void excluir(View v) {
        if (acao == 0) {
            Contato_DAO dao = new Contato_DAO(this);
            dao.open();
            dao.apagar(id);
            dao.close();
        }

        finish();
    }
    public void voltar(View v) {
        finish();
    }
}

