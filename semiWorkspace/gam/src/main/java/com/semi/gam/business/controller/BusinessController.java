package com.semi.gam.business.controller;

import com.semi.gam.business.service.BusinessService;
import com.semi.gam.business.vo.BusinessVo;
import com.semi.gam.business.vo.RankVo;
import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.util.FileUploader;
import com.semi.gam.util.date.ChangeDate;
import com.semi.gam.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String insert(BusinessVo vo, MultipartFile f, HttpSession session) throws IOException {
        String changeName = "";
        String originName = f.getOriginalFilename();
        if(!f.isEmpty()){
            changeName = FileUploader.save(f,path);
        }

        vo.setStartDate(date.changeDate(vo.getStartDate()));
        vo.setEndDate(date.changeDate(vo.getEndDate()));

        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        String writerNo = loginMemberVo.getId();
        vo.setManagerNo(writerNo);

        int result = service.insert(vo,changeName,originName);
        if(result == 1){
            return "redirect:/business/list";
        }
        return "redirect:/error";
    }


    @GetMapping("list")
    public String getBusinessVoList(Model model, @RequestParam(name="pno",required = false,defaultValue = "1") int currentPage,String searchType,String searchValue){
        int listCount = service.getBusinessCnt(searchValue,searchType);
        int pageLimit = 5;
        int boardLimit = 8;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<BusinessVo> businessVoList = service.getBusinessVoList(pvo,searchType,searchValue);

        for (BusinessVo businessVo : businessVoList) {
            String changePhone = businessVo.getPhone().replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
            businessVo.setPhone(changePhone);
        }

        model.addAttribute("businessVoList",businessVoList);
        model.addAttribute("pvo",pvo);
        model.addAttribute("searchValue",searchValue);
        model.addAttribute("searchType",searchType);
        return "business/list";
    }

    @GetMapping("detail")
    public String detail(String no,Model model){
        BusinessVo vo = service.detail(no);
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

    @GetMapping("delete")
    public String delete(String no){
        int result = service.delete(no);
        return "redirect:/business/list";
    }

    @GetMapping("edit")
    public String edit(String no, Model model){
        BusinessVo vo = service.detail(no);

        vo.setStartDate(date.changeDate1(vo.getStartDate()));
        vo.setEndDate(date.changeDate1(vo.getEndDate()));
        vo.setBrn(vo.getBrn().replaceFirst("(\\d{3})(\\d{2})(\\d{5})", "$1-$2-$3"));
        model.addAttribute("vo",vo);
        return "business/edit";
    }

    @PostMapping("edit")
    public String edit(BusinessVo bvo,Model model){
        bvo.setStartDate(date.changeDate(bvo.getStartDate()));
        bvo.setEndDate(date.changeDate(bvo.getEndDate()));
        int result= service.edit(bvo);
        if(result != 1){
            return "redirect:/error";
        }
        detail(bvo.getNo(),model);
        return "business/detail";
    }

}
