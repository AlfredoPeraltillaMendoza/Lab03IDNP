// MainActivity.java
package com.example.lab03app01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private BatteryReceiver batteryReceiver;
    private TextView batteryStatusTextView; // TextView para mostrar el nivel de batería

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryStatusTextView = findViewById(R.id.batteryStatusTextView); // Inicializar el TextView
        batteryReceiver = new BatteryReceiver(this); // Pasar referencia a la actividad
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
        System.out.println("BroadcastReceiver registrado satisfactoriamente");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(batteryReceiver);
        System.out.println("BroadcastReceiver desregistrado satisfactoriamente");
    }

    // Método para actualizar el estado de la batería en la UI
    public void updateBatteryStatus(float batteryPct) {
        String status = String.format("Nivel de batería: %.2f%%", batteryPct);
        batteryStatusTextView.setText(status); // Actualizar el TextView
    }
}

