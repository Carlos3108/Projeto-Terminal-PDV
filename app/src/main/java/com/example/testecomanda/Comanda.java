package com.example.testecomanda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Currency;
import java.util.Objects;

public class Comanda extends AppCompatActivity {
    private Button buttonAdd;
    private TextView itemComanda;
    private TextView textView;
    String a = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        itemComanda = (TextView) findViewById(R.id.itemComanda);
        //textView = (TextView) findViewById(R.id.textView);
        TextView textView = (TextView) findViewById(R.id.textView);
    }
        private static Currency MySingleton;

        public void obterUsuario(String nome){
            String URL = "http://31.220.21.132:5000/usuario/";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, URL + nome, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                         itemComanda.setText("Response: " + response.toString());
                        }
                    }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            //MySingleton.getInstance(String.valueOf(this)).addToRequestQueue(jsonObjectRequest);
        }
    }

