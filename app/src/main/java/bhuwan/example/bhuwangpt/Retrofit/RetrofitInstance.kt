package bhuwan.example.bhuwangpt.Retrofit

import bhuwan.example.bhuwangpt.ApiService.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// If we don't use the Dependency Injection, we use this Retrofit Instance
// We can remove this while we use DI, we added this to the Module

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api-inference.huggingface.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}