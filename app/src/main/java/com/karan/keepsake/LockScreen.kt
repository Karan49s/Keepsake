package com.karan.keepsake

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

private val PREF_NAME = "login-data"
lateinit var sharedPref: SharedPreferences

lateinit var pass : String
lateinit var confirm_pass : String



enum class states {
    LOGIN, SET_PASSWORD, CONFIRM_PASSWORD
}

var pageState = states.LOGIN


class LockScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)
        sharedPref =  getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val first = sharedPref.getBoolean("isFirst",true);
        if(first){
            pageState = states.SET_PASSWORD
            StateChangeListner()
        }




    }

    fun StateChangeListner(){
        when(pageState){
            states.SET_PASSWORD->{

            }
            states.LOGIN->{

            }
            states.CONFIRM_PASSWORD->{

            }
        }
    }

    fun key_press(view: android.view.View) {
        when(view.id){
            R.id.pass0->{
                pass += "0"
            }
            R.id.pass1->{
                pass+="1"
            }
            R.id.pass2->{
                pass+="2"
            }
            R.id.pass3->{
                pass+="3"
            }
            R.id.pass4->{
                pass+="4"
            }
            R.id.pass5->{
                pass+="5"
            }
            R.id.pass6->{
                pass+="6"
            }
            R.id.pass7->{
                pass+="7"
            }
            R.id.pass8->{
                pass+="8"
            }
            R.id.pass9->{
                pass+="9"
            }
            R.id.erase->{
                if(pass.length>0){
                    pass.dropLast(1)
                }
            }
            R.id.submit->{
                when(pageState){
                    states.CONFIRM_PASSWORD->{
                        if(confirm_pass == pass){
                            setpass()
                            login()
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
                        pageState = states.CONFIRM_PASSWORD
                        StateChangeListner()
                    }
                }
            }
        }
    }

    private fun login() {
        Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show()
    }

    fun setpass(){
        sharedPref.edit().putString("password",pass);
        sharedPref.edit().commit()
    }

}