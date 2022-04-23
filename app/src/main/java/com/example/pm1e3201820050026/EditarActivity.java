package com.example.pm1e3201820050026;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pm1e3201820050026.db.DbMedicamentos;
import com.example.pm1e3201820050026.entidades.Medicamentos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtDescripcion, txtCantidad, txtTiempo, txtPeriocidad;
    ImageView imgMedicamentos;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Medicamentos medicamentos;
    int id = 0;

    @SuppressLint("RestrictedApi")
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
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbMedicamentos dbMedicamentos = new DbMedicamentos(EditarActivity.this);
        medicamentos = dbMedicamentos.verMedicamento(id);

        if (medicamentos != null) {
            txtDescripcion.setText(medicamentos.getDescripcion());
            txtCantidad.setText(medicamentos.getCantidad());
            txtTiempo.setText(medicamentos.getTiempo());
            txtPeriocidad.setText(medicamentos.getPeriocidad());
            //imgMedicamentos.setImageDrawable(medicamentos.getImagen());

        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtDescripcion.getText().toString().equals("") && !txtCantidad.getText().toString().equals("")) {
                    correcto = dbMedicamentos.editarMedicamento(id, txtDescripcion.getText().toString(), txtCantidad.getText().toString(), txtTiempo.getText().toString(), txtPeriocidad.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, com.example.pm1e3201820050026.VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}