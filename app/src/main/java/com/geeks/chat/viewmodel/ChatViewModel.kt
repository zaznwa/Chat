package com.geeks.chat.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.chat.model.models.MessageResponse
import com.geeks.chat.model.repositories.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val repository = ChatRepository()

    private val _messages = MutableLiveData<List<MessageResponse>>()
    val messages: LiveData<List<MessageResponse>> get() = _messages

    fun getChat(chatId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getChat(chatId)
                _messages.postValue(response)
            } catch (e: Exception) {
                Log.e("ololo", "getChat: ${e.message}")
            }
        }
    }

    fun sendMessage(chatId: Int, message: String, senderId: String, receiverId: String) {
        viewModelScope.launch {
            try {
                repository.sendMessage(chatId, message, senderId, receiverId)
                refreshChat(chatId)
            } catch (e: Exception) {
                Log.e("ololo", "sendMessage: ${e.message}")
            }
        }
    }

    fun updateMessage(chatId: Int, messageInt: Int, newMessage: String) {
        viewModelScope.launch {
            try {
                repository.updateMessage(chatId, messageInt, newMessage)
                refreshChat(chatId)
            } catch (e: Exception) {
                Log.e("ololo", "updateMessage: ${e.message}")            }
        }
    }

    fun deleteMessage(chatId: Int, messageId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteMessage(chatId, messageId)
                refreshChat(chatId)
            } catch (e: Exception) {
                Log.e("ololo", "deleteMessage: ${e.message}")
            }
        }
        getChat(chatId)
    }

    private fun refreshChat(chatId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getChat(chatId)
                _messages.postValue(response)
            } catch (e: Exception) {
                Log.e("ololo", "refreshChat: ${e.message}")
            }
        }
    }
}
