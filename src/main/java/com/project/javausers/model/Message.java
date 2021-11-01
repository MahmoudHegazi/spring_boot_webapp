package com.project.javausers.model;

import javax.persistence.*;


import javax.persistence.*;

@Table(name = "MESSAGES", uniqueConstraints = {
        @UniqueConstraint(name = "uc_message_message_id", columnNames = {"message_id"})
})
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Integer messageId;
    private String messageText;
    private String username;


    public Message(Integer messageId,String messageText, String username) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.username = username;
    }

    public Integer getMessageId(){ return messageId; }

    public void setMessageId(Integer messageId){this.messageId = messageId; }


    public String getMessageText(){ return messageText; }

    public void setMessageText(String messageText){ this.messageText = messageText;}


    public String getUsername(){ return username; }

    public void setUsername(String username){ this.username = username; }
}
