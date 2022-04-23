package com.example.pm1e3201820050026.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pm1e3201820050026.R;
import com.example.pm1e3201820050026.VerActivity;
import com.example.pm1e3201820050026.entidades.Medicamentos;

import java.util.ArrayList;

public class ListaMedicamentosAdapter extends RecyclerView.Adapter<ListaMedicamentosAdapter.MedicamentoViewHolder> {

    ArrayList<Medicamentos> listaMedicamentos;
    public ListaMedicamentosAdapter(ArrayList<Medicamentos> listaMedicamentos){
        this.listaMedicamentos = listaMedicamentos;
    }

    @NonNull
    @Override
    public MedicamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_medicamento, null, false);
        return new MedicamentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentoViewHolder holder, int position) {
        holder.viewDescripcion.setText(listaMedicamentos.get(position).getDescripcion());
        holder.viewCantidad.setText(listaMedicamentos.get(position).getCantidad());
        holder.viewTiempo.setText(listaMedicamentos.get(position).getTiempo());
        holder.viewPeriocidad.setText(listaMedicamentos.get(position).getPeriocidad());
        //holder.imgMedicamentos.setImageDrawable(listaMedicamentos.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return listaMedicamentos.size();
    }

    public class MedicamentoViewHolder extends RecyclerView.ViewHolder {

        TextView viewDescripcion, viewCantidad, viewTiempo, viewPeriocidad;
        ImageView imgMedicamentos;

        public MedicamentoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewDescripcion = itemView.findViewById(R.id.viewDescripcion);
            viewCantidad = itemView.findViewById(R.id.viewCantidad);
            viewTiempo = itemView.findViewById(R.id.viewTiempo);
            viewPeriocidad = itemView.findViewById(R.id.viewPeriocidad);
            //imgMedicamentos = itemView.findViewById(R.id.imgMedicamento);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaMedicamentos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}