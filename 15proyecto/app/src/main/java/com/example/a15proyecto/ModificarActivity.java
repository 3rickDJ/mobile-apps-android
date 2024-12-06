package com.example.a15proyecto;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class ModificarActivity extends AppCompatActivity {
    Button btnCancelar;
    Button btnAceptar;
    Button btnSearch;

    EditText txtNom;
    EditText txtCosto;
    EditText txtFecha;
    EditText txtID;
    Spinner txtFoto;

    String spinner;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCancelar = (Button) findViewById(R.id.button10);
        btnAceptar = (Button) findViewById(R.id.button9);
        btnSearch = (Button) findViewById(R.id.button14);

        txtNom = (EditText) findViewById(R.id.editTextText2);
        txtCosto = (EditText) findViewById(R.id.editTextNumberDecimal3);
        txtID = (EditText) findViewById(R.id.editTextNumber3);
        txtFoto = (Spinner) findViewById(R.id.spinner3);
        txtFecha = (EditText) findViewById(R.id.editTextText4);
        ArrayList<String> objetivo = new ArrayList<String>();
        objetivo.add("img1.jpg");
        objetivo.add("img2.jpg");
        objetivo.add("img3.jpg");
        objetivo.add("img4.jpg");
        objetivo.add("img5.jpg");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,objetivo);
        txtFoto.setAdapter(adapter);

        txtFoto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAceptarClick(v);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearchClick(v);
            }
        });
    }

    public void btnSearchClick(View v) {
        String id = txtID.getText().toString();
        String url = "https://serviciosdigitalesplus.com/distribuida2024/procesos.php?tipo=5&id="+id+"&form=MG0AV3";
        requestQueue = Volley.newRequestQueue(this);
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Extraer el array del JSON
                    JSONObject data = response.getJSONArray("array").getJSONObject(0);

                    // Establecer los valores en los EditTexts
                    System.out.println("HOLA");
                    System.out.println(data.getString("nom"));
                    txtNom.setText(data.getString("nom"));
                    System.out.println(data.getString("costo"));
                    txtCosto.setText(data.getString("costo"));
                    System.out.println(data.getString("fecha"));
                    txtFecha.setText(data.getString("fecha"));
                    System.out.println("Que pedo jaja");

                    // Mostrar alerta de éxito
                    alerta("Se encontraron los datos", "Información");
                } catch (Exception e) {
                    e.printStackTrace();
                    alerta("No se encontró el artículo", "Error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("web error");
            }
        });
        requestQueue.add(jsonObjectRequest);/**/
    }
    public void btnAceptarClick(View v) {
        String id = txtID.getText().toString();
        String nom = txtNom.getText().toString();
        String foto = "";
        String costo = txtCosto.getText().toString();
        String fecha = txtFecha.getText().toString();

        //System.out.println(id+" "+nom+" "+costo);

        String url = "https://serviciosdigitalesplus.com/distribuida2024/procesos.php?tipo=2&id="+id+"&nom="+nom+"&costo="+costo+"&foto="+spinner+"&fecha="+fecha+"&form=MG0AV3";
        requestQueue = Volley.newRequestQueue(this);
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("web ok");
                txtID.setText("");
                txtNom.setText("");
                txtCosto.setText("");
                txtFecha.setText("");
                alerta("Se Modifico","Informacion");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("web error");
            }
        });
        requestQueue.add(jsonObjectRequest);/**/
    }
    public void alerta(String line, String titulo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo)
                .setMessage(line)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                    }
                }).show();
    }
}