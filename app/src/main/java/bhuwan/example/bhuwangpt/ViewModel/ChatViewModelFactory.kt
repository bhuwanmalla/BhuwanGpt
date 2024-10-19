package bhuwan.example.bhuwangpt.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bhuwan.example.bhuwangpt.Repository.ChatRepository

@Suppress("UNCHECKED_CAST")
class ChatViewModelFactory(private val repository: ChatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatViewModel(repository) as T
    }
}