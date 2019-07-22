package com.studentHub.mentor.controller;

import com.studentHub.mentor.websocket.TextWebSocketFrameHandler;
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
}