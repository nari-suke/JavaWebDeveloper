package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.service.MessageService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getChatPage(ChatForm chatForm, Model model) {
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        return "chat";
    }

    @PostMapping
    public String postChatMessage(Authentication authentication, ChatForm chatForm, Model model) {
        chatForm.setUsername(authentication.getName());
        this.messageService.addMessage(chatForm);
        chatForm.setMessageText("");
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public String[] allMessageTypes () {
        return new String[] { "Say", "Shout", "Whisper" };
    }

}
