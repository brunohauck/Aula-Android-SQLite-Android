package com.example.lock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lock.R;
import com.example.lock.helper.ClienteDAO;
import com.example.lock.model.Cliente;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private EditText editSite;
    private EditText editSenha;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editSite = findViewById(R.id.editSite);
        editSenha = findViewById(R.id.editSenha);
        buttonSalvar = findViewById(R.id.buttonSalvar);

        /*
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);
                    bancoDados.execSQL("INSERT INTO senha (id, site, senha) VALUES (NULL, " + editSite.getText().toString() + ", " + editSenha.getText().toString() + ")");
                    bancoDados.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                finish();
            }
        });*/

        //Button btnSalvar = (Button)findViewById(R.id.btnSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //carregando os campos
                //EditText txtNome = (EditText)findViewById(R.id.txtNome);
                //Spinner spnEstado = (Spinner)findViewById(R.id.spnEstado);
                //RadioGroup rgSexo = (RadioGroup)findViewById(R.id.rgSexo);
                //CheckBox chkVip = (CheckBox)findViewById(R.id.chkVip);

                //pegando os valores
                String nome = "Bruno Hauck";
                String uf = "MG";
                boolean vip = true;
                String sexo = "M";

                //salvando os dados
                ClienteDAO dao = new ClienteDAO(getBaseContext());
                boolean sucesso = dao.salvar(nome, sexo, uf, vip);
                if(sucesso) {
                    //limpa os campos
                    //txtNome.setText("");
                    //rgSexo.setSelected(false);
                    //spnEstado.setSelection(0);
                    //chkVip.setChecked(false);
                    List<Cliente> clientes = new ArrayList<>();
                    clientes = dao.retornarTodos();

                    Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    

                    //findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                    //findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                    //findViewById(R.id.fab).setVisibility(View.VISIBLE);
                }else{
                    Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}