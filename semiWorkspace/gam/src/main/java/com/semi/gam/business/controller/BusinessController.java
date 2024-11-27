package com.semi.gam.business.controller;

import com.semi.gam.business.service.BusinessService;
import com.semi.gam.business.vo.BusinessVo;
import com.semi.gam.business.vo.RankVo;
import com.semi.gam.util.FileUploader;
import com.semi.gam.util.date.ChangeDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
        String originName = f.getOriginalFilename();
        if(!f.isEmpty()){
            changeName = FileUploader.save(f,path);
        }

        vo.setStartDate(date.changeDate(vo.getStartDate()));
        vo.setEndDate(date.changeDate(vo.getEndDate()));

        int result = service.insert(vo,changeName,originName);
        if(result == 1){
            return "redirect:/home";
        }
        return "redirect:/error";
    }

    @GetMapping("list")
    public String list(Model model){
        return "business/list";
    }

    @GetMapping("list/data")
    @ResponseBody
    public List<BusinessVo> getBusinessVoList(){
        List<BusinessVo> businessVoList = service.getBusinessVoList();
        for (BusinessVo businessVo : businessVoList) {
            String changePhone = businessVo.getPhone().replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
            businessVo.setPhone(changePhone);
        }
        return businessVoList;
    }

    @GetMapping("detail")
    public String detail(String bno,Model model){
        BusinessVo vo = service.detail(bno);
        String changePhone = vo.getPhone().replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
        String changeBrn = vo.getBrn().replaceFirst("(\\d{3})(\\d{2})(\\d{5})", "$1-$2-$3");
        vo.setPhone(changePhone);
        vo.setBrn(changeBrn);
        model.addAttribute("vo",vo);
        return "business/detail";
    }

    @GetMapping("data")
    @ResponseBody
    public List<RankVo> getData(){
        List<RankVo> rankDataList = service.getDataList();
        return rankDataList;
    }
}
