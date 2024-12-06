package com.example.a15proyecto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class adaptar extends RecyclerView.Adapter<adaptar.ViewHolderDatos> {
    ArrayList<Articulos> lista;

    public adaptar(ArrayList<Articulos> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public adaptar.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptar.ViewHolderDatos holder, int position) {
        holder.asignardato(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        EditText txtID;
        EditText txtNom;
        EditText txtCosto;
        EditText txtFecha;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.editTextText_ilist_id);
            txtNom = itemView.findViewById(R.id.editTextText_ilist_nombre);
            txtCosto = itemView.findViewById(R.id.editTextText_ilist_costo);
            txtFecha = itemView.findViewById(R.id.editTextText_ilist_fecha);
        }

        public void asignardato(Articulos a) {
            txtID.setText(a.id);
            txtNom.setText(a.nom);
            txtCosto.setText(a.costo);
            txtFecha.setText(a.fecha);
        }
    }
}
