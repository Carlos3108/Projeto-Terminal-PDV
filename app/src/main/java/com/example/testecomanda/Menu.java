package com.example.testecomanda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button additem = findViewById(R.id.additem);
        additem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(Menu.this, Comanda.class);
                startActivity(intent);
            }
        });
        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}