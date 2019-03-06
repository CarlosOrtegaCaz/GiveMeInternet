package com.example.givemeinternet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button dameInternet;
    MediaPlayer tengoHambreM4a;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tengoHambreM4a = MediaPlayer.create(this, R.raw.mm_tengo_hambre2);
        dameInternet = findViewById(R.id.btnQR);
        dameInternet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println(" pressed ");
                        dameInternet.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.boton_rojo_presionado));
                        tengoHambreM4a.start();
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println(" released ");
                        dameInternet.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.boton_rojo_normal));
                        Intent qrIntent = new Intent(MainActivity.this, qrCredentials.class);
                        MainActivity.this.startActivity(qrIntent);

                        break;
                }
                return true;
            }
        });
        dameInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dameInt = new Intent(MainActivity.this, qrCredentials.class);
                MainActivity.this.startActivity(dameInt);
            }
        });
    }
}
