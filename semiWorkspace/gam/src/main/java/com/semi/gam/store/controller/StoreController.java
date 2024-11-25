package com.semi.gam.store.controller;

import com.semi.gam.store.service.StoreService;
import com.semi.gam.store.vo.StoreVo;
import com.semi.gam.util.FileUploader;
import com.semi.gam.util.date.ChangeDate;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("store")
@Slf4j
public class StoreController {
    private final StoreService service;
    private final ChangeDate date;
    @Value("#{pathInfo.getStorePath()}")
    private String path;

    @GetMapping("home")
    public String storeHome(){
        return "store/home";
    }

    @GetMapping("insert")
    public String insert(){
        return "store/insert";
    }

    @PostMapping("insert")
    public String insert(StoreVo vo, HttpSession session, @RequestParam(name = "f") List<MultipartFile> fileList) throws IOException {
        List<String> changeNameList = new ArrayList<String>();
        for (MultipartFile f : fileList) {
            if(f.isEmpty()){break;}
            String changeName = FileUploader.save(f,path);
            changeNameList.add(changeName);
        }
        vo.setEduDate(date.changeDate(vo.getEndDate()));
        vo.setOpenDate(date.changeDate(vo.getOpenDate()));
        vo.setStartDate(date.changeDate(vo.getStartDate()));
        vo.setEndDate(date.changeDate(vo.getEndDate()));

        int result = service.insert(vo,changeNameList);
        if(result > 2){
            return "redirect:/home";
        }
        return "redirect:/error";
    }
}