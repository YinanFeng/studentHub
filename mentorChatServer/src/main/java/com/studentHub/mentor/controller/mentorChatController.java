package com.studentHub.mentor.controller;

import com.alibaba.fastjson.JSONObject;
import com.studentHub.mentor.common.ChannelMapper;
import com.studentHub.mentor.common.JsonRes;
import com.studentHub.mentor.websocket.TextWebSocketFrameHandler;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MentorChatController {

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        //test success
//        Object msg = new TextWebSocketFrame("testetstetetette \n");
//        TextWebSocketFrameHandler.channels.writeAndFlush(msg);

        return "hello "+name+"，this is first messge";
    }


    @RequestMapping("/mentorChat/receive")
    public JSONObject receiveMessageFromMentor(@RequestParam String message, @RequestParam String mentorId) {

        Channel cn = ChannelMapper.get(mentorId);
        if(cn == null){
            return JsonRes.resError();
            //inform mentor this stu is not here
        }

        Object msg = new TextWebSocketFrame(message + "\n");
        cn.writeAndFlush(msg);

        return JsonRes.resSuccess();
    }

    //student leave, inform mentor
    @RequestMapping("/mentorChat/leave")
    public JSONObject mentorLeave(@RequestParam String mentorId) {
        Channel cn = ChannelMapper.get(mentorId);
        if(cn == null){
            return JsonRes.resError();
        }

        cn.writeAndFlush(new TextWebSocketFrame("student already leave" + "\n"));

        //need more test? what is different from ctx.close() is this ok?
        cn.close();

        return JsonRes.resSuccess();
    }
}