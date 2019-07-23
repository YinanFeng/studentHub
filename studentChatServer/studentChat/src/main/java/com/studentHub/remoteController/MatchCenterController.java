package com.studentHub.remoteController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="studentHub-match-centre")
public interface MatchCenterController {


    @PostMapping(value = "/stu/studentNewCov")
    JSONObject studentNewCov(
            @RequestParam("stuNum") String stuNum,
            @RequestParam("mentorType") String mentorType
    );

    @PostMapping(value = "/stu/studentNewMessage")
    JSONObject studentNewMessage(
            @RequestParam("stuNum") String stuNum,
            @RequestParam("message") String message

    );

    @PostMapping(value = "/stu/studentLeaveCov")
    JSONObject studentLeaveCov(
            @RequestParam("stuNum") String stuNum
    );




}
