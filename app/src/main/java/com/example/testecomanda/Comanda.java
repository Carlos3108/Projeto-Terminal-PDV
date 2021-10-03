package com.example.testecomanda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class Comanda extends AppCompatActivity {
    private Button buttonAdd;
    private TextView itemComanda;
    private EditText codigoComanda;
    private EditText codigoProduto;
    private EditText quantidadeProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        TextView itemComanda = findViewById(R.id.itemComanda);
        TextView codigoComanda = findViewById(R.id.codigoComanda);
        TextView codigoProduto = findViewById(R.id.codigoProduto);
        TextView quantidadeProdutos = findViewById(R.id.quantidadeProdutos);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputComanda(codigoComanda.getText().toString(), codigoProduto.getText().toString(), quantidadeProdutos.getText().toString());
            }
        });
    }

    public void inputComanda(String codComanda, String codProduto, String qtdProd) {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String URL = "https://kind-puma-61.loca.lt/comanda/"+codComanda;
        System.out.println(URL);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("codprod", codProduto);
        params.put("qtde", qtdProd);
        JsonObjectRequest request_json = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error ", error.getMessage());
            }
        });
        queue.add(request_json);
    }
}