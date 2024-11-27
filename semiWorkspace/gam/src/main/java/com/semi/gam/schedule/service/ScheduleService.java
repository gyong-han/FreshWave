package com.semi.gam.schedule.service;

import com.semi.gam.schedule.mapper.ScheduleMapper;
import com.semi.gam.schedule.vo.ScheduleVo;
import com.semi.gam.util.date.DateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleMapper sm;
    private final DateTime dt;

    public int write(ScheduleVo vo) {

        System.out.println("vo = " + vo);

        validateSchdule(vo);
        dateTimeChange(vo);

        System.out.println("vo = " + vo);

        int result = 0;
        int shareResult = 0;
        int addResult = 0;
        int attachResult = 1;

//        if(userAdd != null){
//            shareResult = sm.writeShare(vo);
//            addResult = sm.addUser(vo);
//            attachResult = sm.insertSchAttachment(changeNameList);
//        }else{
            result = sm.writePersonal(vo);
//          attachResult = sm.insertSchAttachment(changeNameList);
            if(result < 1){
                String errCode = "[ERROR-SCH-201] 일정 작성하기 오류 발생";
                log.warn(errCode);
                throw new IllegalStateException(errCode);
            }
            return result;
//        }


    }

    private void validateSchdule(ScheduleVo vo){

        //biz
        String errCode = null;
        if(vo.getTitle() != null && vo.getTitle().length() != 0 && vo.getTitle().length() < 1) {
            errCode = "[ERROR-SCH-001] 제목 내용이 너무 짧습니다.";
        }
        if(vo.getBeginDate() != null && vo.getEndDate() != null && vo.getEndDate().isBefore(vo.getBeginDate())){
            errCode = "[ERROR-SCH-002] 종료날짜가 시작날짜보다 빠릅니다.";
        }
        if(vo.getBeginTime() != null && vo.getEndTime() != null && vo.getBeginDate().isEqual(vo.getEndDate()) && vo.getEndTime().isBefore(vo.getBeginTime())) {
            errCode = "[ERROR-SCH-003] 종료시간이 시작시간보다 빠릅니다.";
        }

        if(errCode != null){
            log.warn(errCode);
            throw new IllegalStateException(errCode);
        }
    }

    private void dateTimeChange(ScheduleVo vo){

        // vo에 객체 담아주기
        vo.setStartDate(dt.dateChange(vo.getBeginDate()));
        vo.setFinishDate(dt.dateChange(vo.getEndDate()));
        vo.setStartTime(dt.timeChange(vo.getBeginTime()));
        vo.setFinishTime(dt.timeChange(vo.getEndTime()));
    }
}
