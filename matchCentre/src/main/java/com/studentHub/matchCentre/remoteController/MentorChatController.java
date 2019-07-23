package com.studentHub.matchCentre.remoteController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="studentHub-chat-mentor")
public interface MentorChatController {

    @RequestMapping(value="/mentorChat/receive")
    JSONObject receiveMessageFromStudent(
            @RequestParam("message") String message,
            @RequestParam("mentorId") String mentorId
    );

    @RequestMapping(value="/mentorChat/leave")
    JSONObject studentLeave(
            @RequestParam("mentorId") String mentorId
    );

}
