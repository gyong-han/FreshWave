package com.semi.gam.phoneReservation.controller;

import com.semi.gam.member.vo.MemberVo;
import com.semi.gam.phoneReservation.service.PhoneReservationService;
import com.semi.gam.phoneReservation.vo.PhoneReservationVo;
import com.semi.gam.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("reservation")
public class PhoneReservationController {

    private final PhoneReservationService service;

    // 예약문의 작성하기
    @GetMapping("write")
    public String write(HttpSession session, PhoneReservationVo vo, Model model){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo == null){
            return "redirect:/member/login";
        }
        model.addAttribute("loginMemberVo", loginMemberVo);

        return "reservation/write";
    }

    @PostMapping("write")
    public String write(PhoneReservationVo vo, HttpSession session){

        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());

        int result = service.write(vo, loginMemberVo);

        if(result != 1){
            throw new IllegalStateException("[ERR-PRV-100 고객센터 작성하기 오류 발생]");
        }
        return "redirect:/reservation/list";
    }

    // 예약문의 수정하기
    @GetMapping("edit")
    public String edit(String rno, HttpSession session, Model model){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        if(loginMemberVo.getId() == null){
            return "redirect:/member/login";
        }

        String id = loginMemberVo.getId();

        PhoneReservationVo vo = service.getReservationByNo(rno, id);
        model.addAttribute("vo", vo);

        return "reservation/edit";
    }

    @PostMapping("edit")
    public String edit(PhoneReservationVo vo, HttpSession session, String rno){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());

        int result = service.edit(vo, rno);
        System.out.println("rno = " + rno);
        System.out.println("vo = " + vo);

        if(result != 1){
            throw new IllegalStateException("[ERR-PRV-200 고객센터 수정하기 오류 발생]");
        }

        return "redirect:/reservation/detail?rno="+rno;
    }

    // 예약문의 삭제하기
    @GetMapping("del")
    @ResponseBody
    public int del(String rno, HttpSession session, PhoneReservationVo vo){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getId());

        int result = service.del(rno, vo);

        if(result != 1){
            throw new IllegalStateException("[ERR-PRV-300] 고객센터 삭제하기 오류 발생");
        }

        return result;
    }


    // 예약문의 조회하기
    @GetMapping("list")
    public String list(){
        return "reservation/list";
    }

    // 예약문의 데이터
    @GetMapping("list/data")
    @ResponseBody
    public HashMap getReservationVoList(@RequestParam(name="rno", defaultValue = "1", required = false) int currentPage, String searchType, String searchValue){
        int listCount = service.getReservationCnt(searchType, searchValue);
        int pageLimit = 5;
        int boardLimit = 8;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        List<PhoneReservationVo> reservationVoList = service.getReservationVoList(pvo, searchType, searchValue);

        HashMap map = new HashMap();
        map.put("r", reservationVoList);
        map.put("p", pvo);

        return map;
    }

    // 예약문의 상세조회하기
    @GetMapping("detail")
    public String detail(String rno, Model model, HttpSession session){
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        String id = loginMemberVo.getId();

        PhoneReservationVo vo = service.getReservationByNo(rno, id);
        model.addAttribute("vo", vo);
        return "reservation/detail";
    }

}
