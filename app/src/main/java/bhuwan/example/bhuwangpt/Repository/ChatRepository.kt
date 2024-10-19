package bhuwan.example.bhuwangpt.Repository

import bhuwan.example.bhuwangpt.ApiService.ApiService
import bhuwan.example.bhuwangpt.Models.GptRequest
import bhuwan.example.bhuwangpt.Models.MyGptResponse
import javax.inject.Inject

class ChatRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getGptResponse(authorization: String, body: GptRequest): MyGptResponse {
        val response =
            apiService.getGptResponse(authorization = authorization, body = body)
        return response
    }
}