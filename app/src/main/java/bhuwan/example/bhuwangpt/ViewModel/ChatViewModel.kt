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

    fun fetchChatGptData(apiData: GptRequest) {
        viewModelScope.launch {
            try {
                val response =
                    repository.getGptResponse(
                        "YOUR_API_KEY",
                        apiData
                    )

//                Bearer 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
                Log.e("Data", response.get(0).generated_text)
                _data.value = response
            } catch (e: Exception) {
                _data.value = null
            }
        }
    }


}