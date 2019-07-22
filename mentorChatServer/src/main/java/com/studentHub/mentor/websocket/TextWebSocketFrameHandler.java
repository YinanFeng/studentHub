package com.studentHub.mentor.websocket;

import com.alibaba.fastjson.JSONObject;
import com.studentHub.mentor.common.ChannelMapper;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {



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

        if(userInfo == true){
            ChannelMapper.add(mentorId,incoming);
            System.out.println("new mentor" + mentorId + "connect of type" + type);
            //connect with matchCenter
        }else{

            System.out.println(mentorId + " send " + message);
            //connect with matchCenter and send message
            //if failed, remove
        }



        //test
        //ctx.close();
       // System.out.println(incoming.isActive());

//      for (Channel channel : channels) {
//            if (channel != incoming) {
//                channel.writeAndFlush(new TextWebSocketFrame("[" + incoming.remoteAddress() + "]" + msg.text()));
//            } else {
//                channel.writeAndFlush(new TextWebSocketFrame("[本地发送]：" + msg.text()));
//                // Console打印，可以删除
//                StringBuffer sb = new StringBuffer();
//                sb.append(incoming.remoteAddress()).append("->").append(msg.text());
//                System.out.println(sb.toString());
//            }
//        }
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