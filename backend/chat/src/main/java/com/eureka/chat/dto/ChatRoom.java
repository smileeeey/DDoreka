package com.eureka.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class ChatRoom {
    private long id;
    private String title;

    @Builder
    public ChatRoom(long id, String title, long masterId) {
        super();
        this.id = id;
        this.title = title;
    }

}