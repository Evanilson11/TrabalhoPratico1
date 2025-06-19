package com.example.trabalhopratico1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trabalhopratico1.ui.theme.TrabalhoPratico1Theme

// Modelo da Tarefa
data class Task(
    val title: String,
    val isDone: Boolean = false
)

// Tipos de filtro
enum class FilterOption { ALL, DONE, TODO }

@Composable
fun ToDoScreen() {
    var tasks by remember { mutableStateOf(listOf<Task>()) }
    var input by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf(FilterOption.ALL) }

    val filteredTasks = when (filter) {
        FilterOption.ALL -> tasks
        FilterOption.DONE -> tasks.filter { it.isDone }
        FilterOption.TODO -> tasks.filter { !it.isDone }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Campo de texto para nova tarefa
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Nova tarefa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botão Adicionar
        Button(onClick = {
            if (input.isNotBlank()) {
                tasks = tasks + Task(title = input)
                input = ""
            }
        }) {
            Text("Adicionar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filtros
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterButton("Todas", filter == FilterOption.ALL) { filter = FilterOption.ALL }
            FilterButton("Concluídas", filter == FilterOption.DONE) { filter = FilterOption.DONE }
            FilterButton("Por fazer", filter == FilterOption.TODO) { filter = FilterOption.TODO }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de tarefas filtradas
        filteredTasks.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Checkbox(
                        checked = task.isDone,
                        onCheckedChange = {
                            tasks = tasks.map {
                                if (it == task) it.copy(isDone = !it.isDone) else it
                            }
                        },
                        colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = task.title)
                }

                IconButton(onClick = {
                    tasks = tasks - task
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Remover tarefa")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para limpar todas as tarefas
        Button(
            onClick = { tasks = emptyList() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Limpar todas")
        }
    }
}

@Composable
fun FilterButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = if (selected)
            ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        else
            ButtonDefaults.buttonColors()
    ) {
        Text(text)
    }
}
