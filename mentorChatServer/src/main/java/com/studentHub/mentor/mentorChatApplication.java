package com.studentHub.mentor;

import com.studentHub.mentor.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class mentorChatApplication implements CommandLineRunner {

    @Autowired
    private WebSocketServer wss;


    public static void main(String[] args) {
        SpringApplication.run(mentorChatApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        wss.start();
    }
}