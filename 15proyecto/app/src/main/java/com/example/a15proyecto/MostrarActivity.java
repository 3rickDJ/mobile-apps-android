package com.example.a15proyecto;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class MostrarActivity extends AppCompatActivity {

    RecyclerView rc;
    ArrayList<Articulos>lista;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lista = new ArrayList<Articulos>();

        for(int i=0; i<10; i++){
            Articulos a = new Articulos( "a","i ","12","11/12/2024");
            lista.add(a);
        }
        rc = (RecyclerView) findViewById(R.id.RecyclerView);
        rc.setLayoutManager(new GridLayoutManager(this,1));

        adaptar ad = new adaptar(lista);
        rc.setAdapter(ad);
    }
}