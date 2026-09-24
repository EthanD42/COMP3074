package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

data class Message(
    val name: String,
    val message: String
)


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {

                val messages = listOf(
                    Message("John", "Hello Lebron James"),
                    Message("Lebron james", "Why hello there sir John"),
                    Message("BigBobby", "Oh wow I love Lebron can you sign  my belly button?"),
                    Message("LumpyLarper", "Ya and can I watch?")
                )
                LazyColumn(
                    modifier = Modifier.padding(top = 250.dp)

                ) {
                    items(messages) { message ->
                        MessageCard(message)
                    }
                }


            }
        }
    }
}
@Composable
fun MessageCard(message: Message) {
    Row(
        modifier = Modifier.padding(bottom = 20.dp)

    ){
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray, CircleShape),

            contentAlignment = Alignment.Center
        ) {
            Text(message.name.first().toString())
        }
        Column(
            modifier = Modifier.padding(start = 20.dp)
        ){
            Text(message.name)
            Text(message.message)
        }

    }

}

