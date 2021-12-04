package com.karan.keepsake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    lateinit var write: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        write = findViewById(R.id.write)
        write.setOnClickListener {
            val intent = Intent(this, DairyEntry::class.java)
            startActivity(intent)
        }
    }
}