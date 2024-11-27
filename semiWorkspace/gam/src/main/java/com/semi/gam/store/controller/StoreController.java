package com.semi.gam.store.controller;

import com.semi.gam.store.service.StoreService;
import com.semi.gam.store.vo.StoreVo;
import com.semi.gam.util.FileUploader;
import com.semi.gam.util.date.ChangeDate;
import com.semi.gam.util.page.PageVo;
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
    public String getStoreVoList(Model model,@RequestParam(name="pno",required = false,defaultValue = "1") int currentPage,String searchType,String searchValue){
        int listCount = service.getStoreCnt(searchType,searchValue);
        int pageLimit = 5;
        int boardLimit = 8;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
        List<StoreVo> storeVoList = service.getStoreVoList(pvo,searchType,searchValue);
        for (StoreVo storeVo : storeVoList) {
            String changePhone = storeVo.getPhone().replaceFirst("(\\d{2})(\\d{3})(\\d{4})", "$1-$2-$3");
            storeVo.setPhone(changePhone);
        }
        model.addAttribute("storeVoList",storeVoList);
        model.addAttribute("pvo",pvo);
        model.addAttribute("searchValue",searchValue);
        model.addAttribute("searchType",searchType);
        return "store/list";
    }

    @GetMapping("detail")
    public String detail(String no, Model model){
        StoreVo vo = service.detail(no);

        String changePhone = vo.getPhone().replaceFirst("(\\d{2})(\\d{3})(\\d{6})", "$1-$2-$3");
        String changeBrn = vo.getBrn().replaceFirst("(\\d{3})(\\d{2})(\\d{5})", "$1-$2-$3");
        String changeCeoPhone = vo.getCeoPhone().replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
        vo.setPhone(changePhone);
        vo.setBrn(changeBrn);
        vo.setCeoPhone(changeCeoPhone);

        model.addAttribute("vo",vo);
        return "store/detail";
    }

}