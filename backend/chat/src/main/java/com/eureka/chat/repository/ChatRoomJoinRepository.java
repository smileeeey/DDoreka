package com.eureka.chat.repository;

import com.eureka.chat.entity.ChatRoomJoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoinEntity, String> {
    List<ChatRoomJoinEntity> findAllByUserId(String userId);
}
