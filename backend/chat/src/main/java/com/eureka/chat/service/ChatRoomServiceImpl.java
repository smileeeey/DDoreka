package com.eureka.chat.service;

import com.eureka.chat.dto.ChatRoom;
import com.eureka.chat.entity.ChatRoomEntity;
import com.eureka.chat.entity.ChatRoomJoinEntity;
import com.eureka.chat.repository.ChatMessageRepository;
import com.eureka.chat.repository.ChatRoomJoinRepository;
import com.eureka.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl {
//    private Map<String, ChatRoom> chatRoomMap;

    private final ChatRoomJoinRepository chatRoomJoinRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public List<ChatRoom> findAllRoom(){
        List<ChatRoomEntity> chatRoomEntities =chatRoomRepository.findAll();
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id){
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String name){
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }