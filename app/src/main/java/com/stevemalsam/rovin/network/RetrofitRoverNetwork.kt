package com.stevemalsam.rovin.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"
private const val API_KEY = "DEMO_KEY"

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(APIKeyInterceptor())
    .build()


class APIKeyInterceptor(val apiKey : String = API_KEY) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalURL = originalRequest.url

        val url = originalURL.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build();

        val request = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface RetrofitRoverNetworkApi {
    @GET("rovers/curiosity/photos")
    suspend fun getPhotos(@Query("sol") sol:Int, @Query("page") page: Int): String
}

object MarsApi {
    val retrofitService: RetrofitRoverNetworkApi by lazy {
        retrofit.create(RetrofitRoverNetworkApi::class.java)
    }
}
