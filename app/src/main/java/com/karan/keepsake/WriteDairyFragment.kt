package com.karan.keepsake

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class WriteDairyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_write_dairy, container, false)
        val txt : EditText = view.findViewById(R.id.writeEntry)
        val btn : Button = view.findViewById(R.id.makeEntry)

        btn.setOnClickListener {
            try {
                val fileOutputStream = activity?.openFileOutput("data.txt", Context.MODE_PRIVATE)
                if (fileOutputStream != null) {
                    fileOutputStream.write(txt.text.toString().toByteArray())
                }
                Toast.makeText(activity?.baseContext, "File saved successfully!", Toast.LENGTH_SHORT).show()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return view
    }
}