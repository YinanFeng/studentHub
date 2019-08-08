package com.studentHub.mentor.websocket;

import com.alibaba.fastjson.JSONObject;
import com.studentHub.mentor.common.ChannelMapper;
import com.studentHub.mentor.messageResolve.MessageResolve;
import com.studentHub.mentor.remoteController.MatchCenterController;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

   // @Autowired
   // MatchCenterController matchCenterController;

    //---test
//    public static TextWebSocketFrameHandler textWebSocketFrameHandler;
//
//    @PostConstruct
//    public void init(){
//        textWebSocketFrameHandler=this;
//        textWebSocketFrameHandler.matchCenterController=this.matchCenterController;
//    }

    //--test

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    // 处理从客户端发来的消息，核心方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        Channel incoming = ctx.channel();


        JSONObject jo = JSONObject.parseObject(msg.text());
        Boolean userInfo = jo.getBoolean("userInfo");
        String mentorId = jo.getString("mentorId");
        String message = jo.getString("message");
        String type = jo.getString("type");
        Boolean close = jo.getBoolean("close");

        if(userInfo == true) {
            ChannelMapper.add(mentorId,incoming);
            System.out.println("new mentor" + mentorId + "connect of type" + type);
            MessageResolve.mentorJoin(mentorId,type);
            //matchCenterController.mentorJoinCov(mentorId,type);


            //connect with matchCenter
        }else if(close == true) {
            ctx.close();
        }else {
                MessageResolve.mentorMessage(mentorId,message);
                //matchCenterController.mentorNewMessage(mentorId,message);
                System.out.println(mentorId + " send " + message);
                //connect with matchCenter and send message
                //if failed, remove
        }

    }

    // 有新通道加入的时候响应
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
      //  System.out.println(incoming.remoteAddress());
//        for (Channel channel : channels) {
//            channel.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 加入"));
//        }
        channels.add(ctx.channel());
        //ChannelMapper.add("test",incoming);
        //System.out.println(incoming.isActive());
        System.out.println("Client:" + incoming.remoteAddress() + "加入");


        //System.out.println(incoming.isActive());

    }

    // 有通道关闭时响应
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
     //   System.out.println(incoming.remoteAddress());
        //for (Channel channel : channels) {
        //    channel.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 离开"));
        //}
        System.out.println("Client:" + incoming.remoteAddress() + "离开");
       // System.out.println(incoming.isActive());
        channels.remove(ctx.channel());

        String mentorId = ChannelMapper.findMentor(incoming);
        //inform match center mentor leave


        //what if student first leave, send to mentor then mentor leave, go to this step
        //will inform student again ??????? not good!
        MessageResolve.mentorLeave(mentorId);
        //matchCenterController.mentorLeaveCov(mentorId);
        ChannelMapper.remove(incoming);



    }

    // 通道激活时响应
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "在线");
    }

    // 通道关闭时响应
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "掉线");
    }

    // 异常时响应
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "异常");

        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
        ChannelMapper.remove(incoming);
    }

}