package com.rakaadinugroho.composeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.OutlinedButtonStyle
import androidx.ui.material.themeTextStyle

class GroceryActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                GroceryApp(generateGroceries())
            }
        }
    }
}

@Composable
fun GroceryApp(groceries: List<Grocery>) {
    VerticalScroller {
        Column {
            groceries.forEach { data ->
                GroceriesItem(
                    name = data.name,
                    total = data.total,
                    addItem = {data.total++},
                    minItem = {
                        if (data.total > 0) data.total--
                    }
                )
            }
        }

    }
}

@Composable
fun GroceriesItem(name: String, total: Int, addItem: () -> Unit, minItem: () -> Unit) {
    FlexRow(modifier = Spacing(8.dp), crossAxisSize = LayoutSize.Wrap) {
        expanded(1.0f) {
            Text(text = name, style = +themeTextStyle { h6 }, modifier = Spacing(4.dp))
        }
        inflexible {
            Button(text = "+", style = OutlinedButtonStyle(), onClick = { addItem() })
            Text(text = total.toString(), style = +themeTextStyle { h6 }, modifier = Spacing(4.dp))
            Button(text = "-", style = OutlinedButtonStyle(), onClick = { minItem() })
        }
    }
}

fun generateGroceries(): List<Grocery> {
    return listOf(
        Grocery(name = "Ikan Tongkol", total = 0),
        Grocery(name = "Kembang Kol", total = 0),
        Grocery(name = "Sirup Marjan", total = 0)
    )
}

@Model
data class Grocery(
    val name: String,
    var total: Int
)
