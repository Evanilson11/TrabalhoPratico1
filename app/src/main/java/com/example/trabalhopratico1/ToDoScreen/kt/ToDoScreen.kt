package com.example.trabalhopratico1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trabalhopratico1.ui.theme.TrabalhoPratico1Theme

// Data class para representar uma tarefa com estado de conclusão
data class Task(val name: String, var done: Boolean = false)

@Composable
fun ToDoScreen() {
    var tasks by remember { mutableStateOf(listOf<Task>()) }
    var input by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Campo de texto
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Nova tarefa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botões de adicionar e limpar todas
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                if (input.isNotBlank()) {
                    tasks = tasks + Task(input)
                    input = ""
                }
            }) {
                Text("Adicionar")
            }

            Button(onClick = {
                tasks = emptyList()
            }) {
                Text("Limpar todas")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de tarefas com checkbox
        Text("Tarefas:")
        tasks.forEachIndexed { index, task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = task.name,
                    modifier = Modifier.weight(1f)
                )
                Checkbox(
                    checked = task.done,
                    onCheckedChange = { checked ->
                        tasks = tasks.toMutableList().also {
                            it[index] = it[index].copy(done = checked)
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary.copy(green = 1f) // verde forte
                    )
                )

            }
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
