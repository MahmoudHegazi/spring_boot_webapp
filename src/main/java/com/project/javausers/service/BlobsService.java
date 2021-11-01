package com.project.javausers.service;

import com.project.javausers.mapper.BlobsMapper;
import com.project.javausers.model.Blobs;
import org.springframework.stereotype.Service;

import java.sql.Blob;

@Service
public class BlobsService {
    private BlobsMapper blobsMapper;

    public BlobsService(BlobsMapper blobsMapper){
        this.blobsMapper = blobsMapper;
    }

    public void addBlobs(Blob blobContent, String blobName, String username){
        blobsMapper.insert(blobContent, blobName, username);
    }

    public Blob getFirstBlob(){
       return blobsMapper.getFirstBlob();
    }

}
