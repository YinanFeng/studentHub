package com.studentHub.matchCentre;


import com.studentHub.matchCentre.webSocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MatchCentreApplication implements CommandLineRunner {

    @Autowired
    private WebSocketServer wss;

    public static void main(String[] args) {
        SpringApplication.run(MatchCentreApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        wss.start();
    }

}