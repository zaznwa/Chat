package com.example.telegram.view.adapters

import androidx.recyclerview.widget.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.geeks.chat.databinding.ItemMessageBinding
import com.geeks.chat.model.models.MessageResponse


class ChatAdapter(
    private val clickListener: (MessageResponse) -> Unit,
    private val longClickListener: (MessageResponse) -> Unit
) : ListAdapter<MessageResponse, ChatAdapter.ChatViewHolder>(DifUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            ItemMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = getItem(position)
        with(holder.binding) {
            text.text = message.message

        }
        holder.itemView.setOnClickListener {
            clickListener(message)
        }
        holder.itemView.setOnLongClickListener {
            longClickListener(message)
            true
        }
    }

    class DifUtil : DiffUtil.ItemCallback<MessageResponse>() {
        override fun areItemsTheSame(oldItem: MessageResponse, newItem: MessageResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MessageResponse, newItem: MessageResponse): Boolean {
            return oldItem == newItem
        }
    }

    inner class ChatViewHolder(val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root)
}