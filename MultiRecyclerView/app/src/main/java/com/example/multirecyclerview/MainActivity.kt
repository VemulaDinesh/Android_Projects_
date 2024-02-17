package com.example.multirecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val repository=HomeRepository()
        viewModel = HomeViewModel(repository)
        viewModel.getSections()
        viewModel.mainData.observe(this, Observer { mainData ->
            val adapter = MainAdapter(mainData.sections)
            recyclerView.adapter = adapter
        })
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize an empty list of Section objects and add sections horizontally
//        val url = "https://images.unsplash.com/photo-1417325384643-aac51acc9e5d?q=75&fm=jpg&w=1080&fit=max"
//
//        val sections = mutableListOf<Section>().apply {
////            add(
////                Section(
////                    "Card", "Subtitle 1", "Card", listOf(
////                        Card("Image", "", url, "", "Image", 0)
////                    )
////                )
////            )
//            add(
//                Section(
//                    "Title 3", "Subtitle 3", "grid", listOf(
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//                        Card("Image", "", url, "", "Image", 0),
//
//                    ), 12
//                )
//            )
////            add(
////                Section(
////                    "Title 2", "Subtitle 2", "Carousal", listOf(
////                        Card("Image", "", url, "", "Image", 0),
////                        Card("Image", "", url, "", "Image", 0),
////                        Card("Image", "", url, "", "Image", 0)
////                    )
////                )
////            )
//        }
//
//        val adapter = MainAdapter(sections)
//        recyclerView.adapter = adapter
    }


    }

