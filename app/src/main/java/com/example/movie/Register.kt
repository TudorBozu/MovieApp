package com.example.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class Register : AppCompatActivity() {
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button

    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // forgot password button
        val buttonref = findViewById<Button>(R.id.btnF)
        buttonref.setOnClickListener(){
            val intent = Intent(this,ForgotPassword::class.java)
            startActivity(intent)
        }

        //redirect button
        val buttonredirect = findViewById<Button>(R.id.Button_redirect_register)
         buttonredirect.setOnClickListener {
             val intent = Intent (this,login_screen::class.java)
             startActivity(intent)
         }

        btnLogin = findViewById(R.id.log)
        etEmail = findViewById(R.id.edlog)
        etPass = findViewById(R.id.edpaslog)

        // auth
        auth = FirebaseAuth.getInstance()

        // button login
        btnLogin.setOnClickListener {
            login()
        }
    }



    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Log.d("testlogin1", "Log successfully")
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MovieScreen1::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.d("testlogin2", "Log is faild")
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
