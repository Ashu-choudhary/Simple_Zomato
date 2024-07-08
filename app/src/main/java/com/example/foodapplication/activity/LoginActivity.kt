package com.example.foodapplication.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodapplication.R

class LoginActivity : AppCompatActivity() {

    lateinit var etmobileno: EditText
    lateinit var etpassword: EditText
    lateinit var btnlogin: Button
    lateinit var txtpassforgot: TextView
    lateinit var txtregster: TextView

    lateinit var shared_prefernces: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


        title = "Log In"
        shared_prefernces = getSharedPreferences(getString(R.string.preferences_file), MODE_PRIVATE)

        val isLoggedIn = shared_prefernces.getBoolean("isLoggedIn",false)
        setContentView((R.layout.activity_login))
        if(isLoggedIn){
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        etmobileno = findViewById(R.id.et_mobile_no)
        etpassword = findViewById(R.id.et_password)
        btnlogin = findViewById(R.id.btn_login)
        txtpassforgot = findViewById(R.id.txt_passforgot)
        txtregster = findViewById(R.id.txt_regster)

        val validmobileno = "7983132721"
        val validpassword = "foodapp"

        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnlogin.setOnClickListener {
            val mobile = etmobileno.text.toString()
            val password = etpassword.text.toString()

            if(mobile == validmobileno){
                if(password == validpassword){
                    savepreferences()
                    startActivity(intent)
                }
                else{
                    Toast.makeText(
                        this@LoginActivity,
                        "Enter correct Password",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
            else{
                Toast.makeText(
                    this@LoginActivity,
                    "Enter correct Mobile No",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun savepreferences(){
        shared_prefernces.edit().putBoolean("isLoggedIn",true).apply()
    }
}