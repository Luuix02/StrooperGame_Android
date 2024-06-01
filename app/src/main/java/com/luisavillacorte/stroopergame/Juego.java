package com.luisavillacorte.stroopergame;

import android.graphics.Camera;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import kotlinx.coroutines.CancelHandlerBase;

public class Juego extends AppCompatActivity {

    TextView txtPalabra;
    Button btnCorrecto, btnIncorrecto;

    Random randomColor;
    Handler handler;
    Runnable cambiarColorRunn;
    int[] colores;
    String[] nombresColores;
    int colorActual;
    String nombreColorActual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        txtPalabra = findViewById(R.id.txtPalabra);
        btnCorrecto = findViewById(R.id.btnCorrecto);
        btnIncorrecto = findViewById(R.id.btnIncorrecto);
        randomColor = new Random();
        handler = new Handler();

        nombresColores = new String[]{"Rojo", "Blanco", "Azul", "Amarillo", "Verde", "Naranja", "Púrpura"};

        colores = new int[]{
                Color.RED,
                Color.WHITE,
                Color.BLUE,
                Color.YELLOW,
                Color.GREEN,
                Color.parseColor("#FFA500"),
                Color.parseColor("#800080")
        };

        CambiarColorTexto();

        btnCorrecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(true);
            }
        });

        btnIncorrecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(false);
            }
        });
    }

    public void CambiarColorTexto() {
        int indiceColor = randomColor.nextInt(colores.length);
        colorActual = colores[indiceColor];

        boolean esCorrecto = randomColor.nextBoolean();
        if (esCorrecto) {
            nombreColorActual = nombresColores[indiceColor];
        } else {
            int indiceNombre;
            do {
                indiceNombre = randomColor.nextInt(nombresColores.length);
            } while (indiceNombre == indiceColor);
            nombreColorActual = nombresColores[indiceNombre];
        }

        txtPalabra.setTextColor(colorActual);
        txtPalabra.setText(nombreColorActual);
    }

    public void verificarRespuesta(boolean esCorrecto) {
        boolean respuestaCorrecta = nombreColorActual.equalsIgnoreCase(nombreDelColor(colorActual));
        if ((esCorrecto && respuestaCorrecta) || (!esCorrecto && !respuestaCorrecta)) {
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
        }
        CambiarColorTexto();
    }

    public String nombreDelColor(int color) {
        if (color == Color.YELLOW) {
            return "Amarillo";
        } else if (color == Color.BLUE) {
            return "Azul";
        } else if (color == Color.parseColor("#FFA500")) {
            return "Naranja";
        } else if (color == Color.WHITE) {
            return "Blanco";
        } else if (color == Color.RED) {
            return "Rojo";
        } else if (color == Color.GREEN) {
            return "Verde";
        } else if (color == Color.parseColor("#800080")) {
            return "Púrpura";
        } else {
            return "Desconocido";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && cambiarColorRunn != null) {
            handler.removeCallbacks(cambiarColorRunn);
        }
    }
}