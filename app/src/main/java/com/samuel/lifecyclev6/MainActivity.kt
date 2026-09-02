package com.samuel.lifecyclev6

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

            // TODO: Logging for dev purposes, implement real functionality instead.
            Log.i("SAM", "username: $username");
            Log.i("SAM", "password: $password");
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}