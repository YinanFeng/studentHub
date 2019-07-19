package com.studentHub.matchCentre;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MatchCentreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchCentreApplication.class, args);

    }

}