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

class MainAdapter(private val sections: List<Section>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.carouselRecyclerView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        fun bind(imageUrls: List<String>) {
            val adapter = CarouselAdapter(imageUrls)
            recyclerView.adapter = adapter
            val layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = layoutManager
        }
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val gridView: GridView = itemView.findViewById(R.id.gridRecyclerView )
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        fun bind(imageUrls: List<String>,noOfColumns:Int) {
//            val layoutManager = GridLayoutManager(itemView.context, noOfColoumns)
//            recyclerView.layoutManager = layoutManager
//            val adapter = GridAdapter(imageUrls)
//            recyclerView.adapter = adapter
            val adapter = GridViewAdapter(itemView.context, imageUrls, noOfColumns)
            gridView.adapter = adapter
            gridView.numColumns = noOfColumns
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_IMAGE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_view, parent, false)
                ImageViewHolder(view)
            }
            VIEW_TYPE_CAROUSEL -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel_view, parent, false)
                CarouselViewHolder(view)
            }
            VIEW_TYPE_GRID -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_view, parent, false)
                GridViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val section = sections[position]
        when (holder.itemViewType) {
            VIEW_TYPE_IMAGE -> {
           val imageViewHolder = holder as ImageViewHolder
                imageViewHolder.titleTextView.text = section.title
                val imageUrl = section.cards[0].imageUrl
                Glide.with(imageViewHolder.itemView.context)
                    .load(imageUrl)
                    .override(500, 500)
                    .into(imageViewHolder.imageView)
                //imageViewHolder.imageView.setImageResource(R.drawable.img)
            }
            VIEW_TYPE_CAROUSEL -> {
                val carouselViewHolder = holder as CarouselViewHolder
                carouselViewHolder.titleTextView.text = section.title
                val imageUrls = section.cards.map { it.imageUrl }
                carouselViewHolder.bind(imageUrls)
            }
            VIEW_TYPE_GRID -> {
                val gridViewHolder = holder as GridViewHolder
                gridViewHolder.titleTextView.text = section.title
                val imageUrls = section.cards.map { it.imageUrl }
                val noOfColoumns=section.no_of_coloumns
                gridViewHolder.bind(imageUrls,noOfColoumns)
            }
        }
    }

    override fun getItemCount(): Int = sections.size

    override fun getItemViewType(position: Int): Int {
        val section = sections[position]
        return when (section.type) {
            "Card" -> VIEW_TYPE_IMAGE
            "Carousal" -> VIEW_TYPE_CAROUSEL
            "Grid" -> VIEW_TYPE_GRID
            else -> throw IllegalArgumentException("Invalid section type")
        }
    }



    companion object {
        private const val VIEW_TYPE_IMAGE = 0
        private const val VIEW_TYPE_CAROUSEL = 1
        private const val VIEW_TYPE_GRID = 2
    }
}
