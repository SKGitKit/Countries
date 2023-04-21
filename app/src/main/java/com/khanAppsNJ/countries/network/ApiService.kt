package com.khanAppsNJ.countries.network

import com.khanAppsNJ.countries.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): List<Country>

    companion object {
        fun create(): ApiService {
            val retrofit = ApiClient.getClient()
            return retrofit!!.create(ApiService::class.java)
        }
    }

}
