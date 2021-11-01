package com.project.javausers.model;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "blobs", indexes = {
        @Index(name = "idx_blobs_image_content", columnList = "image_content")
})
public class Blobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String file_name;
    private String username;
    @Lob
    private Blob image_content;

    // @ManyToOne private Menu menu;

    //constructor
    public Blobs(Integer id, String filename,String username, Blob image_content) {
        this.id = id;
        this.file_name = filename;
        this.image_content = image_content;
        this.username = username;
    }

    //Getters and Setters - Accessors.

    public int getId() {
        return id;
    }
    public void setId() {
        this.id =  id;
    }

    public String getFilename() {
        return file_name;
    }

    public void setFilename(String filename) {
        this.file_name = filename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Blob getImageContent() {
        return image_content;
    }

    public void setImageContent(Blob image_content) {
        this.image_content = image_content;
    }



}