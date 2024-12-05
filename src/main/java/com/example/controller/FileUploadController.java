package com.example.controller;

import com.example.pojo.Result;
import com.example.utils.AliossUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@CrossOrigin
@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        String orginalFilename=file.getOriginalFilename();
        //防止文件覆盖 名字用UUID
        String filename = UUID.randomUUID().toString() + orginalFilename.substring(orginalFilename.lastIndexOf("."));
//        file.transferTo(new File("D:/upload/"+filename));
        String url = AliossUtil.uploadFile(filename,file.getInputStream() );
        return Result.success(url);
    }
}
