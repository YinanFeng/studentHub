package com.studentHub.matchCentre.controller;


import com.alibaba.fastjson.JSONObject;
import com.studentHub.matchCentre.common.Data;
import com.studentHub.matchCentre.common.JsonRes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCentreController {

    @PostMapping(value = "/stu/studentNewCov")
    public JSONObject studentNewCov(
            @RequestParam String stuNum,
            @RequestParam String mentorType
    ) {
        System.out.println(stuNum + mentorType);

        if(Data.matchAvailableMentor(stuNum,mentorType)==null){
            return JsonRes.resError();
        }
        return JsonRes.resSuccess();
    }

    @PostMapping(value = "/stu/studentNewMessage")
    public JSONObject studentNewMessage(
            @RequestParam String stuNum,
            @RequestParam String message

    ) {

        String mentorId = Data.findMentor(stuNum);
        if(mentorId == null){
            return JsonRes.resError();
        }
        //send message to mentor
        return JsonRes.resSuccess();
    }

    @PostMapping(value = "/stu/studentLeaveCov")
    public JSONObject studentLeaveCov(
            @RequestParam String stuNum
    ) {
        System.out.println(stuNum);
        String mentorId =  Data.studentLeave(stuNum);
        if(mentorId == null){
            return JsonRes.resError();
        }

        //send this message to mentor by mentorId

        return JsonRes.resSuccess();
    }

}
