package com.eureka.chat.controller;

import com.eureka.chat.dto.ChatRoom;
import com.eureka.chat.dto.Message;
import com.eureka.chat.service.IChatRoomService;
import com.eureka.chat.service.IMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@RestController
public class ChatRoomController {

    private final IChatRoomService chatroomService;
    private final IMessageService messageService;
    final int PAGE = 10;

    // 특정 채팅방 타이틀 가져오기
    @GetMapping("/room/{id}")
    public ResponseEntity<String> roomTitle(@PathVariable long id) {
        String roomTitle = chatroomService.getRoomTitle(id);
        if (roomTitle == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(roomTitle);
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoom>> room() {
        List<ChatRoom> rooms = chatroomService.getAllChatRooms();
        if (rooms == null || rooms.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(rooms);

    }

    // 방 생성
    @PostMapping("/room")
    public ResponseEntity<Long> createRoom(@RequestBody ChatRoom newRoom) {
        long resultOfCreation = chatroomService.createRoom(newRoom);
        if (resultOfCreation >= 0)
            return ResponseEntity.status(HttpStatus.OK).body(resultOfCreation);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Long.MIN_VALUE);
    }

    // 특정 채팅방 의 메세지 최근 10개
    @GetMapping("/room/message/{id}")
    public ResponseEntity<List<Message>> roomInfo(@PathVariable long id,
                                                  @RequestParam(value = "page", defaultValue = "0") String page) {
        long idx = page.equals("0") ? 0 : Integer.parseInt(page) * PAGE + 1;
        List<Message> msgList = messageService.getMessagesByChatroomId(id, idx);
        return ResponseEntity.status(HttpStatus.OK).body(msgList);
    }

}
