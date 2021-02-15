package com.eureka.chat.entity;

import com.eureka.chat.dto.MessageType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "chatmessage")
public class ChatMessageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private String id;
    private String message;
    private Date time;
    private String writer;
    @ManyToOne
    @JoinColumn(name = "roomId")
    private ChatRoomEntity chatRoomEntity;

    @Transient
    private MessageType type;
}