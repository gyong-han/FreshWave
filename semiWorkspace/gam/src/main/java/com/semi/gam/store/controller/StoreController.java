package com.semi.gam.store.controller;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.store.service.StoreService;
import com.semi.gam.store.vo.StatusVo;
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
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

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
    public String storeHome(HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo != null){
            return "store/home";
        }
        return "redirect:/member/login";
    }

    //작성 화면
    @GetMapping("insert")
    public String insert(HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo != null){
            return "store/insert";
        }
        return "redirect:/member/login";
    }

    //작성 처리
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

        MemberVo loginMemberVo = (MemberVo)session.getAttribute("loginMemberVo");
        String writerNo = loginMemberVo.getId();
        vo.setManagerNo(writerNo);

        int result = service.insert(vo,changeName,originName);
        if(result > 0){
            return "redirect:/store/list";
        }
        return "redirect:/error";
    }

    //목록조회
    @GetMapping("list")
    public String getStoreVoList(HttpSession session,Model model,@RequestParam(name="pno",required = false,defaultValue = "1") int currentPage,String searchType,String searchValue){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        int listCount = service.getStoreCnt(searchType,searchValue);
        int pageLimit = 5;
        int boardLimit = 8;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);
        List<StoreVo> storeVoList = service.getStoreVoList(pvo,searchType,searchValue);
        for (StoreVo storeVo : storeVoList) {
            if(storeVo.getPhone() != null){
                if(storeVo.getPhone().length() == 9){
                    String changePhone = storeVo.getPhone().replaceFirst("(\\d{2})(\\d{3})(\\d{4})", "$1-$2-$3");
                    storeVo.setPhone(changePhone);
                }else if(storeVo.getPhone().length() == 10){
                    String changePhone = storeVo.getPhone().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1-$2-$3");
                    storeVo.setPhone(changePhone);
                }else{
                    String changePhone = storeVo.getPhone().replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
                    storeVo.setPhone(changePhone);
                }

            }

        }
        model.addAttribute("storeVoList",storeVoList);
        model.addAttribute("pvo",pvo);
        model.addAttribute("searchValue",searchValue);
        model.addAttribute("searchType",searchType);
        return "store/list";
    }

    //상세조회
    @GetMapping("detail")
    public String detail(String no, Model model,HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        StoreVo vo = service.detail(no);
        String changePhone = "";
        if(vo.getPhone() != null){
            if(vo.getPhone().length() == 9){
                 changePhone = vo.getPhone().replaceFirst("(\\d{2})(\\d{3})(\\d{4})", "$1-$2-$3");
            }else if(vo.getPhone().length() == 10){
                 changePhone = vo.getPhone().replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1-$2-$3");
            }else{
                 changePhone = vo.getPhone().replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
            }
        }
        String changeBrn = vo.getBrn().replaceFirst("(\\d{3})(\\d{2})(\\d{5})", "$1-$2-$3");
        String changeCeoPhone = vo.getCeoPhone().replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
        vo.setPhone(changePhone);
        vo.setBrn(changeBrn);
        vo.setCeoPhone(changeCeoPhone);
        vo.setEnrollDate(vo.getEnrollDate().substring(0,10));

        model.addAttribute("vo",vo);
        return "store/detail";
    }

    //삭제
    @GetMapping("delete")
    @ResponseBody
    public int delete(String bno){
        int result = service.delete(bno);
        return result;
    }

    //수정화면
    @GetMapping("edit")
    public String edit(String no, Model model, HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        StoreVo vo = service.detail(no);

        vo.setEduDate(date.changeDate1(vo.getEndDate()));
        vo.setOpenDate(date.changeDate1(vo.getOpenDate()));
        vo.setStartDate(date.changeDate1(vo.getStartDate()));
        vo.setEndDate(date.changeDate1(vo.getEndDate()));

        vo.setBrn(vo.getBrn().replaceFirst("(\\d{3})(\\d{2})(\\d{5})", "$1-$2-$3"));
        vo.setEnrollDate(vo.getEnrollDate().substring(0,10));
        model.addAttribute("vo",vo);
        return "store/edit";
    }

    //수정 처리
    @PostMapping("edit")
    public String edit(StoreVo svo, Model model,HttpSession session,MultipartFile f) throws IOException {
        svo.setEduDate(date.changeDate(svo.getEndDate()));
        svo.setOpenDate(date.changeDate(svo.getOpenDate()));
        svo.setStartDate(date.changeDate(svo.getStartDate()));
        svo.setEndDate(date.changeDate(svo.getEndDate()));
        if(!svo.getCloseDate().equals("")){
            svo.setCloseDate(date.changeDate(svo.getCloseDate()));
        }
        else{
            svo.setCloseDate("-");
        }

        String changeName = "";
        String originName = "";
        if(f != null){
            originName = f.getOriginalFilename();
            changeName = FileUploader.save(f,path);
        }

        int result= service.edit(svo,changeName,originName);

        session.setAttribute("alertNo",result);
        detail(svo.getNo(),model,session);
        return "store/detail";
    }
    
    //원형 차트 데이터 가져오기
    @GetMapping("data")
    @ResponseBody
    public List<StatusVo> storeData(){
        List<StatusVo> statusList = service.storeData();
        return statusList;
    }
}