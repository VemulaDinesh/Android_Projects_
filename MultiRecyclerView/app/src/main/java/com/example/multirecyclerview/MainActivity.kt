package com.example.multirecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multirecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
       binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val repository=HomeRepository()
        viewModel = HomeViewModel(repository)
        viewModel.getSections()
        viewModel.mainData.observe(this, Observer { mainData ->
            val adapter = MainAdapter(mainData.sections)
            binding.recyclerView.adapter = adapter
        })
    }
}

