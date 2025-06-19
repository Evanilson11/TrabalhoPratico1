package com.example.trabalhopratico1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Modelo de dados
data class Task(val title: String, val isDone: Boolean = false)

// Opções de filtro
enum class FilterOption { ALL, DONE, TODO }

@Composable
fun ToDoScreen() {
    var tasks by remember { mutableStateOf(listOf<Task>()) }
    var input by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf(FilterOption.ALL) }

    // Aplicar filtro
    val filteredTasks = when (filter) {
        FilterOption.ALL -> tasks
        FilterOption.DONE -> tasks.filter { it.isDone }
        FilterOption.TODO -> tasks.filter { !it.isDone }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Lista de Tarefas", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Entrada e botão Adicionar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Nova tarefa") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (input.isNotBlank()) {
                    tasks = tasks + Task(input)
                    input = ""
                }
            }) {
                Text("Adicionar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botões de filtro
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterButton("Todas", filter == FilterOption.ALL) { filter = FilterOption.ALL }
            FilterButton("Feitas", filter == FilterOption.DONE) { filter = FilterOption.DONE }
            FilterButton("Por Fazer", filter == FilterOption.TODO) { filter = FilterOption.TODO }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de tarefas
        filteredTasks.forEach { task ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = task.isDone,
                    onCheckedChange = {
                        tasks = tasks.map {
                            if (it == task) it.copy(isDone = !it.isDone) else it
                        }
                    }
                )
                Text(
                    text = task.title,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { tasks = tasks - task }) {
                    Icon(Icons.Default.Delete, contentDescription = "Remover")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para limpar todas
        Button(
            onClick = { tasks = emptyList() },
            modifier = Modifier.fillMaxWidth(),
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
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Text(text)
    }
}
