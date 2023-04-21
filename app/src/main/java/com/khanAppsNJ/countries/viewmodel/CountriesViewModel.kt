package com.khanAppsNJ.countries.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khanAppsNJ.countries.model.Country
import com.khanAppsNJ.countries.network.ApiService
import androidx.lifecycle.viewModelScope
import com.khanAppsNJ.countries.network.ApiClient
import kotlinx.coroutines.launch

class CountriesViewModel() : ViewModel() {


    private val _countries = MutableLiveData<List<Country>>()

    val countries : List<Country>?
    get() = _countries?.value


    // Function to fetch the list of countries from the API
    @SuppressLint("SuspiciousIndentation")
    fun fetchCountries() {
        viewModelScope.launch {
                // Create an instance of the API service
            val apiClient =  ApiClient.getClient()
            val apiService = apiClient?.create(ApiService::class.java)
                try {
                    // Fetch the countries from the API
                    val response = apiService?.getCountries()
                    // Update the value of the MutableLiveData with the fetched countries
                    _countries.value = response
                    Log.d("CountriesViewModel", "No Error Here ${_countries?.value?.get(0)}")
                } catch (e: Exception) {
                    // Log an error if there was a problem fetching the countries
                    Log.e("CountriesViewModel", "Error fetching countries: $e")
                }
            }
        }
    }
