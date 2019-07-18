package com.studentHub.mentor.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

@Component
public class WebSocketServer {

        //    @PostConstruct
        public void start() throws Exception {
            //boss threads handle connections and pass processing to worker threads
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            //handle other logic except connections(receving message...)
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                        .childHandler(new WebSocketServerInitializer())
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, true);

                System.out.println("WebsocketChatServer start - studentHub student part");

                //bind the port, start to accept the connection
                ChannelFuture f = b.bind(8083).sync();
                //let server wait until the channel close
                f.channel().closeFuture().sync();
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
                System.out.println("WebsocketChatServer close - studentHub student part");
            }
        }
}

//    public static void main(String[] args) throws Exception {
//
//        //boss threads handle connections and pass processing to worker threads
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        //handle other logic except connections(receving message...)
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//
//        try {
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
//                    .childHandler(new WebSocketServerInitializer())
//                    .option(ChannelOption.SO_BACKLOG, 128)
//                    .childOption(ChannelOption.SO_KEEPALIVE, true);
//
//            System.out.println("WebsocketChatServer start - studentHub mentor part");
//
//            //bind the port, start to accept the connection
//            ChannelFuture f = b.bind(8083).sync();
//            //let server wait until the channel close
//            f.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//            System.out.println("WebsocketChatServer close - studentHub mentor part");
//        }
//    }





