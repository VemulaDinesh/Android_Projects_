package com.example.image
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.example.image.model.Photo
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import androidx.core.content.ContextCompat
import com.squareup.picasso.Callback


class PhotoAdapter(private val context: Context, private val photosList: List<Photo>, private val numColumns :Int) : BaseAdapter() {
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
        val shimmer = Shimmer.AlphaHighlightBuilder() // You can configure shimmer effect as you want
            .setAutoStart(true)
            .setBaseAlpha(0.7f)
            .setHighlightAlpha(0.6f)
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setTilt(0f)
            .build()
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
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

        Picasso.get()
            .load(photo.urls.regular)
            .placeholder(/* placeholderDrawable = */ shimmerDrawable)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    // Hide shimmer when image is loaded successfully
                    shimmerDrawable.stopShimmer()
                }

                override fun onError(e: Exception?) {
                    // Handle error if image loading fails
                    // You may want to show an error placeholder here
                }
            })

        return imageView
    }


}
