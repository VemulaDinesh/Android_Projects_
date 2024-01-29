package com.example.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomImageButton = findViewById<Button>(R.id.randomImageButton)
        val listPhotosButton = findViewById<Button>(R.id.listPhotosButton)

        randomImageButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("mode", "random")
            startActivity(intent)
        }

        listPhotosButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("mode", "list")
            startActivity(intent)
        }
    }
}
