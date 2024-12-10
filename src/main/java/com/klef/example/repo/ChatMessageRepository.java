package com.klef.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.example.entity.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage > findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
        Long senderId, Long receiverId, Long receiverIdAlt, Long senderIdAlt);
    List<ChatMessage> findByReceiverId(Long receiverId);
}