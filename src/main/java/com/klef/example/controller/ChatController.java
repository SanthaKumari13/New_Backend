package com.klef.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.example.entity.ChatMessage;
import com.klef.example.services.ChatService;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{userId}/{counselorId}")
    public List<ChatMessage> getChat(@PathVariable Long userId, @PathVariable Long counselorId) {
        return chatService.getChat(userId, counselorId);
    }

    @GetMapping("/conversations/{counselorId}")
    public List<ChatMessage> getConversations(@PathVariable Long counselorId) {
        return chatService.getConversations(counselorId);
    }

    @PostMapping
    public ChatMessage sendMessage(@RequestBody ChatMessage chatMessage) {
        return chatService.saveMessage(chatMessage);
    }
}
