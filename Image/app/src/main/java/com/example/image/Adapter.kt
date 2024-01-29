package com.example.image
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.example.image.model.Photo

class PhotoAdapter(private val context: Context, private val photosList: List<Photo>, private val numColumns :Int) : BaseAdapter() {
    //var numColumns = 2
    override fun getCount(): Int {
        return photosList.size
    }

    override fun getItem(position: Int): Any {
        return photosList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val photo = photosList[position]
        val imageView: ImageView

        if (convertView == null) {
            val displayMetrics = context.resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels
            // Set the number of columns

            val imageWidth = screenWidth / numColumns - (8 * (numColumns + 1)) / numColumns // Adjust spacing

            imageView = ImageView(context)
            imageView.layoutParams = AbsListView.LayoutParams(imageWidth, imageWidth)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }

        Picasso.get().load(photo.urls.regular).into(imageView)
        return imageView
    }

}
