package com.lanazirot.pokedex.ui.common.components.country

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.lanazirot.pokedex.domain.enums.countries

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomInputDropdownStates(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    values: List<String> = countries
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(values[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = value,
            onValueChange = {
                onValueChange(selectedType)
            },
            label = { Text("Selecciona un pais") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                )
            },
            leadingIcon = {
                if (value.isNotEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(model = "file:///android_asset/countries/${value}.png"),
                        contentDescription = "Flag",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            values.forEach { state ->
                DropdownMenuItem(
                    onClick = {
                        selectedType = state
                        expanded = false
                        onValueChange(state)
                    }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = rememberAsyncImagePainter(model = "file:///android_asset/countries/${state}.png"),
                            contentDescription = "Flag",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(text = state)
                    }
                }
            }
        }
    }
}