package com.project.javausers.controller;

import com.project.javausers.mapper.BlobsMapper;
import com.project.javausers.model.Blobs;
import com.project.javausers.service.BlobsService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Blob;


@Controller
public class BlobController {

    private BlobsService blobService;
    private BlobsMapper blobsMapper;

    public BlobController(BlobsService blobService, BlobsMapper blobsMapper) {
        this.blobService = blobService;
        this.blobsMapper = blobsMapper;

    }

    @GetMapping("/data")
    public Blob sendBlob(Authentication authentication, Model model){
        return blobService.getFirstBlob();
    }


    @GetMapping("/blob")
    public String getBlob(Authentication authentication, Model model){
        // how to get session Id
        model.addAttribute("logged_user", authentication.getName());


        return "blob";
    }
/*
    @PostMapping("/blob")
    public String handleFileUpload(Authentication authentication, @RequestParam("fileUpload") MultipartFile fileUpload, Model model, Session session) throws IOException {


        InputStream fis = fileUpload.getInputStream();
        File staticFolder = new File("src/main/resources/static/images");


        //Blob blobUploaded = Hibernate.getLobCreator(session).createBlob(fis, fileUpload.getSize());

        // save the blob byte[] file into database using service
        //blobService.addBlobs(blobUploaded,fileUpload.getOriginalFilename(), authentication.getName());

        //blobService.addBlobs((Blob) fis,fileUpload.getOriginalFilename());
        System.out.println("Save Blob Data for First Time thankyou.");
        if (fileUpload.getSize() > 0 && !fileUpload.isEmpty()) {

            String theFileName = "/images/" + fileUpload.getOriginalFilename();
            File convertFile = new File("src/main/resources/static/images/" + fileUpload.getOriginalFilename());
            if (convertFile.exists()){
                convertFile = new File("src/main/resources/static/images/" + fileUpload.getOriginalFilename());
                theFileName = "/images/" + fileUpload.getOriginalFilename();
            };

            model.addAttribute("username",authentication.getName());
            //ImageServices.addImagePath(theFileName);
            OutputStream result = new FileOutputStream(convertFile);

            //javaFile.
            result.write(fileUpload.getBytes());
            result.close();

            //model.addAttribute("fileName", fileUpload.getOriginalFilename());
            //model.addAttribute("size", fileUpload.getSize());
        }
        return "blob";

    }*/
}
