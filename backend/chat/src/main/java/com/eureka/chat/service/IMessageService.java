package com.eureka.chat.service;

import com.eureka.chat.dto.Message;

import java.util.List;

public interface IMessageService {
    int insertMessage(Message chatMessage);
    List<Message> getMessagesByChatroomId(long id, long idx);
}
