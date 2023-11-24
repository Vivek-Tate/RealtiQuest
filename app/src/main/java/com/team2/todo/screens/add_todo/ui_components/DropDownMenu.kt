package com.team2.todo.screens.add_todo.ui_components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

/**
 * Created by Atharva K on 11/13/23.
 */

enum class priorities {
    Low,
    Medium,
    High
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuComponent(defaultPriority:String): Int {

    var menuexpanded by remember { mutableStateOf(false) }
    var selectedPriority by remember { mutableStateOf(defaultPriority) }
    var selectedPriorityIndex by remember { mutableStateOf(0) }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (menuexpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        OutlinedTextField(
            value = selectedPriority.toString(),
            onValueChange = { selectedPriority = it },
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                }
                .clickable {
                    menuexpanded = !menuexpanded
                }
                .fillMaxWidth(),
            label = { Text("Priority") },
            //trailingIcon is for the dropdown arrow
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { menuexpanded = !menuexpanded })
            },
            readOnly = true
        )
        DropdownMenu(
            expanded = menuexpanded,
            //onDismissRequest->
            // when the user clicks outside the menu, the dropdown collapses
            onDismissRequest = { menuexpanded = false },
            //this basically states that the dropdown menu will have the same width
            //as TextField
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() }
                )
        ) {
            priorities.values().forEach { priority ->
                DropdownMenuItem(text = {
                    Text(text = priority.name)
                }, onClick = {
                    selectedPriority = priority.name
                    selectedPriorityIndex = priority.ordinal
                    menuexpanded = false
                })
            }
        }
    }
    return selectedPriorityIndex
}