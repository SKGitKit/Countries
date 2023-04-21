import com.khanAppsNJ.countries.model.Country
import org.junit.Assert
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class ApiTest {

    @Test
    fun testApiResponse() {
        Assert.assertEquals(true, apiTest.testApi())
    }

    object apiTest {
        private val baseUrl =
            "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/"
        private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun testApi(): Boolean {
            val apiService = retrofit.create(ApiService::class.java)
            val call = apiService.getCountries()
            return call.execute().isSuccessful
        }

        interface ApiService {
            @GET("db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
            fun getCountries(): Call<List<Country>>
        }

    }
}
