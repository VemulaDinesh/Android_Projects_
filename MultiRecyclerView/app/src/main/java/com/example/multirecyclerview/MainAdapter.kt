package com.example.multirecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.GridLayoutManager
import com.example.multirecyclerview.databinding.ItemCarouselViewBinding
import com.example.multirecyclerview.databinding.ItemGridViewBinding
import com.example.multirecyclerview.databinding.ItemImageViewBinding

class MainAdapter(private val sections: List<Section>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ImageViewHolder(private val binding: ItemImageViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(section: Section) {
            binding.titleTextView.text = section.title
            val imageUrl = section.cards[0].imageUrl
            Glide.with(binding.imageView.context)
                .load(imageUrl)
                .centerInside()
                .into(binding.imageView)
        }
    }

    inner class CarouselViewHolder(private val binding: ItemCarouselViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(section: Section) {
            binding.titleTextView.text = section.title
            //val imageUrls = section.cards.map { it.imageUrl }
            val adapter = CarouselAdapter(section.cards)
            binding.carouselRecyclerView.adapter = adapter
            val layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.carouselRecyclerView.layoutManager = layoutManager
        }
    }

    inner class GridViewHolder(private val binding: ItemGridViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(section: Section) {
            binding.titleTextView.text = section.title
            //val imageUrls = section.cards.map { it.imageUrl }
            val noOfColumns = section.numberOfColumns ?: 1
            val adapter = GridViewAdapter(binding.root.context, section.cards, noOfColumns)
            binding.gridRecyclerView.adapter = adapter
            binding.gridRecyclerView.numColumns = noOfColumns
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_IMAGE -> {
                val binding = ItemImageViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ImageViewHolder(binding)
            }
            VIEW_TYPE_CAROUSEL -> {
                val binding = ItemCarouselViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CarouselViewHolder(binding)
            }
            VIEW_TYPE_GRID -> {
                val binding = ItemGridViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GridViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val section = sections[position]
        when (holder.itemViewType) {
            VIEW_TYPE_IMAGE -> {
                val imageViewHolder = holder as ImageViewHolder
                imageViewHolder.bind(section)
            }
            VIEW_TYPE_CAROUSEL -> {
                val carouselViewHolder = holder as CarouselViewHolder
                carouselViewHolder.bind(section)
            }
            VIEW_TYPE_GRID -> {
                val gridViewHolder = holder as GridViewHolder
                gridViewHolder.bind(section)
            }
        }
    }

    override fun getItemCount(): Int = sections.size

    override fun getItemViewType(position: Int): Int {
        val section = sections[position]
        return when (section.type) {
            "banner" -> VIEW_TYPE_IMAGE
            "carousel" -> VIEW_TYPE_CAROUSEL
            "grid" -> VIEW_TYPE_GRID
            else -> throw IllegalArgumentException("Invalid section type")
        }
    }
    companion object {
        private const val VIEW_TYPE_IMAGE = 0
        private const val VIEW_TYPE_CAROUSEL = 1
        private const val VIEW_TYPE_GRID = 2
    }
}
