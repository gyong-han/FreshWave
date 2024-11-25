package com.semi.gam.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUploader {
    public static String save(MultipartFile f, String path) throws IOException {
        if (!f.isEmpty()) {
            //파일 이름 변경
            String randomStr = UUID.randomUUID().toString();
            String originalFilename = f.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            String changeName = randomStr + ext;
            File target = new File(path + changeName);
            //파일 서버 저장
            f.transferTo(target);
            return changeName;
        }
        return null;
    }
}
