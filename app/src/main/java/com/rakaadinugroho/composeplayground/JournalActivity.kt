package com.rakaadinugroho.composeplayground

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme

class JournalActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val data = Journal(title = "Large-scale cluster management at Google with Borg", abstraction = "Google's Borg system is a cluster manager that runs hundreds of thousands of jobs, from many thousands of different applications, across a number of clusters each with up to tens of thousands of machines.")
            val journalTwo = data.copy(title = "Brain Computer Interface")
            val journalThree = data.copy(title = "Jetpack Compose vs SwiftUI")

            val listOfJournal = listOf(data, journalTwo, journalThree)

            MaterialTheme {
                JournalList(listOfJournal, listener = {
                    Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

@Composable
fun JournalList(listOfJournal: List<Journal>, listener: (Journal) -> Unit) {
    VerticalScroller {
        Column {
            listOfJournal.forEach{ data ->
                journalItem(data = data, clickItem = {
                    listener(it)
                })
            }
        }
    }
}