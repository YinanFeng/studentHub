package com.studentHub.matchCentre.controller;


import com.alibaba.fastjson.JSONObject;
import com.studentHub.matchCentre.common.Data;
import com.studentHub.matchCentre.common.JsonRes;
import com.studentHub.matchCentre.remoteController.MentorChatController;
import com.studentHub.matchCentre.remoteController.StudentChatController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCentreController {

    @Autowired
    MentorChatController mentorChatController;


    @PostMapping(value = "/stu/studentNewCov")
    public JSONObject studentNewCov(
            @RequestParam String stuNum,
            @RequestParam String mentorType
    ) {
        System.out.println("success cov");

        if(Data.matchAvailableMentor(stuNum,mentorType)==null){
            return JsonRes.resError();
        }

        System.out.println("newCov"+stuNum + mentorType);

        return JsonRes.resSuccess();
    }

    @PostMapping(value = "/stu/studentNewMessage")
    public JSONObject studentNewMessage(
            @RequestParam String stuNum,
            @RequestParam String message

    ) {

        System.out.println("success newMessage");

        String mentorId = Data.findMentor(stuNum);
        if(mentorId == null){
            return JsonRes.resError();
        }

        //what if mentor already leave???
        mentorChatController.receiveMessageFromStudent(message,mentorId);

        System.out.println("STUnewMessage" + stuNum + mentorId);
        //send message to mentor
        return JsonRes.resSuccess();
    }

    @PostMapping(value = "/stu/studentLeaveCov")
    public JSONObject studentLeaveCov(
            @RequestParam String stuNum
    ) {

        System.out.println("success leave");

        System.out.println(stuNum);
        String mentorId =  Data.studentLeave(stuNum);
        if(mentorId == null){
            return JsonRes.resError();
        }

        System.out.println("stuLeave" + stuNum + mentorId);

        mentorChatController.studentLeave(mentorId);
        //send this message to mentor by mentorId

        return JsonRes.resSuccess();
    }

}
