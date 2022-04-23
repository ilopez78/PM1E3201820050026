package com.example.pm1e3201820050026;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.pm1e3201820050026.adaptadores.ListaMedicamentosAdapter;
import com.example.pm1e3201820050026.db.DbMedicamentos;
import com.example.pm1e3201820050026.entidades.Medicamentos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView listaMedicamentos;
    ArrayList<Medicamentos> listaArrayMedicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaMedicamentos = findViewById(R.id.listaMedicamentos);
        listaMedicamentos.setLayoutManager(new LinearLayoutManager(this));

        DbMedicamentos dbMedicamentos = new DbMedicamentos(MainActivity.this);

        listaArrayMedicamentos = new ArrayList<>();

        ListaMedicamentosAdapter adapter = new ListaMedicamentosAdapter(dbMedicamentos.mostrarMedicamentos());
        listaMedicamentos.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
}