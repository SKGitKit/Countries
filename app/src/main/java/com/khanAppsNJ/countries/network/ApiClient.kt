package com.khanAppsNJ.countries.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        // Base URL for the API endpoint
        private const val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/"

        // Retrofit instance
        private var retrofit: Retrofit? = null

        /**
         * Gets the Retrofit instance. If it does not exist, it is created.
         * @return Retrofit instance
         */
        fun getClient(): Retrofit? {
            if (retrofit == null) {
                // Create new Retrofit instance if it does not exist
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}