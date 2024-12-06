package com.example.a15proyecto;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MostrarActivity extends AppCompatActivity {

    RecyclerView rc;
    ArrayList<Articulos> lista;
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

        // Inicializar RecyclerView y lista
        lista = new ArrayList<>();
        rc = findViewById(R.id.RecyclerView);
        rc.setLayoutManager(new GridLayoutManager(this, 1));

        // Configurar Volley
        requestQueue = Volley.newRequestQueue(this);

        // Llamar al método para cargar datos
        cargarDatos();
    }

    private void cargarDatos() {
        String url = "https://serviciosdigitalesplus.com/distribuida2024/procesos.php?tipo=4";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("array");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);

                                // Crear objeto Articulos
                                String id = obj.getString("id");
                                String nom = obj.getString("nom");
                                String costo = obj.getString("costo");
                                String fecha = obj.getString("fecha");

                                Articulos articulo = new Articulos(id, nom, costo, fecha);
                                lista.add(articulo);
                            }

                            // Configurar el adaptador con los datos obtenidos
                            adaptar ad = new adaptar(lista);
                            rc.setAdapter(ad);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Volley", "Error al parsear JSON: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error en la petición: " + error.getMessage());
                    }
                });

        // Agregar la petición a la cola
        requestQueue.add(jsonObjectRequest);
    }
}
