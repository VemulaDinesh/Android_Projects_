package com.example.image
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import com.example.image.model.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import androidx.appcompat.app.AlertDialog
import android.net.NetworkCapabilities


class MainActivity2 : AppCompatActivity() {
    private lateinit var gridView: GridView
    private var photosList: MutableList<Photo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Back"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        gridView = findViewById(R.id.gridView)

        if (isNetworkAvailable()) {
            val mode = intent.getStringExtra("mode")
            if (mode == "random") {
                fetchRandomData()
            } else if (mode == "list") {
                fetchData()
            }
        } else {
            showNoNetworkDialog()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    private fun fetchData() {
        val call = RetrofitClient.instance.getPhotos(page = 1, perPage = 30)
        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        photosList.addAll(it)
                        val numColumns = 3
                        val adapter = PhotoAdapter(this@MainActivity2, photosList, numColumns)
                        gridView.adapter = adapter
                        gridView.numColumns = numColumns
                    }
                } else {
                    Log.e("MainActivity2", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.e("MainActivity2", "Failed to fetch data", t)
            }
        })
    }

    private fun fetchRandomData() {
        val call = RetrofitClient.instance.getRandomPhotos(count = 1)
        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        photosList.addAll(it)
                        val numColumns = 1
                        val adapter = PhotoAdapter(this@MainActivity2, photosList, numColumns)
                        gridView.adapter = adapter
                        gridView.numColumns = numColumns
                    }
                } else {
                    Log.e("MainActivity2", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.e("MainActivity2", "Failed to fetch random data", t)
            }
        })
    }
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }


    private fun showNoNetworkDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Network Connection")
        builder.setMessage("Please check your internet connection and try again.")
        builder.setCancelable(false)
        builder.setPositiveButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
