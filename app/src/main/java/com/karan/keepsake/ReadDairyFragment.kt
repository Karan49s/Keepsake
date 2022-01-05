package com.karan.keepsake

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Environment
import android.util.Log
import android.widget.TextView
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.channels.AsynchronousFileChannel.open


class ReadDairyFragment : Fragment() {

    lateinit var showdata : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        val fileInputStream = activity?.openFileInput("data.txt")
        if (fileInputStream != null) {
            showdata.text = fileInputStream.read().toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_read_dairy, container, false)
        showdata = view.findViewById(R.id.showdata)

        //val fileOutputStream = activity?.openFileOutput("data.txt", Context.MODE_PRIVATE)



        return view
    }

}