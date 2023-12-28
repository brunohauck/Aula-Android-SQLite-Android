package com.example.lock.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.lock.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private final String TABLE_CLIENTES = "Clientes";
    private DbGateway gw;

    public ClienteDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvar(String nome, String sexo, String uf, boolean vip){
        ContentValues cv = new ContentValues();
        cv.put("Nome", nome);
        cv.put("Sexo", sexo);
        cv.put("UF", uf);
        cv.put("Vip", vip ? 1 : 0);
        return gw.getDatabase().insert(TABLE_CLIENTES, null, cv) > 0;
    }
    public List<Cliente> retornarTodos(){
        List<Cliente> clientes = new ArrayList<>();
        try {
            Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Clientes", null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
                    @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex("Nome"));
                    @SuppressLint("Range") String sexo = cursor.getString(cursor.getColumnIndex("Sexo"));
                    @SuppressLint("Range") String uf = cursor.getString(cursor.getColumnIndex("UF"));
                    @SuppressLint("Range") boolean vip = cursor.getInt(cursor.getColumnIndex("Vip")) > 0;
                    clientes.add(new Cliente(id, nome, sexo, uf, vip));
                }
            }
            cursor.close();
        } catch (SQLiteConstraintException e) {
            Log.d("Erro", "failure to insert word,", e);
        }

        return clientes;
    }

    public Cliente retornarUltimo(){

        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Clientes ORDER BY ID DESC", null);
        if(cursor.getCount()>0){
            if(cursor.moveToFirst()){
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
                @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex("Nome"));
                @SuppressLint("Range") String sexo = cursor.getString(cursor.getColumnIndex("Sexo"));
                @SuppressLint("Range") String uf = cursor.getString(cursor.getColumnIndex("UF"));
                @SuppressLint("Range") boolean vip = cursor.getInt(cursor.getColumnIndex("Vip")) > 0;
                cursor.close();
                return new Cliente(id, nome, sexo, uf, vip);
            }

        }


        return null;
    }
}