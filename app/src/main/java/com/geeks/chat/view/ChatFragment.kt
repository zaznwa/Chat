package com.geeks.chat.view

import android.app.AlertDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.telegram.view.adapters.ChatAdapter
import com.geeks.chat.databinding.FragmentChatBinding
import com.geeks.chat.model.models.MessageResponse
import com.geeks.chat.viewmodel.ChatViewModel

class ChatFragment : Fragment() {

    private val viewModel: ChatViewModel by viewModels()
    private lateinit var binding: FragmentChatBinding
    private val adapter = ChatAdapter(
        clickListener = { message -> showUpdateDialog(message) },
        longClickListener = { message -> onLongClick(message) }
    )

    private fun onLongClick(message: MessageResponse) {
        viewModel.deleteMessage(message.chatId!!, message.id!!)
    }

    private fun showUpdateDialog(message: MessageResponse) {
        val editText = EditText(requireContext()).apply {
            setText(message.message)
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Редактировать сообщение")
            .setView(editText)
            .setPositiveButton("OK") { _, _ ->
                viewModel.updateMessage(message.chatId!!, message.id!!, editText.text.toString())
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setListener()
    }

    private fun init() {
        binding.recyclerView.adapter = adapter
        viewModel.getChat(10)

        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            adapter.submitList(messages)
            binding.recyclerView.scrollToPosition(messages.size - 1)
        }
    }

    private fun setListener() {
        binding.apply {
            recyclerView.adapter = adapter
            button.setOnClickListener {
                viewModel.sendMessage(10, editText.text.toString(), 222.toString(), 333.toString())
                editText.text.clear()
            }
        }
    }

}