package com.semi.gam.business.controller;

import com.semi.gam.business.service.BusinessService;
import com.semi.gam.business.vo.BusinessVo;
import com.semi.gam.util.FileUploader;
import com.semi.gam.util.date.ChangeDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService service;
    private final ChangeDate date;
    @Value("#{pathInfo.getStorePath()}")
    private String path;

    @GetMapping("insert")
    public String insert(){
        return "business/insert";
    }

    @PostMapping("insert")
    public String insert(BusinessVo vo, MultipartFile f) throws IOException {
        String changeName = "";
        if(!f.isEmpty()){
            changeName = FileUploader.save(f,path);
        }

        vo.setStartDate(date.changeDate(vo.getStartDate()));
        vo.setEndDate(date.changeDate(vo.getEndDate()));

        int result = service.insert(vo,changeName);
        if(result == 1){
            return "redirect:/home";
        }
        return "redirect:/error";
    }
}
