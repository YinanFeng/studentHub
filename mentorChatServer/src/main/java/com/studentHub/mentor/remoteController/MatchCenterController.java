package com.studentHub.mentor.remoteController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="studentHub-match-centre")
public interface MatchCenterController {

    @PostMapping(value = "/mentor/mentorNewMessage")
    JSONObject mentorNewMessage(
            @RequestParam("mentorId") String mentorId,
            @RequestParam("message") String message

    );

    @PostMapping(value = "/mentor/mentorJoinCov")
    JSONObject mentorJoinCov(
            @RequestParam("mentorId") String mentorId,
            @RequestParam("type") String type
    );

    @PostMapping(value = "/mentor/mentorLeaveCov")
    JSONObject mentorLeaveCov(
            @RequestParam("mentorId") String mentorId
    );



}


