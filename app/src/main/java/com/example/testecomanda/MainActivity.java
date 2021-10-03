package com.example.testecomanda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                validate(cpf.getText().toString(), password.getText().toString());
            }
        });
    }
    public void validate(String cpf, String password) {
        TextView texto = findViewById(R.id.textView);
        if (cpf.toLowerCase(Locale.ROOT).equals("admin") && password.equals("admin")){
            Intent intent = new Intent(MainActivity.this, Menu.class);
            startActivity(intent);
        } else {
            texto.setText("CPF ou Senha invalidos!");
        }
    }
}