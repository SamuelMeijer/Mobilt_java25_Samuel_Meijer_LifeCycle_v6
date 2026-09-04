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

            // TODO: REMOVE! Logging for dev purposes
            Log.i("SAM", "username: $username");
            Log.i("SAM", "password: $password");

            var numbOfErrors = 0;

            if (username.isEmpty()) {
                usernameInput.error = "Username cannot be empty"
                numbOfErrors++
            }

            if (password.isEmpty()) {
                passwordInput.error = "Password cannot be empty"
                numbOfErrors++
            }

            // TODO: Add pre def. values (G) and validate user before proceeding (both G and VG)
            if (username.lowercase(getDefault()) != "admin" && password.lowercase(getDefault()) != "admin") {
                loginBtn.error = "No such username or password exists"
                // TODO: Add Database support for user data (VG) and check for that instead
            } else {
                // Sending user to Activity2 if no errors occurred
                //  TODO: Remove if numberror == 0 since check is already made earlier?
                if (numbOfErrors == 0) {
                    // TODO: Store user credentials to sharedPreferences instead? (for now, switch to a more secure version later)
                    val intent = Intent(this, MainActivity2::class.java);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}