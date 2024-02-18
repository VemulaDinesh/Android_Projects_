package com.example.multirecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.multirecyclerview.databinding.ItemCarouselCellBinding
import com.example.multirecyclerview.databinding.ItemCarouselViewBinding

class CarouselAdapter(private val imageUrls: List<String>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding=ItemCarouselCellBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val imageUrl = imageUrls[position]
        holder.bind(imageUrl)
    }

    override fun getItemCount(): Int = imageUrls.size

    inner class CarouselViewHolder(private val binding: ItemCarouselCellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            Glide.with(itemView.context)
                .load(imageUrl) // Error image if the load fails
                .centerInside()
                .into(binding.carouselImageView)
        }
    }
}
