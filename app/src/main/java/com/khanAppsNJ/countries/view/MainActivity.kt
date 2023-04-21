package com.khanAppsNJ.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.khanAppsNJ.countries.network.NetworkConnection
import com.khanAppsNJ.countries.databinding.ActivityMainBinding
import com.khanAppsNJ.countries.network.ApiClient
import com.khanAppsNJ.countries.network.ApiService
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope {

    // Declare necessary variables
    private lateinit var binding: ActivityMainBinding
    private lateinit var countriesRecyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val coroutineJob = Job()
    override val coroutineContext
        get() = Dispatchers.Main + coroutineJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up the view and set up the SwipeRefreshLayout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }

        // Load the data
        loadData()
    }

    private fun loadData() {
        if (NetworkConnection.isNetworkConnected(this)) {
            // If there is internet connection, make a network call to fetch the countries data
            val apiClient = ApiClient.getClient()
            val apiService = apiClient?.create(ApiService::class.java)
            launch {
                try {
                    // Use coroutine to handle the asynchronous network call
                    val response = withContext(Dispatchers.IO) {
                        apiService?.getCountries()
                    }
                    Log.d("Here:", "We got a response, here is one country ${response?.get(0)}")

                    // Set up the RecyclerView and its adapter to display the fetched countries data
                    countriesRecyclerView = binding.countriesRV.apply {
                        adapter = CountriesAdapter(response)
                        layoutManager = LinearLayoutManager(applicationContext)
                    }

                } catch (e: Exception) {
                    Log.e("MainActivity", "Error fetching countries: $e")
                    Toast.makeText(applicationContext, "No Result, Swipe down refresh", Toast.LENGTH_SHORT).show()
                }
                swipeRefreshLayout.isRefreshing = false
            }

        } else {
            // If there is no internet connection, show a message to the user and hide the SwipeRefreshLayout
            Toast.makeText(this, "No Internet, Swipe down refresh", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel the coroutine job when the activity is destroyed
        coroutineJob.cancel()
    }
}

