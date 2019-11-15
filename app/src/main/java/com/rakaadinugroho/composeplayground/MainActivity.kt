package com.rakaadinugroho.composeplayground

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.State
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.input.EditorStyle
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.surface.Card
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FlexColumn (crossAxisSize = LayoutSize.Expand){
                    expanded(1.0f) {
                        VerticalScroller {
                            Column (modifier = Spacing(16.dp), crossAxisSize = LayoutSize.Expand) {
                                for (i in 0..10) {
                                    if (i%2 == 0) {
                                        ChatSent()
                                    } else {
                                        ChatReceive()
                                    }
                                }
                            }
                        }
                    }
                    inflexible {
                        TypingBox {

                        }
                    }
                }

            }
        }
    }
}

data class Message(val username: String, val message: String)


@Composable
fun ChatSent() {
    Row(mainAxisAlignment = MainAxisAlignment.End, mainAxisSize = LayoutSize.Expand){
        Card(elevation = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)) {
            Column(modifier = Spacing(8.dp), crossAxisAlignment = CrossAxisAlignment.End, crossAxisSize = LayoutSize.Wrap) {
                Text(text = "@rakaadinugroho", style = +themeTextStyle { body2 })
                HeightSpacer(height = 4.dp)
                Text(text = "kamu lagi dimana?", style = +themeTextStyle { body1 })
            }
        }
    }

}

@Composable
fun ChatReceive() {
    Row(mainAxisAlignment = MainAxisAlignment.Start, mainAxisSize = LayoutSize.Expand){
        Card(elevation = 2.dp, color = Color.White, shape = RoundedCornerShape(8.dp)) {
            Column(modifier = Spacing(8.dp), crossAxisAlignment = CrossAxisAlignment.Start, crossAxisSize = LayoutSize.Wrap) {
                Text(text = "@rakaadinugroho", style = +themeTextStyle { body2 })
                HeightSpacer(height = 4.dp)
                Text(text = "kamu lagi dimana?", style = +themeTextStyle { body1 })
            }
        }
    }

}

@Composable
fun TypingBox(
    onSearch: (String) -> Unit
) {
    val state = +state { EditorModel() }
    Padding(padding = 16.dp) {
        Card(color = Color.LightGray) {
            FlexRow(crossAxisAlignment = CrossAxisAlignment.Start) {
                expanded(1.0f) {
                    SingleLineEditText(
                        state,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search,
                        editorStyle = EditorStyle(textStyle = TextStyle(fontSize = 16.sp)),
                        onImeActionPerformed = {
                            onSearch(state.value.text)
                        }
                    )
                }
                inflexible {
                    Button(
                        "Kirim",
                        style = ContainedButtonStyle(),
                        onClick = {
                            onSearch(state.value.text)
                        })

                }
            }
        }

    }
}

@Composable
fun SingleLineEditText(
    state: State<EditorModel>,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Unspecified,
    editorStyle: EditorStyle? = null,
    onImeActionPerformed: (ImeAction) -> Unit = {
    }
) {
    Padding(8.dp) {
        TextField(
            value = state.value,
            onValueChange = { new ->
                state.value = if (new.text.any { it == '\n' }) {
                    state.value
                } else {
                    new
                }
            },
            keyboardType = keyboardType,
            imeAction = imeAction,
            editorStyle = editorStyle,
            onImeActionPerformed = onImeActionPerformed
        )
    }
}

@Preview
@Composable
fun journalApp() {
    val data = Journal(title = "Large-scale cluster management at Google with Borg", abstraction = "Google's Borg system is a cluster manager that runs hundreds of thousands of jobs, from many thousands of different applications, across a number of clusters each with up to tens of thousands of machines.")

    MaterialTheme {
        journalItem(data = data, clickItem = {

        })
    }
}