package com.samuel.lifecyclev6

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        // TODO: Might remove, testing to see that it works as intended.
        var username = intent.getStringExtra("username");
        Log.i("SAM", "A2-username: $username")
        var password = intent.getStringExtra("password");
        Log.i("SAM", "A2-password: $password")

        val titleText = findViewById<TextView>(R.id.titleText);
        titleText.text = "Welcome $username";

        //TODO: Add components for user input
        /*
        Weight: TextInputEditText
        Height: TextInputEditText => SHOW BMI
        Date of Birth: DatePickerDialog
        Wake up Time: TimePickerDialog
        Favorite Color: Spinner?
        Right or Left handed: Switch/Toggle/Radiobuttons
         */


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}