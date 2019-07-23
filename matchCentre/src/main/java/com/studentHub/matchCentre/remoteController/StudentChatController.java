package com.studentHub.matchCentre.remoteController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="studentHub-chat-student")
public interface StudentChatController {


    @RequestMapping(value="/stuChat/receive")
    JSONObject receiveMessageFromMentor(
            @RequestParam("message") String message,
            @RequestParam("stuId") String stuId
    );

    @RequestMapping(value="/stuChat/leave")
    JSONObject mentorLeave(
            @RequestParam("stuId") String stuId
    );


}
