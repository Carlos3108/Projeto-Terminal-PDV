package com.example.testecomanda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView user = findViewById(R.id.textCpfLogin);
        TextView password = findViewById(R.id.textPassword);
        Button login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obterUsuario(user.getText().toString(), password.getText().toString());

            }
        });
    }
       public void validate(String password,String senha, String id) {
        TextView texto = findViewById(R.id.textView);
        if(password.equals(senha)){
            Intent intent = new Intent(MainActivity.this, Menu.class);
            intent.putExtra("ID", id);
            startActivity(intent);
           }
        else {
            texto.setText("Login ou Senha invalidos!");
        }
    }
    public void obterUsuario(String login,String password){
        TextView texto = findViewById(R.id.textView);
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://31.220.21.132:5000/usuario/"+login;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            String senha = response.getString("senha").toString();
                            String id = response.getString("id").toString();
                            System.out.println(senha);
                            validate(password, senha,id);
                        }catch(JSONException error){
                            texto.setText("Login ou Senha invalidos!");
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        texto.setText("Login ou Senhas incorretas");
                    }
                });
        queue.add(jsonObjectRequest);
    }
}
