package com.eureka.chat.repository;

import com.eureka.chat.dto.ChatRoom;
import com.eureka.chat.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, String> {

}
