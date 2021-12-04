package com.karan.keepsake

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter


class DairyEntry : AppCompatActivity() {

    lateinit var txt : EditText
    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dairy_entry)
        txt = findViewById(R.id.writeEntry)
        btn = findViewById(R.id.makeEntry)
        val fileName = "data.txt"

        btn.setOnClickListener {
            try {
                val fileOutputStream: FileOutputStream = openFileOutput("mytextfile.txt", Context.MODE_PRIVATE)
                val outputWriter = OutputStreamWriter(fileOutputStream)
                outputWriter.write(txt.text.toString())
                outputWriter.close()
                //display file saved message
                Toast.makeText(baseContext, "File saved successfully!", Toast.LENGTH_SHORT).show()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }

        }



    }
}










