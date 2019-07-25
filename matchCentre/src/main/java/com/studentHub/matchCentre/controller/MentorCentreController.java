package com.studentHub.matchCentre.controller;

import com.alibaba.fastjson.JSONObject;
import com.studentHub.matchCentre.common.Data;
import com.studentHub.matchCentre.common.JsonRes;
import com.studentHub.matchCentre.remoteController.MentorChatController;
import com.studentHub.matchCentre.remoteController.StudentChatController;
import com.studentHub.matchCentre.webSocket.TextWebSocketFrameHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.netty.channel.Channel;

@RestController
public class MentorCentreController {


    @Autowired
    StudentChatController studentChatController;


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
        studentChatController.receiveMessageFromMentor(message,stuId);

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
        //需写接口推送给前端，哪些type的mentor有人

        System.out.println("hereeeeeeycghdsj");

        for(Channel cn : TextWebSocketFrameHandler.channels){
            System.out.println(cn);
            cn.writeAndFlush(new TextWebSocketFrame(Data.currentMentor() + "\n"));
        }

        return JsonRes.resSuccess();
    }

    @PostMapping(value = "/mentor/mentorLeaveCov")
    public JSONObject mentorLeaveCov(
            @RequestParam String mentorId
    ) {
        System.out.println(mentorId);

        String stuId = Data.mentorLeave(mentorId);
        if(stuId == null){
            //the mentor id unmatched

            //what if (stu)someone already leave, mentor still here?

            //refresh for curr mentor number
            for(Channel cn : TextWebSocketFrameHandler.channels){
                cn.writeAndFlush(new TextWebSocketFrame(Data.currentMentor() + "\n"));
            }
            //also success?!
            return JsonRes.resError();
        }



        System.out.println("mentorLeave" + mentorId + stuId);

        //send this message to stu
        studentChatController.mentorLeave(stuId);

        //refresh for curr mentor number
        for(Channel cn : TextWebSocketFrameHandler.channels){
            cn.writeAndFlush(new TextWebSocketFrame(Data.currentMentor() + "\n"));
        }

        return JsonRes.resSuccess();
    }


}
