package com.example.a15proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnCrear;
    Button btnModificar;
    Button btnEliminar;
    Button btnMostrar;
    Button btnBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCrear = (Button) findViewById(R.id.button2);
        btnModificar = (Button) findViewById(R.id.button3);
        btnEliminar = (Button) findViewById(R.id.button4);
        btnMostrar = (Button) findViewById(R.id.button5);
        btnBuscar = (Button) findViewById(R.id.button6);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {formCrear();}
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {formModificar();}
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {formEliminar();}
        });
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {formMostrar();}
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {formBuscar();}
        });

    }
    public void formCrear(){
        Intent i = new Intent(this, CrearActivity.class);
        startActivity(i);
    }
    public void formModificar(){
        Intent i = new Intent(this, ModificarActivity.class);
        startActivity(i);
    }
    public void formEliminar(){
        Intent i = new Intent(this, EliminarActivity.class);
        startActivity(i);
    }
    public void formMostrar(){
        Intent i = new Intent(this, MostrarActivity.class);
        startActivity(i);
    }

    public void formBuscar(){
        Intent i = new Intent(this, BuscarActivity.class);
        startActivity(i);
    }
}