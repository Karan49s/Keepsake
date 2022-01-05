package com.karan.keepsake

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStreamWriter


class DairyEntry : AppCompatActivity() {

    lateinit var txt : EditText
    lateinit var btn : Button
    lateinit var tv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dairy_entry)
        txt = findViewById(R.id.writeEntry)
        btn = findViewById(R.id.makeEntry)
        tv = findViewById(R.id.tv)
        val fileName = "data.txt"

        btn.setOnClickListener {
            val path = this.getExternalFilesDir(null)
            val letDirectory = File(path, "LETME")
            letDirectory.mkdirs()
            val file = File(letDirectory, fileName)
            FileOutputStream(file).use {
                it.write(txt.text.toString().toByteArray())
            }
            //file.appendText(txt.text.toString())
            Toast.makeText(this, path.toString(), Toast.LENGTH_SHORT).show()
            val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }
            tv.text = inputAsString




//            try {
//                val fileOutputStream: FileOutputStream = openFileOutput("mytextfile.txt", Context.MODE_PRIVATE)
//                val Writer = OutputStreamWriter(fileOutputStream)
//                Writer.write(txt.text.toString())
//                Writer.close()
//                //display file saved message
//                Toast.makeText(baseContext, "File saved successfully!", Toast.LENGTH_SHORT).show()
//                Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "mytextfile.txt",
//                    Toast.LENGTH_LONG).show();
//            }
//            catch (e: Exception) {
//                e.printStackTrace()
//            }

        }



    }
}










