package com.example.lock.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lock.R;
import com.example.lock.adapter.Adapter;
import com.example.lock.adapter.ClienteAdapter;
import com.example.lock.helper.ClienteDAO;
import com.example.lock.model.Senha;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;
    private FloatingActionButton fab;
    //RecyclerView recyclerView;


    RecyclerView recyclerView;
    ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        /*

        Adapter adapter = new Adapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter( adapter );

        SQLiteDatabase bancoDados1 = openOrCreateDatabase("app", MODE_PRIVATE, null);
        bancoDados1.execSQL("CREATE TABLE IF NOT EXISTS configuracoes(" + " id INTEGER PRIMARY KEY AUTOINCREMENT" + " , senha varchar" + ")");
        bancoDados1.execSQL("CREATE TABLE IF NOT EXISTS senha(" + " id INTEGER PRIMARY KEY AUTOINCREMENT" + " , site varchar" + ", senha varchar" + ")");
        bancoDados1.close();*/

        configurarRecycler();
    }


    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        ClienteDAO dao = new ClienteDAO(this);
        adapter = new ClienteAdapter(dao.retornarTodos());
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


}