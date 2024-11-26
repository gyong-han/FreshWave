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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public String insert(StoreVo vo, HttpSession session, MultipartFile f) throws IOException {
        String changeName = "";
        String originName = f.getOriginalFilename();
            if(!f.isEmpty()){
                changeName = FileUploader.save(f,path);
            }

        vo.setEduDate(date.changeDate(vo.getEndDate()));
        vo.setOpenDate(date.changeDate(vo.getOpenDate()));
        vo.setStartDate(date.changeDate(vo.getStartDate()));
        vo.setEndDate(date.changeDate(vo.getEndDate()));

        int result = service.insert(vo,changeName,originName);
        if(result > 0){
            return "redirect:/home";
        }
        return "redirect:/error";
    }

    @GetMapping("list")
    public String list(){
        return "store/list";
    }

    @GetMapping("list/data")
    @ResponseBody
    public List<StoreVo> getStoreVoList(){
        List<StoreVo> storeVoList = service.getStoreVoList();
        for (StoreVo storeVo : storeVoList) {
            String changePhone = storeVo.getPhone().replaceFirst("(\\d{2})(\\d{3})(\\d{4})", "$1-$2-$3");
            storeVo.setPhone(changePhone);
        }
        return storeVoList;
    }

    @GetMapping("detail")
    public String detail(String bno, Model model){
        StoreVo vo = service.detail(bno);
        model.addAttribute("vo",vo);
        return "store/detail";
    }
}