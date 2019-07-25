package com.studentHub.matchCentre.webSocket;

import com.alibaba.fastjson.JSONObject;
import com.studentHub.matchCentre.common.Data;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("in add "+ incoming.remoteAddress());

        channels.add(ctx.channel());

        incoming.writeAndFlush(new TextWebSocketFrame(Data.currentMentor() + "\n"));

    }



    // 有通道关闭时响应
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "离开");
        channels.remove(ctx.channel());
    }

    // 通道激活时响应
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "在线");
    }


    // 异常时响应
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "异常");

        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

    // 处理从客户端发来的消息，核心方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel incoming = ctx.channel();
        incoming.writeAndFlush(new TextWebSocketFrame(Data.currentMentor() + "\n"));
    }



}
