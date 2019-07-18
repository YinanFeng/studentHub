package com.studentHub;



import com.studentHub.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.Resources;

@SpringBootApplication
@EnableDiscoveryClient
public class studentChatApplication implements CommandLineRunner {

    @Autowired
    private WebSocketServer wss;


    public static void main(String[] args) {
        SpringApplication.run(studentChatApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        wss.start();
    }
}