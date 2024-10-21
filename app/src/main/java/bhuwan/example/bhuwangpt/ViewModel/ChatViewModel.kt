package bhuwan.example.bhuwangpt.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bhuwan.example.bhuwangpt.Models.GptRequest
import bhuwan.example.bhuwangpt.Models.MyGptResponse
import bhuwan.example.bhuwangpt.Repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel() {

    private val _data = MutableLiveData<MyGptResponse?>()
    val data: LiveData<MyGptResponse?> = _data

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchChatGptData(apiData: GptRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response =
                    repository.getGptResponse(
                        "USE_YOUR_API_KEY",
                        apiData
                    )
                Log.e("Data", response.get(0).generated_text)
                _data.value = response
            } catch (e: Exception) {
                _data.value = null
            }
            _isLoading.value = false
        }
    }


}