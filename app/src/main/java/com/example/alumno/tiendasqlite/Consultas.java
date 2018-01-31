package com.example.alumno.tiendasqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Consultas extends AppCompatActivity implements View.OnClickListener {

    EditText codigo, precio, descripcion, stock;
    Button inicio, fin, adelante, atras, verTodo, verPorId;
    String NOMBREBASEDATOS = "tienda";
    Cursor consulta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        codigo = (EditText) findViewById(R.id.editCodigo);
        precio = (EditText) findViewById(R.id.editPrecio);
        descripcion = (EditText) findViewById(R.id.editDescripcion);
        stock = (EditText) findViewById(R.id.editStock);

        inicio = (Button) findViewById(R.id.btnInico);
        fin = (Button) findViewById(R.id.btnFin);
        adelante = (Button) findViewById(R.id.btnAdelante);
        atras = (Button) findViewById(R.id.btnAtras);
        verTodo = (Button) findViewById(R.id.btnVerTodo);
        verPorId = (Button) findViewById(R.id.btnVerPorId);

        inicio.setOnClickListener(this);
        fin.setOnClickListener(this);
        adelante.setOnClickListener(this);
        atras.setOnClickListener(this);
        verTodo.setOnClickListener(this);
        verPorId.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnVerTodo: {
                verTodo();
                break;
            }
            case R.id.btnVerPorId: {
                verPorId();
                break;
            }

            case R.id.btnInico: {
                irInicio();
                break;
            }

            case R.id.btnFin: {
                irFin();
                break;
            }
            case R.id.btnAtras: {
                irAtras();
                break;
            }
            case R.id.btnAdelante: {
                irAdelante();
                break;
            }

        }

    }

    public void verPorId() {
        Administracion admin = new Administracion(this, NOMBREBASEDATOS, null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        //Crear cursor
        String cod = codigo.getText().toString();
        consulta =db.rawQuery("SELECT descripcion,precio,stock FROM articulos WHERE codigo="+cod,null);
       //Si hay algun registro
        if(consulta.moveToFirst()==true){
            descripcion.setText(consulta.getString(0));
            precio.setText(consulta.getString(1));
            stock.setText(consulta.getString(2));
        }else{
            Toast.makeText(this, "No se encontro ese registro", Toast.LENGTH_SHORT).show();
        }

    }

    public void verTodo() {
        Administracion admin = new Administracion(this, NOMBREBASEDATOS, null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        consulta =db.rawQuery("SELECT codigo,descripcion,precio,stock FROM articulos",null);
        if(consulta.moveToFirst()==true){
            codigo.setText(consulta.getString(0));
            descripcion.setText(consulta.getString(1));
            precio.setText(consulta.getString(2));
            stock.setText(consulta.getString(3));
        }else{
            Toast.makeText(this, "No se encontro ese registro", Toast.LENGTH_SHORT).show();
        }
    }

    public void irInicio() {
        if(consulta.moveToFirst()==true){
            codigo.setText(consulta.getString(0));
            descripcion.setText(consulta.getString(1));
            precio.setText(consulta.getString(2));
            stock.setText(consulta.getString(3));
        }else{
            Toast.makeText(this, "No se encontro ese registro", Toast.LENGTH_SHORT).show();
        }
    }

    public void irFin() {
        if(consulta.moveToLast()==true){
            codigo.setText(consulta.getString(0));
            descripcion.setText(consulta.getString(1));
            precio.setText(consulta.getString(2));
            stock.setText(consulta.getString(3));
        }else{
            Toast.makeText(this, "No se encontro ese registro", Toast.LENGTH_SHORT).show();
        }
    }

    public void irAdelante() {
        if(consulta.moveToNext()==true){
            codigo.setText(consulta.getString(0));
            descripcion.setText(consulta.getString(1));
            precio.setText(consulta.getString(2));
            stock.setText(consulta.getString(3));
        }else{
            Toast.makeText(this, "ES EL ÚLTIMO RESGISTRO.", Toast.LENGTH_SHORT).show();
        }
    }

    public void irAtras() {
        if(consulta.moveToPrevious()==true){
            codigo.setText(consulta.getString(0));
            descripcion.setText(consulta.getString(1));
            precio.setText(consulta.getString(2));
            stock.setText(consulta.getString(3));
        }else{
            Toast.makeText(this, "ES EL PRIMER REGISTRO.", Toast.LENGTH_SHORT).show();
        }
    }


}
