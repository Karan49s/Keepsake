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
import com.karan.keepsake.SimpleCrypto.encrypt
import java.io.*

class WriteDairyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_write_dairy, container, false)
        val txt : EditText = view.findViewById(R.id.writeEntry)
        val btn : Button = view.findViewById(R.id.makeEntry)
        val keytxt : EditText = view.findViewById(R.id.keytext)

        btn.setOnClickListener {
            if(keytxt.text.trim().length > 0){
                var encryptedtext =  encrypt(keytxt.text.toString(), txt.text.toString())
                try {
                    val fileOutputStream = activity?.openFileOutput("data.txt", Context.MODE_PRIVATE)
                    if (fileOutputStream != null) {
                        fileOutputStream.write(encryptedtext.toByteArray())
                    }
                    keytxt.setText("")
                    txt.setText("")
                    Toast.makeText(activity?.baseContext, "File saved successfully!", Toast.LENGTH_SHORT).show()
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            else{
                Toast.makeText(activity?.applicationContext, "Please enter a valid key", Toast.LENGTH_SHORT).show()
            }



        }


        return view
    }
}