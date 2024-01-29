package com.example.mobilecomputing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilecomputing.ui.theme.MobilecomputingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobilecomputingTheme {
                AppNavHost()
            }
        }
    }
}

@Preview()
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "conversation"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("conversation") {
            Column {
                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = { navController.navigate("options") }) {
                    Text(text = "Options")
                }
                Conversation(
                    messages = SampleData.conversationSample,
                )
            }

        }
        composable("options") {
            Column{
                Button(
                    onClick = {
                        navController.navigate("conversation") {
                            popUpTo("conversation") {
                                inclusive = true
                            }
                        }
                    }) {
                    Text("Back")
                }
                Options(
                    options = listOf("Option a", "Option b", "Option c")
                )}
            }

    }
}

