package com.example.testecomanda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView cpf = findViewById(R.id.textCpfLogin);
        TextView password = findViewById(R.id.textPassword);
        Button login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obterUsuario(cpf.getText().toString(), password.getText().toString());

            }
        });
    }
       public void validate(String login, String password,String senha) {
        TextView texto = findViewById(R.id.textView);
        if(login.equals(login)&&password.equals(senha)){
            Intent intent = new Intent(MainActivity.this, Menu.class);
            startActivity(intent);
           }
        else {
            texto.setText("Login ou Senha invalidos!");
        }
    }
    public void obterUsuario(String login,String password){
        TextView texto = findViewById(R.id.textView);
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "https://kind-puma-61.loca.lt/usuario/"+login;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            String senha = response.getString("senha").toString();
                            System.out.println(senha);
                            validate(login, password, senha);
                        }catch(JSONException error){
                            texto.setText("Login ou Senha invalidos!");
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        texto.setText("Login ou Senha invalidos!");
                    }
                });
        queue.add(jsonObjectRequest);
    }
}
