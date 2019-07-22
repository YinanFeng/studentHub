package com.studentHub.matchCentre.controller;

import com.alibaba.fastjson.JSONObject;
import com.studentHub.matchCentre.common.Data;
import com.studentHub.matchCentre.common.JsonRes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MentorCentreController {

    @PostMapping(value = "/mentor/mentorNewMessage")
    public JSONObject mentorNewMessage(
            @RequestParam String mentorId,
            @RequestParam String message

    ) {
        String stuId = Data.findStudent(mentorId);
        if(stuId == null){
            return JsonRes.resError();
        }

        System.out.println("mentorNewMessage" + stuId + mentorId);

        //send message to stu by stuId
        return JsonRes.resSuccess();
    }

    @PostMapping(value = "/mentor/mentorJoinCov")
    public JSONObject mentorJoinCov(
            @RequestParam String mentorId,
            @RequestParam String type
    ) {
        System.out.println(mentorId);
        Data.addNewAvailbleMentor(mentorId,type);

        System.out.println("mentorJoin" + mentorId + type);

        //send this message to stu

        return JsonRes.resSuccess();
    }

    @PostMapping(value = "/mentor/mentorLeaveCov")
    public JSONObject mentorLeaveCov(
            @RequestParam String mentorId
    ) {
        System.out.println(mentorId);

        String stuId = Data.mentorLeave(mentorId);
        if(stuId == null){
            return JsonRes.resError();
        }

        System.out.println("mentorLeave" + mentorId + stuId);

        //send this message to stu

        return JsonRes.resSuccess();
    }


}
