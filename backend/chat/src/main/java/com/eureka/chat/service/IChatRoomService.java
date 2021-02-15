package com.eureka.chat.service;

import com.eureka.chat.dto.ChatRoom;

import java.util.List;

public interface IChatRoomService {
    String getRoomTitle(long id);

    List<ChatRoom> getAllChatRooms();

    long createRoom(ChatRoom newRoom);
}
