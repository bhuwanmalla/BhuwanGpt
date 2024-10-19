package bhuwan.example.bhuwangpt.ApiService

import bhuwan.example.bhuwangpt.Models.GptRequest
import bhuwan.example.bhuwangpt.Models.MyGptResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("models/google/gemma-1.1-7b-it")
    suspend fun getGptResponse(
        @Header("Authorization") authorization: String,
        @Body body: GptRequest
    ): MyGptResponse
}