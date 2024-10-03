package com.example.lab03app02;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    @Override
    protected void onResume() {
        super.onResume();

        // Intent para el BroadcastReceiver
        Intent intent = new Intent(this, BatteryReceiver.class);

        // Crear el PendingIntent con FLAG_IMMUTABLE
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // AlarmManager para ejecutar el PendingIntent periódicamente
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long interval = AlarmManager.INTERVAL_FIFTEEN_MINUTES; // Intervalo de 15 minutos
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

        System.out.println("PendingIntent registrado satisfactoriamente");
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Cancelar el PendingIntent cuando la actividad se pausa
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
            System.out.println("PendingIntent desregistrado satisfactoriamente");
        }
    }
}
