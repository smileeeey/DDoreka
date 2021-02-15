package com.eureka.chat.controller;


import com.eureka.chat.dto.Message;
import com.eureka.chat.service.IMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {
    private final IMessageService messageService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/message")
    public void sendMessage(@Payload Message chatMessage) {
        log.info("전달 메세지 : " + chatMessage);

        messageService.insertMessage(chatMessage);
        template.convertAndSend("/sub/" + chatMessage.getChatroomId(), chatMessage);
    }
}
