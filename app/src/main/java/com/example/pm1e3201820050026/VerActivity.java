package com.example.pm1e3201820050026;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pm1e3201820050026.db.DbMedicamentos;
import com.example.pm1e3201820050026.entidades.Medicamentos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtDescripcion, txtCantidad, txtTiempo, txtPeriocidad;
    Button btnGuarda;
    ImageView imgMedicamentos;
    FloatingActionButton fabEditar, fabEliminar;

    Medicamentos medicamentos;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtPeriocidad = findViewById(R.id.txtPeriocidad);
        //imgMedicamentos = findViewById(R.id.imgMedicamento);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbMedicamentos dbMedicamentos = new DbMedicamentos(VerActivity.this);
        medicamentos = dbMedicamentos.verMedicamento(id);

        if(medicamentos != null){
            txtDescripcion.setText(medicamentos.getDescripcion());
            txtCantidad.setText(medicamentos.getCantidad());
            txtTiempo.setText(medicamentos.getTiempo());
            txtPeriocidad.setText(medicamentos.getPeriocidad());
            //imgMedicamentos.setImageDrawable(medicamentos.getImagen());
            btnGuarda.setVisibility(View.INVISIBLE);
            txtDescripcion.setInputType(InputType.TYPE_NULL);
            txtCantidad.setInputType(InputType.TYPE_NULL);
            txtTiempo.setInputType(InputType.TYPE_NULL);
            txtPeriocidad.setInputType(InputType.TYPE_NULL);
            //imgMedicamentos.setVisibility(View.INVISIBLE);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbMedicamentos.eliminarMedicamento(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }
    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}