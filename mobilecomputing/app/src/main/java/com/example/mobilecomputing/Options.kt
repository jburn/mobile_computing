package com.example.mobilecomputing

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilecomputing.ui.theme.MobilecomputingTheme

@Preview(name = "Dark")
@Composable
fun PreviewOptions() {
    val list = listOf("Option a", "Option b", "Option c")
    MobilecomputingTheme {
        Options(
            list
        )
    }
}

@Composable
fun Options(options: List<String>) {
    LazyColumn{
        items(options) { option ->
            Option(option)
        }
    }
}

@Composable
fun Option(text: String) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Column (
            modifier = Modifier.clickable {}
        ) {
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Surface(
                modifier = Modifier
                    .padding(all = 2.dp)
                    .animateContentSize()
                ,
                shadowElevation = 1.dp
            ) {
                Text(text = text,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
