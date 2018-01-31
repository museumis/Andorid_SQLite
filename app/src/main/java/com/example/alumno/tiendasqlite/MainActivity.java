package com.example.alumno.tiendasqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText codigo, precio, descripcion, stock;
    Button alta, baja, modificacion, consulta;
    String NOMBREBASEDATOS = "tienda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codigo = (EditText) findViewById(R.id.editCodigo);
        precio = (EditText) findViewById(R.id.editPrecio);
        descripcion = (EditText) findViewById(R.id.editDescripcion);
        stock = (EditText) findViewById(R.id.editStock);

        alta = (Button) findViewById(R.id.btnAlta);
        baja = (Button) findViewById(R.id.btnBaja);
        modificacion = (Button) findViewById(R.id.btnModificacion);
        consulta = (Button) findViewById(R.id.btnConsulta);

        alta.setOnClickListener(this);
        baja.setOnClickListener(this);
        modificacion.setOnClickListener(this);
        consulta.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnAlta: {
                insertarEntabla();
                break;
            }
            case R.id.btnBaja: {
                deleterEntabla();
                break;
            }
            case R.id.btnModificacion: {
                updateEntabla();
                break;
            }
            case R.id.btnConsulta: {
                Intent intent = new Intent(this, Consultas.class);
                startActivity(intent);
                break;
            }

        }
    }

    /**
     * Insertar en una tabla
     */
    public void insertarEntabla() {
        Administracion admin = new Administracion(this, NOMBREBASEDATOS, null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();

        String codigox = codigo.getText().toString();
        String descripx = descripcion.getText().toString();
        String stockx = stock.getText().toString();
        String preciox = precio.getText().toString();

        registro.put("codigo", codigox);
        registro.put("descripcion", descripx);
        registro.put("stock", stockx);
        registro.put("precio", preciox);

        db.insert("articulos", null, registro);
        db.close();


        limpiarEdits();

        Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();


    }

    /**
     * Borrar fila de la tabla
     */
    public void deleterEntabla() {
        Administracion admin = new Administracion(this, NOMBREBASEDATOS, null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        int numBorrados = db.delete("articulos", "codigo=" + codigo.getText().toString(), null);

        Toast.makeText(this, "Registros borrados -> " + numBorrados, Toast.LENGTH_SHORT).show();
        limpiarEdits();
        db.close();
    }

    /**
     * Actualizar una fila
     */
    public void updateEntabla() {
        Administracion admin = new Administracion(this, NOMBREBASEDATOS, null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();

        String codigox = codigo.getText().toString();
        String descripx = descripcion.getText().toString();
        String stockx = stock.getText().toString();
        String preciox = precio.getText().toString();

        registro.put("codigo", codigox);
        registro.put("descripcion", descripx);
        registro.put("stock", stockx);
        registro.put("precio", preciox);

        int numActualizados = db.update("articulos", registro, "codigo=" + codigox, null);

        Toast.makeText(this, "Registros Modificados -> " + numActualizados, Toast.LENGTH_SHORT).show();

        db.close();

    }

    /**
     * Limpia los editex
     */
    public void limpiarEdits() {
        codigo.setText("");
        descripcion.setText("");
        stock.setText("");
        precio.setText("");
    }

}
