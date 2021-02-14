package com.eureka.chat.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "chatroomjoin")
public class ChatRoomJoinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "join_id")
    private String joinId;
    @Column(name="user_id")
    private String userId ;
    @Column(name="room_id")
    private String roomId ;
    @Column(name="seller_id")
    private String sellerId;

    @ManyToOne
    @JoinColumn(name = "id")
    private ChatRoomEntity chatRoomEntity;
}
