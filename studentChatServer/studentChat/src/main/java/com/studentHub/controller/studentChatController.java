package com.studentHub.controller;

import com.alibaba.fastjson.JSONObject;
import com.studentHub.common.ChannelMapper;
import com.studentHub.common.JsonRes;
import com.studentHub.websocket.TextWebSocketFrameHandler;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentChatController {

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        //test success
//        Object msg = new TextWebSocketFrame("testetstetetette \n");
//        TextWebSocketFrameHandler.channels.writeAndFlush(msg);
        return "hello "+name+"，this is first messge";
    }

    @RequestMapping("/stuChat/receive")
    public JSONObject receiveMessageFromMentor(@RequestParam String message, @RequestParam String stuId) {

        Channel cn = ChannelMapper.get(stuId);
        if(cn == null){
            return JsonRes.resError();
            //inform mentor this stu is not here
        }

        Object msg = new TextWebSocketFrame(message + "\n");
        cn.writeAndFlush(msg);

        return JsonRes.resSuccess();
    }


    @RequestMapping("/stuChat/leave")
    public JSONObject mentorLeave(@RequestParam String stuId) {
        Channel cn = ChannelMapper.get(stuId);
        if(cn == null){
            return JsonRes.resError();
        }

        cn.writeAndFlush(new TextWebSocketFrame("mentor already leave" + "\n"));

        //need more test? what is different from ctx.close() is this ok?
        cn.close();
        
        return JsonRes.resSuccess();
    }
}
