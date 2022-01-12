package com.karan.keepsake

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

private val PREF_NAME = "login-data"
lateinit var sharedPref: SharedPreferences


var pass=""
var confirm_pass=""



enum class states {
    LOGIN, SET_PASSWORD, CONFIRM_PASSWORD
}

var pageState = states.LOGIN



class LockScreen : AppCompatActivity() {
    lateinit var pass_view : EditText
    lateinit var submit : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)
        sharedPref =  getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        pass_view = findViewById(R.id.pass_view)
        submit = findViewById(R.id.submit)

        val first = sharedPref.getBoolean("isFirst",true);
        if(first){
            pageState = states.SET_PASSWORD

            StateChangeListner()
            sharedPref.edit().putBoolean("isFirst",false).commit()
        }




    }

    fun StateChangeListner(){
        when(pageState){
            states.SET_PASSWORD->{
                submit.text = "SET PASSWORD"
            }
            states.LOGIN->{
                submit.text = "Login"
            }
            states.CONFIRM_PASSWORD->{
                submit.text = "CONFIRM PASSWORD"
            }
        }
    }

    fun key_press(view: android.view.View) {
        pass = pass_view.text.toString()
        if (pass.trim().length >3){
            when(pageState){
                states.CONFIRM_PASSWORD->{
                    if(confirm_pass == pass){
                        sharedPref.edit().putString("password", confirm_pass).commit();
                        login()
                    }else{
                        Toast.makeText(this, "Password Does not match!!", Toast.LENGTH_SHORT).show()
                    }
                }
                states.LOGIN->{
                    if (sharedPref.getString("password","").toString()==pass) {
                        login()
                    }else{
                        Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show()
                    }
                }
                states.SET_PASSWORD->{
                    confirm_pass= pass
                    pass=""
                    pass_view.setText("")
                    pageState = states.CONFIRM_PASSWORD
                    StateChangeListner()
                }
            }
        }else{
            Toast.makeText(this, "Password should be minimum 4 character long", Toast.LENGTH_SHORT).show()
        }
                

    }

    private fun login() {
        Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent(this,Home::class.java)
        startActivity(intent)
    }

}