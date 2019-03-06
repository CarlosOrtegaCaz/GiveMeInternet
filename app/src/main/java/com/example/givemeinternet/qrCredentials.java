package com.example.givemeinternet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class qrCredentials extends AppCompatActivity {
    TextView wifi, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_credentials);

        wifi = findViewById(R.id.txtWifi);
        pass = findViewById(R.id.txtPass);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Escanea el Codigo QR");
        integrator.setBeepEnabled(false);
        integrator.initiateScan();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "¿No quieres internet?", Toast.LENGTH_LONG).show();
                Intent toMainMenu = new Intent(qrCredentials.this, MainActivity.class);
                qrCredentials.this.startActivity(toMainMenu);
                finish();
            } else {
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                String string = result.getContents();
                String[] parts = string.split(", ");
                wifi.setText("WIFI: " + parts[0]);
                pass.setText("Contraseña: " + parts[1]);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
