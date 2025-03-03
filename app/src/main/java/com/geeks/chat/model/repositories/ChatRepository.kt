package com.geeks.chat.model.repositories

import com.geeks.chat.model.core.RetrofitClient
import com.geeks.chat.model.models.MessageResponse

class ChatRepository {

    suspend fun getChat(chatId: Int): List<MessageResponse> =
        RetrofitClient.chatService.getChat(chatId)

    suspend fun sendMessage(chatId: Int, message: String, senderId: String, receiverId: String): MessageResponse =
        RetrofitClient.chatService.sendMessage(chatId, message, senderId, receiverId)

    suspend fun deleteMessage(chatId: Int, messageId: Int) =
        RetrofitClient.chatService.deleteMessage(chatId, messageId)

    suspend fun updateMessage(chatId: Int, messageId: Int, newMessage: String) =
        RetrofitClient.chatService.updateMessage(chatId, messageId, newMessage)

}