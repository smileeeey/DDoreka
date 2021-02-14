package com.eureka.chat.controller;


import com.eureka.chat.dto.ChatRoom;
import com.eureka.chat.entity.ChatRoomEntity;
import com.eureka.chat.dto.ChatRoomForm;
import com.eureka.chat.repository.ChatRoomRepository;
import com.eureka.chat.service.ChatRoomServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatRoomServiceImpl chatRoomService;

    @GetMapping("/")
    public String rooms(Model model){
        model.addAttribute("rooms",chatRoomService.findAllRoom());
        return "rooms";
    }

    @GetMapping("/rooms/{id}")
    public String room(@PathVariable String id, Model model){
        ChatRoom room = chatRoomService.findRoomById(id);
        model.addAttribute("room",room);
        return "room";
    }

    @GetMapping("/new")
    public String make(Model model){
        ChatRoomForm form = new ChatRoomForm();
        model.addAttribute("form",form);
        return "newRoom";
    }

    @PostMapping("/room/new")
    public String makeRoom(ChatRoomForm form){
        chatRoomService.createChatRoom(form.getName());

        return "redirect:/";
    }

}