package com.luisavillacorte.stroopergame;

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

    Button btnIniciar;
    Button btnPuntajes;
    Button btnAjustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      btnIniciar = findViewById(R.id.btnIniciar);
      btnIniciar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent goPlay = new Intent(MainActivity.this, Juego.class);
              startActivity(goPlay);
          }
      });
      btnPuntajes = findViewById(R.id.btnPuntajes);
      btnAjustes = findViewById(R.id.btnAjustes);




    }
}