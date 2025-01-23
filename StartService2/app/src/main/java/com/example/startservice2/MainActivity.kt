package com.example.startservice2



import androidx.activity.ComponentActivity
import androidx.core.app.NotificationCompat

class MainActivity
private val controlChannelId = "Service_control_channel"


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
        ServiceControlScreen(
            onStartClick = {  },
            onStopClick = { }
        )
    }

    private fun startBackgroundService(){
        val intent = Intent(this, BackgroundService::class.java)

        startService(intent)
        Toast.makeText(this, "Serviço Iniciado pelo botão", Toast.LENGTH_SHORT).show()
        ShowControlNotification("Serviço iniciado")
    }

    private fun stopBackgroundService(){
        val intent = Intent(this, BackgroundService::class.java)

        startService(intent)
        Toast.makeText(this, "Serviço parado pelo botão", Toast.LENGTH_SHORT).show()
        ShowControlNotification("Serviço parado")
    }

    @Suppress("MissingPermission")
    private fun ShowControlNotification(message: String){
        val notification = NotificationCompat.Builder(this, controlChannelId)
            .setContentTitle("Controle de serviço")
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        NotificationManagerCompat.from(this).notify(System.currentTimeMillis())
    }

}