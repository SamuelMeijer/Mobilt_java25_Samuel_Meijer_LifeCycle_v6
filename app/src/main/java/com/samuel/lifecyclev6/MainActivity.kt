package com.samuel.lifecyclev6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Locale
import java.util.Locale.getDefault
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Components
        val usernameInput = findViewById<TextInputEditText>(R.id.usernameInput);
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput);
        val loginBtn = findViewById<Button>(R.id.loginBtn);

        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString().trim();
            val password = passwordInput.text.toString().trim();

            if (username.isEmpty()) {
                usernameInput.error = "Username cannot be empty"
            }

            if (password.isEmpty()) {
                passwordInput.error = "Password cannot be empty"
            }

            if (username.lowercase(getDefault()) != "admin" || password.lowercase(getDefault()) != "admin") {
                loginBtn.error = "No such username or password exists"
                // TODO: Add Database support for user data and check against that instead
            } else {
                // Sending user to Activity2
                    // TODO: Storing user credentials in intent for now, switch to a more secure version later
                    val intent = Intent(this, MainActivity2::class.java);
                    intent.putExtra("username", username);
                    intent.putExtra("isLoggedIn", true);
                    startActivity(intent);
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}