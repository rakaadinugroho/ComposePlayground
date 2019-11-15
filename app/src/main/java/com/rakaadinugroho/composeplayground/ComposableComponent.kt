package com.rakaadinugroho.composeplayground

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.Spacing
import androidx.ui.material.Button
import androidx.ui.material.surface.Card
import androidx.ui.material.themeTextStyle


data class Journal(val title: String, val abstraction: String)


@Composable
fun journalItem(data: Journal, clickItem: (Journal) -> Unit) {
    Card(color = Color.White, shape = RoundedCornerShape(8.dp)) {
        Column(modifier = Spacing(16.dp), crossAxisSize = LayoutSize.Expand) {
            Text(text = data.title, style = +themeTextStyle { h3 }, maxLines = 1)
            Text(text = data.abstraction, style = +themeTextStyle { body1 })

            HeightSpacer(height = 8.dp)

            Button(text = "Show Detail", onClick = {
                clickItem(data)
            })
        }
    }
}
