package com.example.startservice2.ui.theme;

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.startservice2.R


public class BackgroundService: Service() {
    private val channeLId = "background_servicce_channel"

    private var isRunning = false

    override fun onCreate() {
        super.onCreate()
        createNotificationChannerl()
        isRunning = true
        Toast.makeText(this, "Serviço Criado", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationCompat.Builder(this, channeLId)
            .setContentTitle("Serviço em Execução")
            .setContentText("o serviço está sendo executado em segundo plano")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        startForeground(1, notification)
        Toast.makeText(this, "Serviço Iniciado", Toast.LENGTH_SHORT).show()

        Thread{
            while (isRunning){
                Thread.sleep(1000)

            }
            stopSelf()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        Toast.makeText(this, "Serviço Finalizado", Toast.LENGTH_SHORT).show()
    }

    overrire fun onBind (intent: Intent?): IBinder? {

        private fun createNotificationChannerl(){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channel = NotificationChannel(
                    channeLId,
                    "Serviço em background",
                    NotificationManager.IMPORTANCE_LOW
                )
                val manager = getSystemService(NotificationManager::class.java)
                manager.createNotificationChannel(channel)
            }
        }
    }


}

