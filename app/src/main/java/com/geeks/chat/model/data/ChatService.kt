package com.geeks.chat.model.data

import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.geeks.chat.model.models.MessageResponse
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ChatService {

    @GET("chat/{chatId}")
    suspend fun getChat(@Path("chatId") chatId: Int): List<MessageResponse>

    @FormUrlEncoded
    @POST("chat/{send}")
    suspend fun sendMessage(
        @Field("chatId") chatId: Int,
        @Field("message") message: String,
        @Field("senderId") senderId: Int,
        @Field("receiverId") receiverId: Int,
    ): MessageResponse

    @FormUrlEncoded
    @PUT("chat/{chatId}/message/{messageId}")
    suspend fun updateMessage(
        @Path("chatId") chatId: Int,
        @Path("messageId") messageId: Int,
        @Field("newMessage") newMessage: String,
    ): MessageResponse

    @DELETE("chat/{chatId}/message/{messageId}")
    suspend fun deleteMessage(
        @Path("chatId") chatId: Int,
        @Path("messageId") messageId: Int,
    ): MessageResponse
}