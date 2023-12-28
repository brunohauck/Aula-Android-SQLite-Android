package com.example.lock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lock.R;

public class NovaActivity extends AppCompatActivity {

    private EditText editTextPassword;
    private Button buttonEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova);

        try{

            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS usuario (id INT(1), password VARCHAR) ");
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS senha (id INT(11) PRIMARY KEY AUTOINCREMENT, password VARCHAR) ");

        }catch(Exception e){
            e.printStackTrace();
        }

        editTextPassword = findViewById(R.id.editTextPassword);
        buttonEntrar = findViewById(R.id.buttonEntrar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NovaActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}