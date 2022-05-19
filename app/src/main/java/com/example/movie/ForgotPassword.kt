package com.example.movie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ForgotPassword : AppCompatActivity() {

    //create class auth
    lateinit var auth: FirebaseAuth

    //create variable
    lateinit var editfor: EditText
    lateinit var btnfor: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        // intialiema variabile
        editfor = findViewById(R.id.editfor)
        btnfor = findViewById(R.id.btnfor)
        auth = FirebaseAuth.getInstance()

        btnfor.setOnClickListener {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            if (editfor.text.toString().isNullOrEmpty())
                Toast.makeText(this, "Email Address is not provided", Toast.LENGTH_SHORT).show()
            else {
            }
            // Reset password
            Firebase.auth.sendPasswordResetEmail(editfor.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(this, "Password is remove!!!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,Register::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Password is not remove!!!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}


