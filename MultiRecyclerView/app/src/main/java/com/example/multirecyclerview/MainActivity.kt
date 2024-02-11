package com.example.multirecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize an empty list of Section objects and add sections horizontally
        val url = "https://images.unsplash.com/photo-1417325384643-aac51acc9e5d?q=75&fm=jpg&w=1080&fit=max"

        val sections = mutableListOf<Section>().apply {
            add(Section("Card", 0, "Card", "Subtitle 1", listOf(Card("Image", "", url))))
            add(Section("Grid", 3, "Title 3", "Subtitle 3", listOf(
                Card("Image", "", url),
                Card("Image", "", url),
                Card("Image", "", url)
            )))
            add(Section("Carousal", 0, "Title 2", "Subtitle 2", listOf(
                Card("Image", "", url),
                Card("Image", "", url),
                Card("Image", "", url)
            )))

        }

        val adapter = MainAdapter(sections)
        recyclerView.adapter = adapter
    }
}
