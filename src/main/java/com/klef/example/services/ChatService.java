package com.klef.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.example.entity.ChatMessage;
import com.klef.example.repo.ChatMessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getChat(Long userId, Long counselorId) {
        return chatMessageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
            userId, counselorId, counselorId, userId);
    }

    public List<ChatMessage> getConversations(Long counselorId) {
        return chatMessageRepository.findByReceiverId(counselorId);
    }

    public ChatMessage saveMessage(ChatMessage chatMessage) {
        chatMessage.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }
}
