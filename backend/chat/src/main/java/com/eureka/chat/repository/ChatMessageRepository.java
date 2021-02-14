package com.eureka.chat.repository;

import com.eureka.chat.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, String> {

}
