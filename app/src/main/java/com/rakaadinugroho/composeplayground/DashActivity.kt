package com.rakaadinugroho.composeplayground

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.Spacing
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme

class DashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column(modifier = Spacing(16.dp)) {
                    Button(text = "Chat Layout", onClick = {
                        accessPage(MainActivity())
                    })
                    HeightSpacer(8.dp)
                    Button(text = "Journal Layout", onClick = {
                        accessPage(JournalActivity())
                    })
                    HeightSpacer(8.dp)
                    Button(text = "State Layout", onClick = {
                        accessPage(GroceryActivity())
                    })
                }
            }
        }

    }

    fun String.checkTest(){

    }

    fun Context.accessPage(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}