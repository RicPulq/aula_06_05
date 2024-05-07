package br.com.monkey.aula_06_05;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    COMPONENTES DA INTERFACE
    EditText nome, cpf, telefone, busca_nome;
    Button inserir, consultar, buscaNome;
    ListView listViewUsers;

//    MANIPULAÇÃO DO DB


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        COMPONENTES COM ELEMENTO
        nome = findViewById(R.id.nome);
        cpf = findViewById(R.id.cpf);
        telefone = findViewById(R.id.telefone);
        busca_nome = findViewById(R.id.searchName);

        inserir = findViewById(R.id.btnInserir);
        consultar = findViewById(R.id.btnConsultar);
        buscaNome = findViewById(R.id.btnBuscar);

        listViewUsers = findViewById(R.id.listViewUsers);

//        INSTÂNCIA DO GERENCIADOR - HELPER
        DBHelper db = new DBHelper(this);

//        BOTÃO COM A AÇÃO DE INSERÇÃO
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nome.getText().toString();
                String userCPF = cpf.getText().toString();
                String userTelefone = telefone.getText().toString();

              boolean response = db.insertUserData(userName, userCPF, userTelefone);

              if (response){
                  Toast.makeText(MainActivity.this,"DADOS INSERIDOS COM SUCESSO", Toast.LENGTH_LONG).show();
              }else{
                  Toast.makeText(MainActivity.this, "FALHOU", Toast.LENGTH_LONG).show();
              }
            }
        });

//        BOTÃO COM A AÇÃO DE CONSULTAR
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> userList = new ArrayList<>();
                Cursor cursor = db.getUserData();

                if (cursor.getCount()==0){
                    Toast.makeText(MainActivity.this,"BANCO DE DADOS VAZIO", Toast.LENGTH_LONG).show();
                }else{
                    while(cursor.moveToNext()){
                        userList.add("Nome: " + cursor.getString(0));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, userList);
                    listViewUsers.setAdapter(adapter);

                }
            }
        });

//        BOTÃO COM A AÇÃO DE INSERÇÃO

    }
}