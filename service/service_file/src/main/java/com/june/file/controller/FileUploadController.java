package com.june.file.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Deprecated
@Api(description = "文件管理")
@RestController
@RequestMapping("papfile/file")
public class FileUploadController {

    @PostMapping("/esrgan/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest req) {
        String file = "D:\\Codings\\paper_esrgan\\LR";
        File folder = new File(file);
        if(!folder.isDirectory()) {
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID() +
                oldName.substring(oldName.lastIndexOf("."));
        try {
            // 文件保存操作
            uploadFile.transferTo(new File(folder, newName));
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败!";
    }
}

