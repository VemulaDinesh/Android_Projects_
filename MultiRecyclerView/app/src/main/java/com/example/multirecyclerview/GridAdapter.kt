package com.example.multirecyclerview
// GridViewAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.multirecyclerview.databinding.ItemGridCellBinding

class GridViewAdapter(private val context: Context, private val imageUrls: List<String>,private val numColumns:Int) : BaseAdapter() {
    override fun getCount(): Int {
        return imageUrls.size
    }

    override fun getItem(position: Int): Any {
        return imageUrls[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemGridCellBinding.inflate(LayoutInflater.from(context))
            val displayMetrics = context.resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels
            val imageWidth = screenWidth / numColumns - (8 * (numColumns + 1)) / numColumns // Adjust spacing
            binding.root.layoutParams = AbsListView.LayoutParams(imageWidth, 500)
            binding.root.setPadding(8, 8, 8, 8)
         Glide.with(context).load(imageUrls[position]).into(binding.gridImageView)
        return binding.root
    }
}


