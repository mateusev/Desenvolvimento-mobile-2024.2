package com.example.profileapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando componentes
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val nameText = findViewById<TextView>(R.id.nameText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        val currentJobText = findViewById<TextView>(R.id.currentJobText)
        val experienceLayout = findViewById<LinearLayout>(R.id.experienceLayout)
        // Definindo informações de perfil
        nameText.text = "Antônio Fernandes"
        descriptionText.text = "Desenvolvedora de software com 5 anos de experiência."
        currentJobText.text = "Emprego Atual: Analista de dados na TechX"

        // Lista de experiências
        val experiencias = listOf(
            "Analista de Sistemas - Tesla",
            "Desenvolvedor Júnior - Google",
            "Estagiário - NPI"
        )

        // Adicionando experiências dinamicamente
        for (experiencia in experiencias) {
            val textView = TextView(this)
            textView.text = experiencia
            textView.textSize = 16f
            textView.setTextColor(getColor(R.color.white))
            experienceLayout.addView(textView)
        }
        profileImage.setOnClickListener {
            Toast.makeText(this, "Foto de perfil de Antônio Fernandes", Toast.LENGTH_SHORT).show()
        }

    }
}
