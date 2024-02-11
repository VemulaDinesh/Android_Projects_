package com.example.multirecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarouselAdapter(private val imageUrls: List<String>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel_cell, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val imageUrl = imageUrls[position]
        holder.bind(imageUrl,500,500)
    }

    override fun getItemCount(): Int = imageUrls.size

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carouselImageView: ImageView = itemView.findViewById(R.id.carouselImageView)

        fun bind(imageUrl: String, width: Int, height: Int) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .override(width, height) // Error image if the load fails
                .into(carouselImageView)
            //gridImageView.setImageResource(R.drawable.img)
        }
    }
}
