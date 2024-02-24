package com.example.multirecyclerview

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
//import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.multirecyclerview.databinding.ItemCarouselCellBinding
import com.example.multirecyclerview.databinding.ItemCarouselViewBinding

class CarouselAdapter(private val listOfCards: List<Card>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding=ItemCarouselCellBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val card = listOfCards[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int = listOfCards.size

    inner class CarouselViewHolder(private val binding: ItemCarouselCellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            Glide.with(itemView.context)
                .load(card.imageUrl) // Error image if the load fails
                .centerInside()
                .into(binding.carouselImageView)
            binding.root.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(card.deeplink))
                binding.root.context.startActivity(intent)
            }

        }

    }
}
