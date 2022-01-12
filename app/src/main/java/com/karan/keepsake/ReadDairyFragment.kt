package com.karan.keepsake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.karan.keepsake.SimpleCrypto.decrypt
import java.io.*
import java.lang.Exception


class ReadDairyFragment : Fragment() {

    lateinit var showdata : TextView
    lateinit var dcrypt : Button
    lateinit var key : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_read_dairy, container, false)
        showdata = view.findViewById(R.id.showdata)
        key = view.findViewById(R.id.key)
        dcrypt = view.findViewById(R.id.decrypt)



        dcrypt.setOnClickListener {
            readDairy()
        }



        return view
    }

    fun readDairy(){
        if(key.text.trim().toString().length>0){
            val fileInputStream = activity?.openFileInput("data.txt")
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = "yo"
            while (run {
                    text = bufferedReader.readLine()
                    text
                } != null) {
                stringBuilder.append(text)
            }
            var decryptedtext = stringBuilder.toString()
            try {
                decryptedtext = decrypt(key.text.toString(), stringBuilder.toString())
            }catch (e : Exception){
                showdata.text = e.toString()
                Toast.makeText(activity?.applicationContext, "Something went wrong It could be the wrong key", Toast.LENGTH_SHORT).show()
            }

            showdata.text = decryptedtext
            key.setText("")

        }else{
            Toast.makeText(activity?.applicationContext, "Wrong Key", Toast.LENGTH_SHORT).show()
        }

    }

}