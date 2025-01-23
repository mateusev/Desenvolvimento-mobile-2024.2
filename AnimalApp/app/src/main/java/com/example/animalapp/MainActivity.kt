package com.example.animalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.animalapp.ui.theme.AnimalAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimalAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimalAppMenu(onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Carro") }, // Novo padrão para o conteúdo
            onClick = {
                expanded = false
                onOptionSelected("Carro")
            }
        )
        DropdownMenuItem(
            text = { Text("Moto") }, // Novo padrão para o conteúdo
            onClick = {
                expanded = false
                onOptionSelected("Moto")
            }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(onAnimalSelected: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("VeiculoApp") },
                actions = {
                    AnimalAppMenu(onOptionSelected = onAnimalSelected)
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Selecione um carro ou moto")
        }
    }
}

@Composable
fun VeiculoSom(Veiculo: String) {
    val context = LocalContext.current
    val imageRes = if (Veiculo == "Carro") R.drawable.carro == "Carro") R.drawable.carro else R.drawable.moto
    val soundRes = if (Veiculo == "Carro") R.raw.carro_som == "Carro") R.raw.dog_bark else R.raw.moto_som
    val videoRes = if (Veiculo == "Carro") R.raw.carro_video == "Carro") R.raw.carro_video else R.raw.moto_video

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem do veículo
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "$animal Image",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))

// Botão para reproduzir o som
        Button(onClick = {
            try {
                val mediaPlayer = MediaPlayer.create(context, soundRes)
                if (mediaPlayer != null) {
                    mediaPlayer.start()
                    mediaPlayer.setOnCompletionListener { mediaPlayer.release() }
                } else {
                    println("Erro: MediaPlayer retornou null para o recurso $soundRes")
                }
            } catch (e: Exception) {
                println("Erro ao inicializar MediaPlayer: ${e.message}")
            }
        }) {
            Text("Reproduzir Som")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para reproduzir o vídeo
        Button(onClick = {
            val intent = Intent(context, VideoPlayerActivity::class.java)
            intent.putExtra("videoRes", videoRes)
            context.startActivity(intent)
        }) {
            Text("Reproduzir Vídeo")
        }
    }
}

// Botão para reproduzir o som
Button(onClick = {
    try {
        val mediaPlayer = MediaPlayer.create(context, soundRes)
        if (mediaPlayer != null) {
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener { mediaPlayer.release() }
        } else {
            println("Erro: MediaPlayer retornou null para o recurso $soundRes")
        }
    } catch (e: Exception) {
        println("Erro ao inicializar MediaPlayer: ${e.message}")
    }
}) {
    Text("Reproduzir Som")
}

Spacer(modifier = Modifier.height(16.dp))

// Botão para reproduzir o vídeo
Button(onClick = {
    val intent = Intent(context, VideoPlayerActivity::class.java)
    intent.putExtra("videoRes", videoRes)
    context.startActivity(intent)
}) {
    Text("Reproduzir Vídeo")
}

@Composable
fun VeiculoAppMenu(onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Carro") }, // Novo padrão para o conteúdo
            onClick = {
                expanded = false
                onOptionSelected("Carro")
            }
        )
        DropdownMenuItem(
            text = { Text("Moto") }, // Novo padrão para o conteúdo
            onClick = {
                expanded = false
                onOptionSelected("Moto")
            }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(onVeiculolSelected: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("VeiculoApp") },
                actions = {
                    AnimalAppMenu(onOptionSelected = onAnimalSelected)
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Selecione veículo no menu.")
        }
    }
}

@Composable
fun AnimalScreen(animal: String) {
    val context = LocalContext.current
    val imageRes = if (animal == "Carro") R.drawable.carro else R.drawable.moto
    val soundRes = if (animal == "Carro") R.raw.carro_barulho else R.raw.cat_meow
    val videoRes = if (animal == "Carro") R.raw.carro_video else R.raw.cat_video

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem do animal
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "$animal Image",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimalAppTheme {
        Greeting("Android")
    }
}