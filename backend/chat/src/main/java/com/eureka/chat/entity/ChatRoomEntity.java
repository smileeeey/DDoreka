package com.eureka.chat.entity;

import com.eureka.chat.dto.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name = "chatroom")
public class ChatRoomEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private String roomId;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessageEntity> messages;

    @Transient
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoomEntity create(){
        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
//        chatRoom.setRoomId(UUID.randomUUID().toString());
        return chatRoomEntity;
    }

    public void handleMessage(WebSocketSession session, ChatMessageEntity chatMessageEntity,
                              ObjectMapper objectMapper) throws IOException {
        if(chatMessageEntity.getType() == MessageType.ENTER){
            sessions.add(session);
            chatMessageEntity.setMessage(chatMessageEntity.getWriter() + "님이 입장하셨습니다.");
        }
        else if(chatMessageEntity.getType() == MessageType.LEAVE){
            sessions.remove(session);
            chatMessageEntity.setMessage(chatMessageEntity.getWriter() + "님임 퇴장하셨습니다.");
        }
        else{
            chatMessageEntity.setMessage(chatMessageEntity.getWriter() + " : " + chatMessageEntity.getMessage());
        }
        send(chatMessageEntity,objectMapper);
    }

    private void send(ChatMessageEntity chatMessageEntity, ObjectMapper objectMapper) throws IOException {
        TextMessage textMessage = new TextMessage(objectMapper.
                writeValueAsString(chatMessageEntity.getMessage()));
        for(WebSocketSession sess : sessions){
            sess.sendMessage(textMessage);
        }
    }
}