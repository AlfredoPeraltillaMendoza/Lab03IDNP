// BatteryReceiver.java
package com.example.lab03app01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryReceiver extends BroadcastReceiver {
    private MainActivity activity; // Referencia a la actividad

    public BatteryReceiver(MainActivity activity) {
        this.activity = activity; // Inicializar la actividad
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float) scale * 100;

        // Actualizar el nivel de batería en la UI
        activity.updateBatteryStatus(batteryPct);
    }
}


