package com.example.startservice2.ui.theme;

import androidx.compose.runtime.Composable;

@Composable
fun ServiceControlScreen(
        onStartClick :() ->Unit,
onStopClick :()->Unit

){

Box(
        contentAlignment =Alignment.Center,
        modifier =Modifier.fillMaxSize()
    ){

Column(
        horizontalAlignment =Alignment.CenterHorizontally
        modifier=Modifier.fillMaxWidth()
        ){

Button(onClick =onStartClick) {
    Text(text = "Iniciar Serviço")
}
        }
