package com.semi.gam.schedule.service;

import com.semi.gam.schedule.mapper.ScheduleMapper;
import com.semi.gam.schedule.vo.EventVo;
import com.semi.gam.schedule.vo.PriorityVo;
import com.semi.gam.schedule.vo.ScheduleVo;
import com.semi.gam.util.date.DateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleMapper mapper;

    // 이벤트 불러오는 서비스
    public List<EventVo> getEventVoList(ScheduleVo vo) {

        List<ScheduleVo> schVoList = mapper.getSchVoList(vo);
        List<EventVo> evtVoList = new ArrayList<>();

        for(int i = 0; i < schVoList.size(); i++){
            ScheduleVo schVo = schVoList.get(i);
            schVo.setStart(schVo.getStartDate()+"T"+schVo.getStartTime());
            schVo.setEnd(schVo.getFinishDate()+"T"+schVo.getFinishTime());

            EventVo evtVo = new EventVo();
            evtVo.setId(schVo.getNo());
            evtVo.setStart(schVo.getStart());
            evtVo.setEnd(schVo.getEnd());
            evtVo.setTitle(schVo.getTitle());
            evtVo.setPriority(schVo.getPriority());

            evtVoList.add(evtVo);
        }
        return evtVoList;
    }

    // 일정 작성하기 서비스
    public int write(ScheduleVo vo) {

//        validateSchdule(vo);

            int result = mapper.writePersonal(vo);

            if(result < 1){
                String errCode = "[ERROR-SCH-102] 일정 작성하기 오류 발생";
                log.warn(errCode);
                throw new IllegalStateException(errCode);
            }
            return result;
        }
    // 일정 수정하기 서비스
    public int edit(ScheduleVo vo, String sno) {

        int result = mapper.edit(vo, sno);

        if(result < 1){
            String errCode = "[ERROR-SCH-202] 일정 수정하기 오류 발생";
            log.warn(errCode);
            throw new IllegalStateException(errCode);
        }
        return result;
    }

    // 일정 삭제하기 서비스
    public int del(String sno, ScheduleVo vo) {
        int result = mapper.del(sno, vo);

        if(result < 1){
            String errCode = "[ERROR-SCH-302] 일정 삭제하기 오류 발생";
            log.warn(errCode);
            throw new IllegalStateException(errCode);
        }

        return result;
    }

    // 일정 상세조회 서비스
    public ScheduleVo getEventByNo(String sno, String writerNo) {
        return mapper.getEventByNo(sno, writerNo);
    }

    // 중요도 정보 가져오는 서비스
    public List<PriorityVo> getPriorityVo() {
        return mapper.getPriorityVo();
    }


    // 비즈니스 로직
//    private void validateSchdule(ScheduleVo vo){
//
//        //biz
//        String errCode = null;
//        if(vo.getTitle() != null && vo.getTitle().length() != 0 && vo.getTitle().length() < 1) {
//            errCode = "[ERROR-SCH-001] 제목 내용이 너무 짧습니다.";
//        }
//        if(vo.getBeginDate() != null && vo.getEndDate() != null && vo.getEndDate().isBefore(vo.getBeginDate())){
//            errCode = "[ERROR-SCH-002] 종료날짜가 시작날짜보다 빠릅니다.";
//        }
//        if(vo.getBeginTime() != null && vo.getEndTime() != null && vo.getBeginDate().isEqual(vo.getEndDate()) && vo.getEndTime().isBefore(vo.getBeginTime())) {
//            errCode = "[ERROR-SCH-003] 종료시간이 시작시간보다 빠릅니다.";
//        }
//
//        if(errCode != null){
//            log.warn(errCode);
//            throw new IllegalStateException(errCode);
//        }
//    }

}
