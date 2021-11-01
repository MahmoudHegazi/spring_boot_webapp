package com.project.javausers.mapper;

import com.project.javausers.model.Blobs;
import com.project.javausers.model.Message;
import org.apache.ibatis.annotations.*;

import java.sql.Blob;
import java.util.List;

@Mapper
public interface BlobsMapper {
    @Select("SELECT * FROM blobs WHERE id = 1")
    Blob getFirstBlob();

    @Insert("INSERT INTO blobs (file_name, image_content, username) VALUES(#{file_name}, #{image_content}, #{username})")
    void insert(Blob image_content, String file_name, String username);// if you follow this way one form and one class and submit class you no need to use message.

    @Select("SELECT * FROM Blobs")
    @MapKey("id")
    List<Blobs> getBlobs();
}
