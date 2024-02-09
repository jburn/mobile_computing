package com.example.mobilecomputing

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import coil.compose.AsyncImage
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
    Column {
        User(defaultName = "username", defaultImgUri = "Environment.getExternalStorageDirectory().getPath()/Pictures/dk_merkki.png")
        Spacer(modifier = Modifier.height(4.dp))
        LazyColumn{
            items(options) { option ->
                Option(option)
            }
        }
    }
}


@Composable
fun User(defaultName: String, defaultImgUri: String) {
    val context = LocalContext.current
    val sharedPreferences = remember { PreferenceManager.getDefaultSharedPreferences(context)}
    var username by remember { mutableStateOf(defaultName) }
    var imgUri by remember { mutableStateOf(defaultImgUri) }


    val pickImage = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {uri ->
        imgUri = uri.toString()
    }

    AsyncImage(
        model = imgUri,
        contentDescription = "profile photo",
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .fillMaxWidth(),
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = { pickImage.launch("image/*")},
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Choose profile photo")
    }
    Spacer(modifier = Modifier.height(16.dp))
    var newUsername by remember { mutableStateOf(username) }
    Text(
        text=newUsername,
        color = MaterialTheme.colorScheme.primary
    )
    OutlinedTextField(
        value = username,
        onValueChange = {username = it},
        label = {Text("Set username")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                newUsername = username
            }
        ),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = {
            sharedPreferences.edit {
                putString("username", newUsername)
                putString("imgUri", imgUri)
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Save user details")
    }
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = {
            newUsername = sharedPreferences.getString("username", defaultName) ?: defaultName
            imgUri = sharedPreferences.getString("imgUri", defaultImgUri) ?: defaultImgUri
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Load user details")
    }
}

@Composable
fun Option(text: String) {
    Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp,)) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(all = 8.dp)
                .clickable {}
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Surface(
                modifier = Modifier
                    .padding(all = 2.dp)
                    .animateContentSize()
                    .fillMaxWidth()
                ,
                shadowElevation = 1.dp
            ) {
                Text(text = text,
                    modifier = Modifier
                        .padding(all = 4.dp)
                        .wrapContentWidth()
                    ,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
