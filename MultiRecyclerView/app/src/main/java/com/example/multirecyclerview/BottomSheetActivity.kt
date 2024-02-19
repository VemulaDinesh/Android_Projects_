package com.example.multirecyclerview
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.multirecyclerview.databinding.BottomSheetBinding
import com.google.gson.Gson

//import com.example.multirecyclerview.databinding.BottomSheetBinding


class BottomSheetActivity : AppCompatActivity() {
    var id: Int = 0
    private lateinit var binding: BottomSheetBinding // Update to your actual binding name
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BottomSheetBinding.inflate(layoutInflater) // Update to your actual layout binding class
        //binding.progressBar.visibility= View.VISIBLE
        setContentView(binding.root)
        getDeeplinkData()
        val repository = HomeRepository()
        viewModel = HomeViewModel(repository)
        viewModel.getMediaDetails(id)

        viewModel.mediaData.observe(this, Observer { mediaData ->
            val jsonString = Gson().toJson(mediaData)
            Log.d("BottomSheetActivity", "Received media data JSON: $jsonString")

            // Update UI elements using binding

            binding.apply {
               titleTextView.text = mediaData.media.title // Update with actual data fields
                Glide.with(root.context).load(mediaData.media.posterImageUrl).into(imageView)
                summary.text=mediaData.media.summary
                rating.text=mediaData.media.rating
                release.text=mediaData.media.releaseDate
                trailer.text=mediaData.media.trailerUrl

            }
        })
        //binding.progressBar.visibility= View.GONE
        binding.view.setOnClickListener{
            finish()
        }
    }
    private fun getDeeplinkData() {
        if (intent.action == Intent.ACTION_VIEW) {
            val uri: Uri? = intent.data
            if (uri != null) {
                id = uri.getQueryParameter("id")!!.toInt()
            }
        }
    }
}


