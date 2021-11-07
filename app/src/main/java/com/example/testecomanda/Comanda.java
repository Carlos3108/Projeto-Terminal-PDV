package com.example.testecomanda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Comanda extends AppCompatActivity {
    private Button btVoltar;
    private Button buttonAdd;
    private TextView itemComanda;
    private EditText codigoComanda;
    private EditText codigoProduto;
    private EditText quantidadeProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        btVoltar = (Button) findViewById(R.id.btvoltar);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        TextView codigoComanda = findViewById(R.id.codigoComanda);
        TextView codigoProduto = findViewById(R.id.codigoProduto);
        TextView quantidadeProdutos = findViewById(R.id.quantidadeProdutos);
        String sessionId= getIntent().getStringExtra("id_vendedor");
        System.out.println(sessionId);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputComanda(codigoComanda.getText().toString(),
                        codigoProduto.getText().toString(),
                        quantidadeProdutos.getText().toString(),
                        sessionId);
            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Comanda.this, Menu.class);
                startActivity(intent);
            }
        });

    }
    public void additem(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    };

    public void inputComanda(String codComanda, String codProduto, String qtdProd,String sessionId) {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String URL = "http://31.220.21.132:5000/comanda/"+codComanda;
        final String VerificProd = "http://31.220.21.132:5000/produto/"+codProduto;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("codprod", codProduto);
        params.put("qtde", qtdProd);
        params.put("id_vendedor", sessionId);

        JsonObjectRequest request_json = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        additem("Produto adicionado na comanda");
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("erro");
            }
        });
        JsonObjectRequest objetoEro = new JsonObjectRequest
                (Request.Method.GET, VerificProd, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        queue.add(request_json);
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        additem("Produto não existe");
                    }
                });
        queue.add(objetoEro);
    }
}