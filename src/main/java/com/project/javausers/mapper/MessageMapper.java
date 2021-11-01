package com.project.javausers.mapper;
import com.project.javausers.model.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM MESSAGES WHERE username = #{username}")
    Message getMessage(String username);

    @Insert("INSERT INTO MESSAGES (message_text, username) VALUES(#{messageText}, #{username})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insert(Message rightMessage);// if you follow this way one form and one class and submit class you no need to use message.

    @Select("SELECT * FROM MESSAGES")
    @MapKey("messageId")
    List<Message> getMessages();
}
