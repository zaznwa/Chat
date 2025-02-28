package com.geeks.chat.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable

data class MessageResponse(

    @SerialName("chatId")
    var chatId: Int?,
    @SerialName("id")
    var id: Int?,
    @SerialName("message")
    var message: String?,
    @SerialName("receiverId")
    var receiverId: String?,
    @SerialName("senderId")
    var senderId: String?,
    @SerialName("timestamp")
    var timestamp: String?,
)
