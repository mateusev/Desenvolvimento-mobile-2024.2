package com.example.appcrud

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcrud.models.User
import com.example.appcrud.viewModel.UserViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserApp(viewModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserApp(viewModel: UserViewModel) {
    val user by viewModel.users.observeAsState(emptyList())

    var name by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var userEditing by remember { mutableStateOf<User?>(null) }
    var mensagemErro by remember { mutableStateOf<String?>(null) }


    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "CRUD de Usuários") },
                modifier = Modifier.fillMaxSize(),
            )
//            TopAppBar(
//                title = { Text(text = "Gerenciamento de usuários")},
//                backgroundColor = MaterialTheme.colorScheme.primary,
//                contentColor = MaterialTheme.colorScheme.onPrimary
//            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                if (userEditing != null) "Editar usuário" else "Adicionar usuário",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                label = { Text(text = "Nome")},
                modifier = Modifier.fillMaxSize()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = idade,
                onValueChange = {idade = it},
                label = { Text(text = "Idade") },
                modifier = Modifier.fillMaxSize(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(onClick = {
                try {
                    if (name.isNotEmpty() && idade.isNotEmpty()) {
                        val idadeInt = idade.toInt()
                        if (userEditing != null) {
                            viewModel.update(User(name = name, age = idadeInt))
                        } else {
                            viewModel.updateUser(userEditing!!.copy())
                            userEditing = null
                        }
                        name = ""
                        idade = ""
                        mensagemErro = null
                    }
                } catch (e: NumberFormatException) {
                    mensagemErro = "A idade deve ser um número válido."
                }
            },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = if(userEditing == null) "Adicionar usuário" else "Salvar alterações")
            }

            mensagemErro?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(color = Color.Gray, thickness = 1.dp)

            Text(
                text = "Lista de Usuários",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn {
                items(user) { user ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Nome: ${user.name}", fontWeight = FontWeight.Bold)
                                Text(text = "Idade: ${user.age}", color = Color.Gray ,fontSize = 14.sp)
                            }

                            IconButton(onClick = {
                                name = user.name
                                idade = user.age.toString()
                                userEditing = user
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color.Blue)
                            }

                            IconButton(onClick = { viewModel.deleteUser(user) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Deletar", tint = Color.Red)
                            }
                        }
                    }
                }
            }

        }
    }

}

