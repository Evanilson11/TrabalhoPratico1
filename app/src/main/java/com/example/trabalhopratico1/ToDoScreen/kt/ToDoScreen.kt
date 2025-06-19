package com.example.trabalhopratico1.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trabalhopratico1.ui.theme.TrabalhoPratico1Theme

@Composable
fun ToDoScreen() {
    var tasks by remember { mutableStateOf(listOf<String>()) }
    var input by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Nova tarefa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (input.isNotBlank()) {
                tasks = tasks + input
                input = ""
            }
        }) {
            Text("Adicionar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tarefas:")
        tasks.forEach { task ->
            Text(
                text = "- $task (clique para remover)",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable {
                        tasks = tasks - task
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoScreenPreview() {
    TrabalhoPratico1Theme {
        ToDoScreen()
    }
}
