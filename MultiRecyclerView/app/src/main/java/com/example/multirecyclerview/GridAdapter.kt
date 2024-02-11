package com.example.multirecyclerview
// GridViewAdapter.kt
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

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
        val imageView: ImageView
        if (convertView == null) {
            val displayMetrics = context.resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels
            val imageWidth = screenWidth / numColumns - (8 * (numColumns + 1)) / numColumns // Adjust spacing
            imageView = ImageView(context)
            imageView.layoutParams = AbsListView.LayoutParams(imageWidth, 500)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }
         Glide.with(context).load(imageUrls[position]).into(imageView)
        return imageView
    }
}


