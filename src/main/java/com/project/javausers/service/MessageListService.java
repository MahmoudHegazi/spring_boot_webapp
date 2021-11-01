package com.project.javausers.service;


import com.project.javausers.mapper.MessageMapper;
import com.project.javausers.model.Message;
import com.project.javausers.model.MessageForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageListService {

    private MessageMapper messageMapper;

    public MessageListService(MessageMapper messageMapper){
        this.messageMapper = messageMapper;
    }


    public void addMessage(MessageForm messageForm) {

        String messageText = messageForm.getText();
        switch (messageForm.getType()){
            case "Whisper":
                messageText = messageText.toLowerCase();
                break;
            case "Shout":
                messageText = messageText.toUpperCase();
                break;
            case "Normal":
                messageText = messageText;
                break;
        }
        Message chatMessage = new Message(null, messageText, messageForm.getUsername());

        messageMapper.insert(chatMessage);
    }

    public Message getMessage(String username){
        return messageMapper.getMessage(username);
    }

    public List<Message> getMessages() {
        return messageMapper.getMessages();
    }
}
